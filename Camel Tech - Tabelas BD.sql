
CREATE DATABASE camelTech;
USE camelTech;


-- TABELA PROVEDORA
CREATE TABLE provedora (
  idProvedora INT PRIMARY KEY AUTO_INCREMENT,
  nomeFantasia VARCHAR(45) NULL,
  razaoSocial VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  CNPJ VARCHAR(14) NULL
);


INSERT INTO provedora (idProvedora, nomeFantasia, razaoSocial, email, senha, CNPJ)
VALUES (1, 'Nome Fantasia 1', 'Razão Social 1', 'email1@example.com', 'senha123', '123456789');

INSERT INTO provedora (idProvedora, nomeFantasia, razaoSocial, email, senha, CNPJ)
VALUES (2, 'Nome Fantasia 2', 'Razão Social 2', 'email2@example.com', 'senha456', '987654321');


-- TABELA unidadeProvedora
CREATE TABLE unidadeProvedora (
  idunidadeProvedora INT PRIMARY KEY auto_increment,
  nomeUsuario VARCHAR(45) NULL,
  cep CHAR(8) NULL,
  rua VARCHAR(45) NULL,
  complemento VARCHAR(45) NULL,
  numero VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  fkProvedora INT NOT NULL,
  constraint fkProv foreign key(fkProvedora) REFERENCES provedora(idProvedora)
  );

INSERT INTO unidadeProvedora (idunidadeProvedora, nomeUsuario, cep, rua, complemento, numero, senha, fkProvedora)
VALUES (1, 'Usuario1', '12345678', 'Rua 1', 'Complemento 1', '123', 'senha123', 1);

INSERT INTO unidadeProvedora (idunidadeProvedora, nomeUsuario, cep, rua, complemento, numero, senha, fkProvedora)
VALUES (2, 'Usuario2', '87654321', 'Rua 2', 'Complemento 2', '456', 'senha456', 2);


-- TABELA servidor
CREATE TABLE servidor (
  idServidor INT PRIMARY KEY auto_increment,
  Nome VARCHAR(45) NULL,
  fkUnidade INT NOT NULL,
  constraint fkUnid foreign key (fkUnidade) REFERENCES unidadeProvedora(idUnidadeProvedora)
  );

INSERT INTO servidor (idServidor, Nome, fkUnidade)
VALUES (1, 'Servidor 1', 1);

INSERT INTO servidor (idServidor, Nome, fkUnidade)
VALUES (2, 'Servidor 2', 2);

SELECT * FROM servidor;


-- TABELA dadosHardware
CREATE TABLE dadosHardware (
  idDadosHardware INT PRIMARY KEY AUTO_INCREMENT,
  nomeProcessador VARCHAR(255),
  usoDisco DECIMAL(10, 2) NULL,
  tamanhoDisco DECIMAL(10, 2) NULL,
  rede DECIMAL(10, 2) NULL,
  qtdEmUso DOUBLE,
  frequencia BIGINT,
  totalRam DECIMAL(10, 2) NULL,
  emUsoRam DECIMAL(10, 2) NULL
);


-- Inserir um registro com valores para todas as colunas
INSERT INTO dadosHardware (nomeProcessador, usoDisco, tamanhoDisco, rede, qtdEmUso, frequencia, totalRam, emUsoRam)
VALUES ('Intel Core i7', 30.50, 500.00, 100.25, 3.5, 3200, 16.00, 6.50);

-- Inserir um registro com valores nulos para algumas colunas
INSERT INTO dadosHardware (nomeProcessador, usoDisco, tamanhoDisco, rede, qtdEmUso, frequencia, totalRam, emUsoRam)
VALUES ('AMD Ryzen 5', NULL, NULL, 200.75, 4.0, 3600, NULL, NULL);

-- Inserir outro registro com valores diferentes
INSERT INTO dadosHardware (nomeProcessador, usoDisco, tamanhoDisco, rede, qtdEmUso, frequencia, totalRam, emUsoRam)
VALUES ('Intel Core i5', 15.75, 256.00, 50.00, 2.8, 2800, 8.00, 4.25);

SELECT * FROM dadosHardware;


-- TABELA captura
CREATE TABLE captura (
  idCaptura INT PRIMARY KEY auto_increment,
  dataHora DATETIME NULL,
  fkServidor INT NOT NULL,
  constraint fkServ foreign key (fkServidor) REFERENCES servidor(idServidor),
  fkDados INT NOT NULL,
  CONSTRAINT fkDadosHard FOREIGN KEY (fkDados) REFERENCES dadosHardware(idDadosHardware)
  );

INSERT INTO captura (idCaptura, dataHora, fkServidor, fkDados)
VALUES (1, NOW(), 1,1);

INSERT INTO captura (idCaptura, dataHora, fkServidor, fkDados)
VALUES (2, NOW(), 2,2);
SELECT * FROM captura;







-- TABELA alertas
CREATE TABLE alertas (
  idAlerta INT PRIMARY KEY auto_increment,
  tipoAlerta VARCHAR(45) NULL,
  descricao VARCHAR(45) NULL,
  dataHora DATETIME NULL,
  severidade VARCHAR(45) NULL,
  fkServidor INT NOT NULL,
  constraint fkServAlert foreign key(fkServidor) REFERENCES servidor(idServidor)
  );


INSERT INTO alertas (idAlerta, tipoAlerta, descricao, dataHora, severidade, fkServidor)
VALUES (1, 'Erro', 'Erro crítico no servidor 1', NOW(), 'Alto', 1);

INSERT INTO alertas (idAlerta, tipoAlerta, descricao, dataHora, severidade, fkServidor)
VALUES (2, 'Aviso', 'Aviso de baixo espaço em disco no servidor 2', NOW(), 'Médio', 2);

SELECT * FROM alertas;

-- SELECTS teste

-- Select para mostrar dados do disco de um servidor específico
SELECT dh.usoDisco, dh.tamanhoDisco
FROM dadosHardware dh
INNER JOIN captura c ON dh.fkCaptura = c.idCaptura
INNER JOIN servidor s ON c.fkServidor = s.idServidor
WHERE s.idServidor = 1;

-- Select todos os dados do servidor1
SELECT dh.*
FROM dadosHardware dh
INNER JOIN captura c ON dh.fkCaptura = c.idCaptura
INNER JOIN servidor s ON c.fkServidor = s.idServidor
WHERE s.idServidor = 1;















