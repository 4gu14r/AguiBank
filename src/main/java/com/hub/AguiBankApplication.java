package com.hub;

import com.hub.layouts.*;
// import com.hub.utils.Terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class AguiBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(AguiBankApplication.class, args);
		executarMenu();
	}

	public static void executarMenu() {
		try (Scanner scanner = new Scanner(System.in)) {
			TelaInicial menuPage = new TelaInicial();

			int opcao = 0;
			do {
				// Terminal.clear();
				opcao = menuPage.Menu(scanner);
				switch (opcao) {
					case 1:
						Cadastro cadastroPage = new Cadastro();
						cadastroPage.exibirFormulario(scanner);
						break;
					case 2:
						ApplicationContext context = new AnnotationConfigApplicationContext(AguiBankApplication.class);
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
