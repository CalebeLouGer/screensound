package br.com.alura.screensound.main;

import br.com.alura.screensound.model.Artista;
import br.com.alura.screensound.model.Categoria;
import br.com.alura.screensound.model.Musica;
import br.com.alura.screensound.model.TipoArtista;
import br.com.alura.screensound.repository.ArtistaRepository;
import br.com.alura.screensound.service.ConsultaChatGPT;
import br.com.alura.screensound.service.ConsumoApi;
import br.com.alura.screensound.service.ConverteDados;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private ConsultaChatGPT consultaChatGPT = new ConsultaChatGPT();
    private ArtistaRepository repository;

    public Main(ArtistaRepository repository) {
        this.repository = repository;
    }

    public void exibirMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    ======-Screen Sound Músicas-======
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
        var cadastrarNovoArtista = "S";
        while (cadastrarNovoArtista.equalsIgnoreCase("s")){
            System.out.println("Informe o Nome do Artista: ");
            var nomeArtista = leitura.nextLine();
            System.out.println("Informe o Tipo do Artista: 'Solo','Dupla','Banda'");
            var tipo = leitura.nextLine();
            System.out.println("Informe o Gênero Musical do Artista: 'Rock','Gospel','Sertanejo','Pagode'");
            var generoMusical = leitura.nextLine();

            Categoria categoriaGenero = Categoria.valueOf(generoMusical.toUpperCase());
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nomeArtista,tipoArtista,categoriaGenero);
            repository.save(artista);

            System.out.println("Deseja cadastrar um novo Artista? (S/N)");
            cadastrarNovoArtista = leitura.nextLine();
        }
    }

    private void cadastrarMusica() {
        System.out.println("Informe o Artista para Cadastrar a Música: ");
        var nomeArtista = leitura.nextLine();
        Optional<Artista> artistaOptional = repository.findByNomeContainingIgnoreCase(nomeArtista);

        if(artistaOptional.isPresent()){
            System.out.println("Informe o Nome da Música: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artistaOptional.get());
            artistaOptional.get().getMusicaList().add(musica);
            repository.save(artistaOptional.get());
        }else{
            System.out.println("Artista NÃO ENCONTRADO!");
        }
    }

    private void listarMusica() {
        List<Artista> artistaList = repository.findAll();
        artistaList.forEach(a -> a.getMusicaList().forEach(System.out::println));
    }

    private void buscarMusicaPorArtista() {
        System.out.println("Informe o Nome do Artista para a Busca: ");
        var nomeArtista = leitura.nextLine();
        List<Musica> musicaList = repository.buscaMusicaPorArtista(nomeArtista);
        musicaList.forEach(System.out::println);
    }

    private void buscarDadosArtista() {
        System.out.println("Informe o nome do Artista que deseja saber: ");
        var nomeArtista = leitura.nextLine();
        var buscarDadosArtista = consultaChatGPT.obterInformacao(nomeArtista);
        System.out.printf("%s ",buscarDadosArtista);
    }
}
