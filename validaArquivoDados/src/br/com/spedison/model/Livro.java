package br.com.spedison.model;

import lombok.Data;

@Data
public class Livro {
    private String isbn;
    private String titulo;
    private Integer anoPublicacao;
    private String autor;
    private String publicador;
    private String urlImagemS;
    private String urlImagemM;
    private String urlImagemL;
}
