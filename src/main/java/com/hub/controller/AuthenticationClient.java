package com.hub.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationClient {
    
    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apiKey}")
    private String supabaseApiKey;

    /**
     * Realiza a autenticação chamando a API do Supabase.
     * @param email Email informado pelo usuário.
     * @param senha Senha informada pelo usuário.
     */

    public Boolean autenticar(String email, String senha) {
        try {            
    
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
                return true;
            } else {
                System.out.println("Erro ao realizar login. Código de status: " + statusCode);
                System.out.println("Resposta: " + response.body());
                return false;
            }
            
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
