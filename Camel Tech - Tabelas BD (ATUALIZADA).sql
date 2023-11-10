CREATE DATABASE camelTech;
USE camelTech;

-- TABELA provedora
CREATE TABLE provedora (
  idProvedora INT PRIMARY KEY auto_increment,
  nomeFantasia VARCHAR(45),
  razaoSocial VARCHAR(45),
  email VARCHAR(45),
  senha VARCHAR(45),
  cnpj VARCHAR(14));
  
  INSERT INTO provedora (nomeFantasia, razaoSocial, email, senha, cnpj) VALUES
('CamelTech Hosting', 'CamelTech Hosting Ltda.', 'contato@cameltechhosting.com', 'senha123', '12345678901234'),
('ServerPro', 'ServerPro Informática', 'atendimento@serverpro.com.br', 'server456', '98765432109876'),
('DataCenter123', 'DataCenter 123 Serviços de Hospedagem', 'suporte@dc123.com', 'dcP@ssw0rd', '56789012341234');


-- TABELA unidadeProvedora
CREATE TABLE unidadeProvedora (
  idunidadeProvedora INT PRIMARY KEY auto_increment,
  nomeUnidade VARCHAR(45),
  senha VARCHAR(45),
  cep CHAR(8),
  rua VARCHAR(45),
  complemento VARCHAR(45),
  numero VARCHAR(45),
  fkProvedora INT NOT NULL,
  constraint fkunidProv foreign key(fkProvedora) references provedora(idProvedora))
  ;
  INSERT INTO unidadeProvedora (nomeUnidade, senha, cep, rua, complemento, numero, fkProvedora) VALUES
('Unidade 1', 'senhaUnidade1', '12345678', 'Rua A', 'Complemento 1', '123', 1),
('Unidade 2', 'senhaUnidade2', '98765432', 'Rua B', 'Complemento 2', '456', 2),
('Unidade 3', 'senhaUnidade3', '54321876', 'Rua C', 'Complemento 3', '789', 3);


-- TABELA servidor
CREATE TABLE servidor (
  idServidor INT PRIMARY KEY auto_increment,
  nome VARCHAR(45),
  nomeProcessador VARCHAR(45),
  fkUnidade INT NOT NULL,
  constraint fkUnidServ foreign key(fkUnidade) references unidadeProvedora(idUnidadeProvedora));
  INSERT INTO servidor (nome, nomeProcessador, fkUnidade) VALUES
('Servidor 1', 'Intel Xeon E5', 1),
('Servidor 2', 'AMD Ryzen 9', 2),
('Servidor 3', 'Intel Core i7', 3);



-- TABELA tipoComponente
CREATE TABLE tipoComponente (
  idtipoComponente INT PRIMARY KEY auto_increment,
  tipo VARCHAR(45));
  INSERT INTO tipoComponente (tipo) VALUES
('RAM'),
('Disco'),
('CPU'),
('Rede');

-- TABELA tipoDado
CREATE TABLE tipoDado (
  idtipoDado INT PRIMARY KEY auto_increment,
  tipoDado VARCHAR(45));
  INSERT INTO tipoDado (tipoDado) VALUES
('Uso de RAM'),
('Espaço em Disco'),
('Uso de Disco'),
('Uso de CPU'),
('bytesRecebidos'),
('bytesEnviados');

-- TABELA configuracao
CREATE TABLE configuracao (
  idConfiguracao INT auto_increment,
  fkServidor INT NOT NULL,
  fktipoComponente INT NOT NULL,
  PRIMARY KEY (idConfiguracao, fkServidor, fktipoComponente),
  FOREIGN KEY (fkServidor) REFERENCES servidor(idServidor),
  FOREIGN KEY (fktipoComponente) REFERENCES tipoComponente(idtipoComponente)
);

INSERT INTO configuracao values
(null,1,1),
(null,1,2),
(null,1,3),

(null,2,1),
(null,2,2),
(null,2,3),

(null,3,1),
(null,3,2),
(null,3,3),
(null,3,4);

  
  select * from configuracao;

-- TABELA dadosCapturados
CREATE TABLE dadosCapturados (
  iddadosCapturados INT PRIMARY KEY auto_increment,
  dadoCapturado DECIMAL(10,5),
  dtHora DATETIME,
  fkConfiguracao INT,
  constraint fkConfigCap foreign key(fkConfiguracao) references configuracao(idConfiguracao),
  fkTipoDado INT,
  constraint fkTipoDadoCap foreign key(fkTipoDado) references tipoDado(idTipoDado));
  
-- Para o Servidor 1
INSERT INTO dadosCapturados (dadoCapturado, dtHora, fkConfiguracao, fkTipoDado)
VALUES
(85.5, '2023-11-05 11:15:00', 1, 1),
(750.0, '2023-11-05 11:30:00', 2, 2),
(25.0, '2023-11-05 12:00:00', 3, 3);

-- Para o Servidor 2
INSERT INTO dadosCapturados (dadoCapturado, dtHora, fkConfiguracao, fkTipoDado)
VALUES
(90.0, '2023-11-05 11:15:00', 4, 1),
(720.5, '2023-11-05 11:30:00', 5, 2),
(22.5, '2023-11-05 12:00:00', 6, 3);

-- Para o Servidor 3
INSERT INTO dadosCapturados (dadoCapturado, dtHora, fkConfiguracao, fkTipoDado)
VALUES
(87.0, '2023-11-05 11:15:00', 7, 1),
(760.0, '2023-11-05 11:30:00', 8, 2),
(24.0, '2023-11-05 12:00:00', 9, 3);



  select * from dadoscapturados;
  
  -- SELECTS 

-- select para pegar pelo nome do servidor todos os dados
SELECT servidor.nome AS Servidor,
       tipoComponente.tipo AS Componente, 
       dadosCapturados.dadoCapturado AS DadoCapturado
FROM dadosCapturados
INNER JOIN configuracao ON dadosCapturados.fkConfiguracao = configuracao.idConfiguracao
INNER JOIN tipoComponente ON configuracao.fktipoComponente = tipoComponente.idtipoComponente
INNER JOIN servidor ON configuracao.fkServidor = servidor.idServidor
WHERE servidor.idServidor = 1;


-- Selecionar tipo de dado e componente
SELECT tipoComponente.tipo AS Componente,
       tipoDado.tipoDado AS TipoDado,
       dadosCapturados.dadoCapturado AS DadoCapturado
FROM dadosCapturados
INNER JOIN configuracao ON dadosCapturados.fkConfiguracao = configuracao.idConfiguracao
INNER JOIN tipoComponente ON configuracao.fktipoComponente = tipoComponente.idtipoComponente
INNER JOIN tipoDado ON dadosCapturados.fkTipoDado = tipoDado.idtipoDado
WHERE configuracao.fkServidor = 2;


SELECT provedora.nomeFantasia AS Provedora,
       unidadeProvedora.nomeUnidade AS Unidade,
       unidadeProvedora.rua AS Rua,
       unidadeProvedora.cep AS CEP
FROM provedora
INNER JOIN unidadeProvedora ON provedora.idProvedora = unidadeProvedora.fkProvedora
WHERE provedora.nomeFantasia = 'CamelTech Hosting';


-- Selecionar média de uso de ram dos servidores
SELECT AVG(dadosCapturados.dadoCapturado) AS MediaRAM
FROM dadosCapturados
INNER JOIN configuracao ON dadosCapturados.fkConfiguracao = configuracao.idConfiguracao
INNER JOIN tipoComponente ON configuracao.fktipoComponente = tipoComponente.idtipoComponente
WHERE tipoComponente.tipo = 'RAM';





