package br.com.spedison.primeirobach.step;

import br.com.spedison.primeirobach.itens.writer.ImprimeDadosWriter;
import br.com.spedison.primeirobach.processor.ProcessadorParImparItemProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.stream.IntStream;

@Configuration
public class ImprimeParImparStepConfig {

    @Autowired
    private StepBuilderFactory sbf;

    @Bean("parImparStep")
    public Step imprimeParImparStep(
            @Autowired
            ImprimeDadosWriter imprimeWriter,
            @Autowired
            ProcessadorParImparItemProcessor parOuImparProcessor) {
        return sbf
                .get("imprimeParImparStep")
                .<Integer, String>chunk(50)
                .reader(contaAteDezReader(null))
                .processor(parOuImparProcessor)
                .writer(imprimeWriter)
                .build();
    }

    @StepScope
    @Bean("contaAteNBean")
    public IteratorItemReader<Integer> contaAteDezReader(@Value("#{jobParameters['maxNumero']}") Integer max) {
        return new IteratorItemReader<Integer>(IntStream.range(0, max + 1).iterator());
    }


}
