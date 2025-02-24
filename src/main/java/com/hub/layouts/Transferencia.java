package com.hub.layouts;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class Transferencia {

    public Map<String, Object> exibirFormulario(Scanner scanner) {
        System.out.println("\u001B[34m--------------------------------\u001B[0m"); 
        System.out.println("\u001B[1m          Transferência          \u001B[0m"); 
        System.out.println("\u001B[34m--------------------------------\u001B[0m");
        System.out.println("Deseja transferir quanto?");
        System.out.print("\u001B[34mValor: \u001B[0m");
        BigDecimal valor = scanner.nextBigDecimal();
        System.out.print("\u001B[34mConta Destino: \u001B[0m");
        Long conta = scanner.nextLong();
        System.out.println("\u001B[34m--------------------------------\u001B[0m");
        
        Map<String, Object> dados = new HashMap<>();
        dados.put("valor", valor);
        dados.put("contaDestino", conta);
        return dados;
    }
}
