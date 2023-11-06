package br.com.spedison.primeirobach.step;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProcessadorParImparStepConfig implements ItemProcessor<Integer, String> {
    @Override
    public String process(Integer item) throws Exception {
        return "Item <Refatorado> " + item + " eh : " + (item % 2 == 0 ? "PAR" : "IMPAR");
    }
}
