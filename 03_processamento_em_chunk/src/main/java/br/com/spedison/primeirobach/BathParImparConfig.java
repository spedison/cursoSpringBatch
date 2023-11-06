package br.com.spedison.primeirobach;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.stream.IntStream;

@Configuration
public class BathParImparConfig {

    @Autowired
    JobBuilderFactory jbf;

    @Autowired
    StepBuilderFactory sbf;

    @Bean
    Job imprimeParImparJob() {
        return jbf
                .get("imprimeParImparJob")
                .start(imprimeParImparStep())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Step imprimeParImparStep() {
        return sbf
                .get("imprimeParImparStep")
                .<Integer, String>chunk(1)
                .reader(contaAteDezReader(null))
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    @StepScope
    @Bean
    public IteratorItemReader<Integer> contaAteDezReader(@Value("#{jobParameters['maxNumero']}") Integer max) {
        return new IteratorItemReader<Integer>(IntStream.range(0,max+1).iterator());
    }

    public FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<Integer, String>(item -> "Item " + item + " eh : " + (item % 2 == 0 ? "PAR" : "IMPAR"));
    }

    public ItemWriter<String> imprimeWriter() {
        return
                (items) ->
                        items
                                .stream()
                                .map(s -> (new Date()).toString() + " - " + s)
                                .forEach(System.out::println);
    }

//    @StepScope
//    @Bean
//    public Tasklet imprimeOlaMundoTasklet(@Value("#{jobParameters['nome']}") String nome) {
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                System.out.println((new Date()).toString() + "  -  Olá " + nome);
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }

}
