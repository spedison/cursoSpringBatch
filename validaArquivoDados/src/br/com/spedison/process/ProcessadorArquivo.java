package br.com.spedison.process;

import br.com.spedison.regras.RegraGeral;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class ProcessadorArquivo {
    private String nomeArquivo;
    private List<RegraGeral> regras = new LinkedList<>();

    public ProcessadorArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo.replaceAll("[.]csv$","");
    }

    public void adicionaRegra(RegraGeral regra) {
        regras.add(regra);
    }

    BufferedWriter abreArquivoEscrita(String nomeArquivo) throws IOException {
        FileOutputStream fi = new FileOutputStream(nomeArquivo);
        OutputStreamWriter isr = new OutputStreamWriter(fi, "ISO-8859-1");
        BufferedWriter br = new BufferedWriter(isr);
        return br;
    }


    public void processa() throws IOException {

        FileInputStream fi = new FileInputStream(nomeArquivo+".csv");
        InputStreamReader isr = new InputStreamReader(fi, "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);

        BufferedWriter saidaOk = abreArquivoEscrita(nomeArquivo + ".ok.csv");
        BufferedWriter saidaErro = abreArquivoEscrita(nomeArquivo + ".erro.csv");

        String linha;
        final int[] contador = {0};
        while ((linha = br.readLine()) != null) {

            // Header.
            if (contador[0] == 0) {
                saidaOk.write(linha);
                saidaOk.newLine();
                saidaErro.write(linha);
                saidaErro.newLine();
                contador[0]++;
                continue;
            }

            final var l = linha;
            // Valida todas as Regras.
            regras
                    .stream()
                    .parallel()
                    .forEach(r -> r.rodaRegra(l, contador[0]));
            // Envia o resultado para os arquivos de erro ou correto, para separar os dados
            if (
                    regras.stream().filter(RegraGeral::isUltimoResultado).count() == regras.size()) {
                saidaOk.write(linha);
                saidaOk.newLine();
            } else {
                saidaErro.write(linha);
                saidaErro.newLine();
            }
            contador[0]++;
        }
        saidaOk.close();
        saidaErro.close();
        br.close();
    }

    public void imprimeResultado() {
        System.out.println("\n\n======== Fim do processamento ======");
        regras
                .stream()
                .sequential()
                .forEach(RegraGeral::mostraErros);
    }

}
