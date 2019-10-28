CREATE DATABASE HospitalFMU;
USE HospitalFMU;

CREATE TABLE pacientes (
	id_pac INTEGER AUTO_INCREMENT NOT NULL,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dt_nasc VARCHAR (10) NOT NULL,
    sexo VARCHAR(9) NOT NULL,
    telfix VARCHAR(10) NULL,
    telcel VARCHAR(11) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    plano_saude VARCHAR(20) NOT NULL,
    CONSTRAINT PK_pac PRIMARY KEY (id_pac)
);
CREATE TABLE funcionarios (
	id_func INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    dt_nasc VARCHAR(10) NOT NULL,
    sexo VARCHAR(9) NOT NULL,
    telfix VARCHAR(10) NULL,
    ramal VARCHAR(3) NULL,
    telcel VARCHAR(11) NOT NULL,
    cep VARCHAR(8) NOT NULL,
    cargo VARCHAR(30) NOT NULL,
    turno VARCHAR(8) NOT NULL,
    salario VARCHAR(20) NOT NULL,
    CONSTRAINT PK_Func PRIMARY KEY (id_func)
);
CREATE TABLE usuarios (
	id_user INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
    login VARCHAR(20) NOT NULL,
    senha VARCHAR(20) NOT NULL,   
    CONSTRAINT FK_func FOREIGN KEY (id_user) REFERENCES funcionarios (id_func),
    CONSTRAINT PK_user PRIMARY KEY (id_user)
);

SELECT * FROM pacientes;
SELECT * FROM funcionarios;
SELECT * FROM usuarios;

DROP TABLE pacientes;
DROP TABLE funcionarios;
DROP TABLE usuarios;

INSERT INTO funcionarios (nome, cpf, dt_nasc, sexo, telfix, ramal, telcel, cep, cargo, turno, salario) VALUES ("Admin", "00000000000", "01/01/0000",
"Outros", null, null, "00000000000", "00000000", "CEO", "Integral", "R$0,00");
INSERT INTO usuarios (login, senha, nome, cpf) VALUES ("admin", "admin123", "Admin", "00000000000");