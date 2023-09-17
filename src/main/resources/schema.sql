
CREATE TABLE `user`
(
    `id`   bigint       NOT NULL AUTO_INCREMENT,
    `uu`   varchar(255) NOT NULL,
    `type` int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;