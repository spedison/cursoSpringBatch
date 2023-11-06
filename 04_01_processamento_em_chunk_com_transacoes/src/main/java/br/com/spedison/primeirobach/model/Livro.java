package br.com.spedison.primeirobach.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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