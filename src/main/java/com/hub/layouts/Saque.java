package com.hub.layouts;

import java.math.BigDecimal;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class Saque {
    
    /**
     * Exibe o formulário de adiconar o valor do saldo.
     * @param scanner Scanner para capturar a entrada do usuário.
     */

    public BigDecimal exibirFormulario(Scanner scanner) {
        System.out.println("\u001B[34m--------------------------------\u001B[0m"); 
        System.out.println("\u001B[1m              Saque              \u001B[0m"); 
        System.out.println("\u001B[34m--------------------------------\u001B[0m");
        System.out.println("Deseja sacar quanto?");
        System.out.print("\u001B[34mValor: \u001B[0m");
        BigDecimal valor = scanner.nextBigDecimal();
         
        System.out.println("\u001B[34m--------------------------------\u001B[0m");

        return valor;

    }
}
