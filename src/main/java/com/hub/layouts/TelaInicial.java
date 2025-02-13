package com.hub.layouts;

import java.util.Scanner;

public class TelaInicial {
    public int Menu(Scanner scanner) {
        System.out.println("********************************");
        System.out.println("Bem-vindo ao AguiBank!");
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");
        System.out.println("********************************");
        
		System.out.print("\nOpção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;
    }
}
