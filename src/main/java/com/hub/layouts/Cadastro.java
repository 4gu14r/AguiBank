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
        System.out.println("\u001B[33m-------------------\u001B[0m"); 
        System.out.println("\u001B[1m     Cadastro     \u001B[0m"); 
        System.out.println("\u001B[33m-------------------\u001B[0m");
        
        System.out.print("\u001B[32mNome: \u001B[0m"); 
        String nome = scanner.nextLine();
        System.out.print("\u001B[32mEmail: \u001B[0m");
        String email = scanner.nextLine();
        System.out.print("\u001B[32mSenha: \u001B[0m");
        String senha = scanner.nextLine();
        
        System.out.println("\u001B[33m-------------------\u001B[0m");
        signClient.registrarUsuario(email, senha, nome);
    }
}