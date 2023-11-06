package br.com.spedison.primeirobach;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class PrimeiroBachApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeiroBachApplication.class, args);
    }

}
