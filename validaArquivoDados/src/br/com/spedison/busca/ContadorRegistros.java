package br.com.spedison.busca;

import br.com.spedison.model.Livro;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContadorRegistros implements Closeable {
    Connection connection;

    public ContadorRegistros() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mariadb://mariadb-perf.casa.com.br:3306/springbatch?user=root&password=123456");
    }

    public boolean achouID(Livro livro) throws SQLException {
        String sql = "select count(1) as contagem from livro_importado where isbn = '%s'".formatted(livro.getIsbn());

        ResultSet result = connection.prepareStatement(sql).executeQuery();

        result.next();
        boolean ret = result.getInt(1) == 1;
        result.close();
        return ret;
    }

    @Override
    public void close() throws IOException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
