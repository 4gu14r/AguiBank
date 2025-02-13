package com.hub.layouts;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.util.Scanner;

public class Cadastro {

    // Ajuste esses valores conforme sua configuração do Supabase.
    private static final String SUPABASE_URL = "https://kabjwnhllotjibbxlfse.supabase.co";
    private static final String SUPABASE_API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImthYmp3bmhsbG90amliYnhsZnNlIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzg5OTQ3MDIsImV4cCI6MjA1NDU3MDcwMn0.OVHY1RdlHCfPAmRReV-k1ddLFyPtr7wCdeWvngIrf4A";

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
            // Monta a URL para o cadastro.
            String url = SUPABASE_URL + "/auth/v1/signup";

            // Monta o corpo da requisição em formato JSON.
            String requestBody = "{\"email\":\"" + email + "\", \"password\":\"" + senha + "\", \"data\":{\"displayName\":\"" + nome + "\"}}";

            // Cria a requisição HTTP POST.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("apikey", SUPABASE_API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            // Cria o cliente HTTP e envia a requisição.
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica o status da resposta.
            int statusCode = response.statusCode();
            if (statusCode == 200 || statusCode == 201) {
                System.out.println("Cadastro realizado com sucesso!");
                System.out.println("Resposta: " + response.body());
                // Aqui você pode utilizar uma biblioteca (como Gson ou Jackson) para fazer o parsing do JSON retornado.
            } else {
                System.out.println("Erro ao realizar cadastro. Código de status: " + statusCode);
                System.out.println("Resposta: " + response.body());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
