package br.com.spedison.regras;

public class RegraQuantidadeDeTokens extends RegraGeral {
    @Override
    protected boolean processamentoRegra(String linha, int numero) {
        if (linha == null)
            return false;

        if (linha.split("[;]").length != 8) {
            System.out.println("Quantidade de tokens da linha %d é inválido.".formatted(numero));
            return false;
        }
        return true;
    }
}
