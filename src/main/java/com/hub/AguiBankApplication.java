package com.hub;

import com.hub.layouts.*;
import com.hub.model.Cliente;
import com.hub.service.ContaBancariaService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
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
                            executarMenuPrincipal(context, scanner);
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

    public static void executarMenuPrincipal(ConfigurableApplicationContext context, Scanner scanner) {
        Cliente cliente = Cliente.getInstance();

        TelaPrincipal menuPrincipal = context.getBean(TelaPrincipal.class);
        ContaBancariaService contaService = context.getBean(ContaBancariaService.class);
        int opcao;

        do {
            opcao = menuPrincipal.exibirTelaPrincipal(scanner);
            switch (opcao) {
                case 1:
                    BigDecimal saldo = contaService.consultarSaldo(cliente.getUserId());
                    System.out.println("Saldo atual: R$ " + saldo);
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