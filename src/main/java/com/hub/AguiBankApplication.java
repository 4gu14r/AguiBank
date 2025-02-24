package com.hub;

import com.hub.layouts.*;
import com.hub.model.Cliente;
import com.hub.model.Transacao;
import com.hub.service.ContaBancariaService;
import com.hub.service.DepositoService;
import com.hub.service.SaqueService;
import com.hub.service.TransacaoService;
import com.hub.utils.TransacaoUtils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.util.List;
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
        
        TelaPrincipal menuPrincipal = context.getBean(TelaPrincipal.class);
        ContaBancariaService contaService = context.getBean(ContaBancariaService.class);
        TransacaoService transacaoService = context.getBean(TransacaoService.class);
        
        Cliente cliente = Cliente.getInstance();
        Long contaBancaria = contaService.consultarNumContaPorUserId(cliente.getUserId());
        
        int opcao;

        do {
            opcao = menuPrincipal.exibirTelaPrincipal(scanner);
            switch (opcao) {
                case 1:
                    BigDecimal saldo = contaService.consultarSaldoPorUserId(cliente.getUserId());
                    System.out.println("Saldo atual: R$ " + saldo);
                    break;
                case 2:
                    try {
                        List<Transacao> transacoes = transacaoService.extratoBancario(contaBancaria);
                        TransacaoUtils.imprimirExtrato(transacoes);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    Saque saquePage = context.getBean(Saque.class);
                    SaqueService saqueService = context.getBean(SaqueService.class);
                    BigDecimal valorSaque = saquePage.exibirFormulario(scanner);
                    try{
                        saqueService.realizarSaque(valorSaque, contaBancaria);
                        System.out.println("Saque de R$: "+valorSaque+" realizado, seu saldo atual é de "+contaService.consultaSaldoPorNumConta(contaBancaria));
                    }catch(Exception e){
                        System.out.println("Erro ao processar o saque: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Transferência");
                    break;
                case 5:
                    Deposito depositoPage = context.getBean(Deposito.class);
                    DepositoService depositoService = context.getBean(DepositoService.class);
                    BigDecimal valorDeposito = depositoPage.exibirFormulario(scanner);
                    try {
                        depositoService.realizarDeposito(valorDeposito, contaBancaria);
                        System.out.println("Depósito  de R$: "+valorDeposito+" realizado com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao processar o depósito: " + e.getMessage());
                    }
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