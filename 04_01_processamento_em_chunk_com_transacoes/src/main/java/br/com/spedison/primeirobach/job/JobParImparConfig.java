package br.com.spedison.primeirobach.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.AbstractJob;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
* Eu desabilitei todos os Jobs e deixei somente o Job ExecutaTudo. Tive dificuldades em executar Jobs em sequencia, mesmo rodando a aplicação
* em linha de comando. Talvez tenha que exetivamente desligar o auto Configuration do Spring Boot e pegar os Beans pontualmente um a um
* e solicitar a executção usando os componentes necessários.
* Achei um Arquivo razoavelmente grande para brincar e deixei ele devidamente ajustado para carregar todos os daods
* Estou usando o MariaDB, vou colcoar os scripts para banco app e spring.
* Por enquanto só vou fazer isso para dar andamento ao curso.
* */

//@Configuration
public class JobParImparConfig {

//    @Autowired
//    JobBuilderFactory jbf;

//    @Bean("imprimeParImparJob")
//    public Job imprimeParImparJob(
//            @Qualifier("parImparStep") Step imprimeParImparStep) {
//        var ret = jbf
//                .get("imprimeParImparJob")
//                .start(imprimeParImparStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//        return ret;
//    }
}
