package com.hub.layouts;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Login {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apiKey}")
    private String supabaseApiKey;


    /**
     * Exibe o formulário de login e processa a autenticação.
     * @param scanner Scanner para capturar a entrada do usuário.
     */

    public void exibirFormulario(Scanner scanner) {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        autenticar(email, senha);
    }


    /**
     * Realiza a autenticação chamando a API do Supabase.
     * @param email Email informado pelo usuário.
     * @param senha Senha informada pelo usuário.
     */

    private void autenticar(String email, String senha) {
        try {            

            System.out.println("Valor de supabaseUrl: " + supabaseUrl);
    
            String url = supabaseUrl + "/auth/v1/token?grant_type=password";

            String requestBody = "{\"email\":\"" + email + "\", \"password\":\"" + senha + "\"}";

            // Cria a requisição HTTP POST.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", supabaseApiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Cria o cliente HTTP e envia a requisição.
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            if (statusCode == 200) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Resposta: " + response.body());
            } else {
                System.out.println("Erro ao realizar login. Código de status: " + statusCode);
                System.out.println("Resposta: " + response.body());
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
