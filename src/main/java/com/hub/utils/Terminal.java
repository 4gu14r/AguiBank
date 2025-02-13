package com.hub.utils;

public class Terminal {
    public static void clear() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                // Para Windows, executa o comando "cls"
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Para sistemas Unix/Linux/Mac, utiliza códigos ANSI para limpar a tela
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o terminal: " + e.getMessage());
        }
    }
}
