package com.hub.layouts;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hub.controller.AuthenticationClient;

@Component
public class Login {

    @Autowired
    private AuthenticationClient authClient;

    /**
     * Exibe o formulário de login e processa a autenticação.
     * @param scanner Scanner para capturar a entrada do usuário.
     */

    public void exibirFormulario(Scanner scanner) {
        System.out.print("\nEmail: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        authClient.autenticar(email, senha);
    }
}
