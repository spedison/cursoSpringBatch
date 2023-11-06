package br.com.spedison.primeirobach.itens.writer;

import br.com.spedison.primeirobach.model.Livro;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class ConfigArquivoLivrosItemWriter {

    @Bean("processaArquivoItemWriterBean")
    @StepScope
    public ItemWriter<Livro> writer(
            @Qualifier("appDataSource")     DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Livro>()
                .dataSource(dataSource)
                .sql(
                        """
                                INSERT INTO livro_importado (isbn, titulo, ano_Publicacao, autor, publicador, urlImageS, urlImageM, urlImageL)
                                VALUES (
                                                :isbn, 
                                                :titulo,                          
                                                :anoPublicacao,
                                                :autor,
                                                :publicador,
                                                :urlImagemS,
                                                :urlImagemM,
                                                :urlImagemL
                                )
                                """)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .build();
        /*
                                -- ON DUPLICATE KEY UPDATE
                                --                titulo     = :titulo,
                                --                ano_Publicacao = :anoPublicacao,
                                --                autor      = :autor,
                                --                publicador = :publicador,
                                --                urlImageS  = :urlImagemS,
                                --                urlImageM  = :urlImagemM,
                                --                urlImageL  = :urlImagemL
        * */
    }


}
