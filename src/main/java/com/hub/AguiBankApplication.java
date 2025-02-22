package com.hub;

import com.hub.layouts.*;
// import com.hub.utils.Terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class AguiBankApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AguiBankApplication.class, args);
		executarMenu(context);
	}

	public static void executarMenu(ConfigurableApplicationContext context) {
		try (Scanner scanner = new Scanner(System.in)) {
			TelaInicial menuPage = new TelaInicial();

			int opcao = 0;
			do {
				// Terminal.clear();
				opcao = menuPage.Menu(scanner);
				switch (opcao) {
					case 1:
						Cadastro cadastroPage = context.getBean(Cadastro.class);
						cadastroPage.exibirFormulario(scanner);
						break;
					case 2:
						Login loginPage = context.getBean(Login.class);
						loginPage.exibirFormulario(scanner);
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
}
