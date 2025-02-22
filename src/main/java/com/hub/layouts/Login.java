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

    public Boolean exibirFormulario(Scanner scanner) {
        System.out.println("\u001B[34m--------------------------------\u001B[0m"); 
        System.out.println("\u001B[1m             Login               \u001B[0m"); 
        System.out.println("\u001B[34m--------------------------------\u001B[0m");
        
        System.out.print("\u001B[34mEmail: \u001B[0m");
        String email = scanner.nextLine();
        System.out.print("\u001B[34mSenha: \u001B[0m");
        String senha = scanner.nextLine();
         
        System.out.println("\u001B[34m--------------------------------\u001B[0m");

        return authClient.autenticar(email, senha);
    }
}