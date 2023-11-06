create schema app;

use app;

CREATE TABLE livro_importado (
                                   isbn varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
                                   titulo varchar(300) DEFAULT NULL,
                                   ano_Publicacao int(11) DEFAULT NULL,
                                   autor varchar(300) DEFAULT NULL,
                                   publicador varchar(300) DEFAULT NULL,
                                   urlImageS varchar(1024) DEFAULT NULL,
                                   urlImageM varchar(1024) DEFAULT NULL,
                                   urlImageL varchar(1024) DEFAULT NULL,
                                   id bigint(20) NOT NULL AUTO_INCREMENT,
                                   PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;