CREATE TABLE IF NOT EXISTS `estado_avaliacao` (
  `id_estado_avaliacao` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `estado_jogo` (
  `id_estado_jogo` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `estado_transacao` (
  `id_estado_transacao` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `interesse` (
  `id_interesse` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `metodo_envio` (
  `id_metodo_envio` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `nivel` (
  `id_nivel` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS `plataforma` (
  `id_plataforma` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
  `descricao` VARCHAR(200) NOT NULL,
  `marca` VARCHAR(150) NOT NULL
);
