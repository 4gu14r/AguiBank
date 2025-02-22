package com.hub.layouts;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hub.controller.RegistrationClient;

@Component
public class Cadastro {

    @Autowired
    private RegistrationClient signClient;
    
    /**
     * Exibe o formulário de cadastro e registra o usuário via Supabase Auth.
     * @param scanner Scanner para capturar a entrada do usuário.
     */

    public void exibirFormulario(Scanner scanner) {
        System.out.println("---- Cadastro ----");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        signClient.registrarUsuario(email, senha, nome);
    }

    
}
