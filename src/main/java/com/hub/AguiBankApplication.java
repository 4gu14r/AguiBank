package com.hub;

import com.hub.layouts.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class AguiBankApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AguiBankApplication.class, args);
        executarMenuInicial(context);
    }

    public static void executarMenuInicial(ConfigurableApplicationContext context) {
        try (Scanner scanner = new Scanner(System.in)) {
            TelaInicial menuInicial = new TelaInicial();

            int opcao;
            do {
                opcao = menuInicial.Menu(scanner);
                switch (opcao) {
                    case 1:
                        Cadastro cadastroPage = context.getBean(Cadastro.class);
                        cadastroPage.exibirFormulario(scanner);
                        break;
                    case 2:
                        Login loginPage = context.getBean(Login.class);
                        if (loginPage.exibirFormulario(scanner)) {
                            executarMenuPrincipal(scanner); // Passa o mesmo scanner
                        }
                        break;
                    case 3:
                        System.out.println("\nSaindo ...\n");
                        break;
                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                        break;
                }
            } while (opcao != 3);
        }
    }

    public static void executarMenuPrincipal(Scanner scanner) {
        TelaPrincipal menuPrincipal = new TelaPrincipal();

        int opcao;
        do {
            opcao = menuPrincipal.exibirTelaPrincipal(scanner);
            switch (opcao) {
                case 1:
                    System.out.println("Consulta");
                    break;
                case 2:
                    System.out.println("Imprimir Extrato");
                    break;
                case 3:
                    System.out.println("Saque");
                    break;
                case 4:
                    System.out.println("Transferência");
                    break;
                case 5:
                    System.out.println("Depósito");
                    break;
                case 6:
                    System.out.println("Voltando ao Menu Inicial");
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 6);
    }
}