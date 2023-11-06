package br.com.spedison.regras;

public abstract class RegraGeral {
    private int contaErrors;
    private int contaLinhasAvaliadas;
    private String nomeRegra;
    private boolean ultimoResultado;


    public RegraGeral() {
        nomeRegra = this.getClass().getName();
        contaErrors = 0;
        contaLinhasAvaliadas = 0;
        ultimoResultado = false;
    }

    public RegraGeral(String nomeRegra, int numero) {
        this.nomeRegra = nomeRegra;
        contaErrors = 0;
        contaLinhasAvaliadas = 0;
    }

    public void rodaRegra(String linha, int numero) {
        contaLinhasAvaliadas++;
        ultimoResultado = processamentoRegra(linha, numero);
        if (!ultimoResultado) {
            contaErrors++;
        }
    }

    public boolean isUltimoResultado() {
        return ultimoResultado;
    }

    abstract protected boolean processamentoRegra(String linha, int numero);

    public void mostraErros() {
        System.out.printf("A regra %s processou %d linhas e detectou %d erros\n",
                nomeRegra, contaLinhasAvaliadas, contaErrors);
    }
}
