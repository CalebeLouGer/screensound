package br.com.alura.screensound.main;

import br.com.alura.screensound.service.ConsultaChatGPT;
import br.com.alura.screensound.service.ConsumoApi;
import br.com.alura.screensound.service.ConverteDados;

import java.util.Scanner;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private ConsultaChatGPT consultaChatGPT = new ConsultaChatGPT();

    public void exibirMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    ==================================
                    [1] Cadastrar Artistas
                    [2] Cadastrar Músicas
                    [3] Lista Música
                    [4] Buscar Música por Artista
                    [5] Pesquisar Dados Sobre um Artista
                    
                    [0] Sair
                    ==================================""";

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusica();
                    break;
                case 4:
                    buscarMusicaPorArtista();
                    break;
                case 5:
                    buscarDadosArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
    private void cadastrarArtista() {

    }

    private void cadastrarMusica() {

    }

    private void listarMusica() {

    }

    private void buscarMusicaPorArtista() {

    }

    private void buscarDadosArtista() {
        System.out.println("Informe o nome do Artista que deseja saber: ");
        var nomeArtista = leitura.nextLine();
        var buscarDadosArtista = consultaChatGPT.obterInformacao(nomeArtista);
        System.out.printf("%s ",buscarDadosArtista);
    }
}
