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

    // Injeção dos valores a partir do arquivo de configuração/variáveis de ambiente
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
            // Verifica se a variável supabaseUrl está nula
            if (supabaseUrl == null) {
                System.err.println("Erro: supabaseUrl está null!");
                return; // ou lance uma exceção personalizada
            }
            
            // Exibe o valor da URL para debug
            System.out.println("Valor de supabaseUrl: " + supabaseUrl);
    
            // Monta a URL com o grant_type na query string
            String url = supabaseUrl + "/auth/v1/token?grant_type=password";

            // Monta o corpo da requisição em formato JSON
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

            // Verifica o status da resposta.
            int statusCode = response.statusCode();
            if (statusCode == 200) {
                System.out.println("Login realizado com sucesso!");
                System.out.println("Resposta: " + response.body());
                // Aqui, você pode utilizar uma biblioteca (ex.: Gson ou Jackson) para fazer o parsing do JSON e extrair o token JWT.
            } else {
                System.out.println("Erro ao realizar login. Código de status: " + statusCode);
                System.out.println("Resposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
