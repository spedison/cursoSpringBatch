package br.com.spedison.primeirobach.tasklets;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
@Log4j2
public class TruncaTabelaTasklet implements Tasklet {

    @Autowired
    @Qualifier("appDataSource")
    DataSource dataSource;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        try {
            Connection conn = dataSource.getConnection();
            log.info("Apagando os dados de livro_importado");
            conn.beginRequest();
            PreparedStatement ps = conn.prepareStatement("truncate table livro_importado");
            boolean ret = ps.execute();
            conn.commit();
            log.info("Apagado os dados da tabela livro_importado");
            return RepeatStatus.FINISHED;
        } catch (SQLException sqle) {
            log.error("Erro ao truncar a tabela " + sqle.getMessage());
            return RepeatStatus.CONTINUABLE;
        }
    }
}
