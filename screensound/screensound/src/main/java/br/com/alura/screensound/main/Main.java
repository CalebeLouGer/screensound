package br.com.alura.screensound.main;

import br.com.alura.screensound.service.ConsumoApi;
import br.com.alura.screensound.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibirMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    ==================================
                    [1] 
                    
                    [0] Sair
                    ==================================""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}
