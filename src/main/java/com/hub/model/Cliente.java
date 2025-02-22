package com.hub.model;

public class Cliente {
    private static Cliente instance;
    private String userId;
    private String accessToken;
    private String refreshToken;

    // Construtor privado para garantir o Singleton
    private Cliente() {
        this.userId = null;
        this.accessToken = null;
        this.refreshToken = null;
    }

    // Método para obter a instância única (Singleton)
    public static Cliente getInstance() {
        if (instance == null) {
            instance = new Cliente();
        }
        return instance;
    }

    // Métodos para gerenciar a sessão
    public void setSessao(String userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    // Getters
    
    public String getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public boolean isLogado() {
        return userId != null;
    }
}