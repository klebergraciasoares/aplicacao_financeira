use financeiro;
CREATE TABLE conta_bancaria(
	cod_conta int(11) not null AUTO_INCREMENT,
	cod_usuario int(11) not null,
	des_conta varchar(255) default null,
	dat_cadastro datetime not null,
	saldo_inicial float default null,
	favorita bit(1) default null,
	primary key (cod_conta),
	constraint fk_conta_usuario
	foreign key (cod_usuario)
	references usuario (codigo) on delete cascade
);