package br.com.spedison.primeirobach.tasklets;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ImprimeDadosTasklet implements ItemWriter<String>{

    @Override
    public void write(List<? extends String> items) throws Exception {
        items
                .stream()
                .map(s -> (new Date()).toString() + " - " + s)
                .forEach(System.out::println);
    }
}
