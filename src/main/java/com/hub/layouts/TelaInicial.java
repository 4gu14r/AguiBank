package com.hub.layouts;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class TelaInicial {
    public int Menu(Scanner scanner) {
        System.out.println("\u001B[34m********************************\u001B[0m"); 
        System.out.println("\u001B[1m     Bem-vindo ao AguiBank!          \u001B[0m"); 
        System.out.println("\u001B[34m********************************\u001B[0m");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");
        System.out.println("\u001B[34m********************************\u001B[0m");
        
        System.out.print("\n\u001B[34mOpção: \u001B[0m");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}