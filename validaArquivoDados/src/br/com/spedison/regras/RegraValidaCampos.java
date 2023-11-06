package br.com.spedison.regras;

public class RegraValidaCampos extends RegraGeral {
    @Override
    protected boolean processamentoRegra(String linha, int numero) {
        if (linha == null)
            return false;

        String[] dados = linha.split("[;]");

        if (dados[0].replaceAll("[\"]", "").isBlank()) {
            System.out.println("Linha %d tem o ISBN vazio".formatted(numero));
            return false;
        }

        try {
            try {
                long a = Long.parseLong(dados[3].replaceAll("[\"]", ""));
                if (a < 0)
                    return false;
            } catch (NumberFormatException nfe) {
                System.out.println("Linha %d tem ano inválido".formatted(numero));
                return false;
            }

            if (!dados[5].replaceAll("[\"]", "").toLowerCase().startsWith("http")) {
                System.out.println("Linha %d tem um link sem http(5)".formatted(numero));
                return false;
            }

            if (!dados[6].replaceAll("[\"]", "").toLowerCase().startsWith("http")) {
                System.out.println("Linha %d tem um link sem http(6)".formatted(numero));
                return false;
            }

            if (!dados[7].replaceAll("[\"]", "").toLowerCase().startsWith("http")) {
                System.out.println("Linha %d tem um link sem http(7)".formatted(numero));
                return false;
            }

            return true;
        } catch (ArrayIndexOutOfBoundsException aior) {
            System.out.println("Linha %d com a quantidade de tokens inválidos".formatted(numero));
            return false;
        }
    }
}