package br.com.spedison.regras;

public class RegraLinhaVazia extends RegraGeral {
    @Override
    protected boolean processamentoRegra(String linha, int numero) {
        if (linha == null || linha.isBlank()) {
            System.out.println("Linha %d está vazia".formatted(numero));
            return false;
        }
        return true;
    }
}
