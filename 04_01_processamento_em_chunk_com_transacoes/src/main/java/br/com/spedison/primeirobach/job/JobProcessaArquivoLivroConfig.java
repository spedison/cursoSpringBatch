package br.com.spedison.primeirobach.job;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class JobProcessaArquivoLivroConfig {

//    @Autowired
//    JobBuilderFactory jbf;

//    @Bean("processaArquivoLivrosJob")
//    public Job imprimeParImparJob(
//            @Qualifier("processaLivrosStep") Step processaArquivos//,
//            // @Qualifier("truncaTabelaStep") Step truncaTabelaStep
//            ) {
//        return jbf
//                .get("processaArquivoLivrosJob")
//                //.start(truncaTabelaStep)
//                .start(processaArquivos)
//                .incrementer(new RunIdIncrementer())
//                //.preventRestart()
//                .build();
//    }

//    @Bean
//    @Order(1)
//    Job processaArquivoLivrosJob(
//            @Qualifier("processaLivrosStep")
//            Step processaArquivoLivrosStep) {
//        return jbf
//                .get("processaArquivoLivrosJob")
//                .start(processaArquivoLivrosStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }



}
