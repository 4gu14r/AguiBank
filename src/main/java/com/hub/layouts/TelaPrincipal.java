package com.hub.layouts;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TelaPrincipal {
    public int exibirTelaPrincipal(Scanner scanner) {
        System.out.println("\u001B[34m********************************\u001B[0m");
        System.out.println("\u001B[1m               MENU               \u001B[0m"); 
        System.out.println("\u001B[34m********************************\u001B[0m");
        System.out.println("1. Consultar Saldo");
        System.out.println("2. Imprimir Extrato");
        System.out.println("3. Saque");
        System.out.println("4. Transferência");
        System.out.println("5. Depósito");
        System.out.println("6. Voltar ao Menu Inicial");
        System.out.println("\u001B[34m********************************\u001B[0m");

        System.out.print("\n\u001B[34mOpção: \u001B[0m");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}