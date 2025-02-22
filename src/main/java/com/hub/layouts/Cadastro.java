package com.hub.layouts;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Import para parsear o JSON (Jackson já vem com Spring Boot Starter Web)
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.service.ContaBancariaService;

@Component
public class Cadastro {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apiKey}")
    private String supabaseApiKey;

    @Autowired
    private ContaBancariaService contaBancariaService;

    
    /**
     * Exibe o formulário de cadastro e registra o usuário via Supabase Auth.
     * @param scanner Scanner para capturar a entrada do usuário.
     */

    public void exibirFormulario(Scanner scanner) {
        System.out.println("---- Cadastro ----");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        registrarUsuario(email, senha, nome);
    }

    /**
     * Realiza o registro do usuário chamando a API do Supabase.
     * @param email Email informado pelo usuário.
     * @param senha Senha informada pelo usuário.
     * @param nome Nome do usuário, que ficará em displayName.
     */

    private void registrarUsuario(String email, String senha, String nome) {
        try {
            String url = supabaseUrl + "/auth/v1/signup";

            String requestBody = "{\"email\":\"" + email + "\", \"password\":\"" + senha + "\", \"data\":{\"displayName\":\"" + nome + "\"}}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", supabaseApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200 || statusCode == 201) {
                System.out.println("Cadastro realizado com sucesso!");
                System.out.println("Resposta: " + response.body());

                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.body());
                String userId = root.path("user").path("id").asText();

                contaBancariaService.criarContaParaUsuario(userId);
                System.out.println("Registro de conta bancária criado para o usuário com id: " + userId);
            } else {
                System.out.println("Erro ao realizar cadastro. Código de status: " + statusCode);
                System.out.println("Resposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
