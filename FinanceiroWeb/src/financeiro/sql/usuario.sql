use financeiro;
CREATE TABLE usuario(
	codigo int(11) not null auto_increment,
	nome varchar(50) not null,
	login varchar (50) not null,
	senha varchar(10) not null,
	nascimento date not null,
	celular varchar(25),
	idioma varchar(10) not null,
	ativo tinyint(1) not null,
	primary key (codigo),
	unique key login (login)
	
);
