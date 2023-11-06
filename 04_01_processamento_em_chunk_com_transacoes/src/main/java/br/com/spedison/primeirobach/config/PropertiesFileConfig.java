package br.com.spedison.primeirobach.config;

import br.com.spedison.primeirobach.commom.DetectOs;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import java.nio.file.Path;
import java.nio.file.Paths;

/****
 * Configura a aplicação para utilizar o applicarion.properties em qualquer diretório do SO.
 * Estou utilizando ele com pequenas variações caso esteja em um SO UnixLike ou Windows.
 */
@Configuration
@Log4j2
public class PropertiesFileConfig {

    static private DetectOs detectOs = new DetectOs();

    @Bean
    public PropertySourcesPlaceholderConfigurer applicationPropertiesLocationConfig() {
        var ret = new PropertySourcesPlaceholderConfigurer();

        // Home do Usuario
        String homeDir = System.getProperty("user.home");
        String configSystemDir;

        // Diretórios do sistema são definidos pelo tipo de sistema operacional (windows ou unix like.)
        if (detectOs.isMac() || detectOs.isSolaris() || detectOs.isUnix()) {
            configSystemDir = "/etc/";
        } else {
            configSystemDir = System.getenv("ProgramFiles");
        }
        String appFile = "application.properties";
        Path pathUserFile = Paths.get(homeDir, "springbath", "primeira-etl", appFile);
        Path pathConfigSystemFile = Paths.get(configSystemDir, "springbath", "primeira-etl", appFile);
        Path pathCurrentFile = Paths.get(System.getProperty("user.dir"), "application.properties");

        String path = "";

        if (pathCurrentFile.toFile().exists()) { // 1o o diretório Corrente da execução
            path = pathCurrentFile.toString();
        } else if (pathUserFile.toFile().exists()) { // 2o O home do usuario
            path = pathUserFile.toString();
        } else if (pathConfigSystemFile.toFile().exists()) { // 3o diretório de configuração do Sistema
            path = pathConfigSystemFile.toString();
        }

        log.info("Usando o diretório " + path);

        ret.setLocation(new FileSystemResource(path));

        return ret;
    }

}
