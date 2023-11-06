package br.com.spedison.primeirobach.job;


import br.com.spedison.primeirobach.tasklets.TruncaTabelaTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class JobTruncaTabelaConfig {

//    @Autowired
//    JobBuilderFactory jbf;

//    @Bean("truncaTabelaJob")
//    public Job imprimeParImparJob(
//            @Qualifier("truncaTabelaStep") Step truncaTabelaStep) {
//        return jbf
//                .get("truncaTabelaJob")
//                .start(truncaTabelaStep)
//                .incrementer(new RunIdIncrementer())
//                .preventRestart()
//                .build();
//    }

}
