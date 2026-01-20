package br.com.alura.screensound.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Musica> musicaList = new ArrayList<>();

    public Artista(String nomeArtista, TipoArtista tipo) {
        this.nome = nomeArtista;
        this.tipo = tipo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Musica> getMusicaList() {
        return musicaList;
    }

    public void setMusicaList(List<Musica> musicaList) {
        this.musicaList = musicaList;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", musicaList=" + musicaList +
                '}';
    }
}
