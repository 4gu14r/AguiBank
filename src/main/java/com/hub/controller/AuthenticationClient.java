package com.hub.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hub.model.Cliente;

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
                
                // Parsear a resposta JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.body());
                String accessToken = jsonResponse.get("access_token").asText();
                String refreshToken = jsonResponse.get("refresh_token").asText();
                JsonNode userNode = jsonResponse.get("user");
                String userId = userNode.get("id").asText();

                // Armazenar a sessão no Cliente (Singleton)
                Cliente.getInstance().setSessao(userId, accessToken, refreshToken);
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
