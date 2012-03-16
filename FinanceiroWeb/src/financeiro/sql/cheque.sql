CREATE TABLE cheque(
	cheque int(11) not null,
	conta int(11) not null,
	data_cadastro date not null,
	situacao char(1) not null,
	lancamento int(11) default null,
	primary key (cheque,conta),
	key fk_cheque_lancamento (lancamento),
	key fk_cheque_conta(conta),
	constraint fk_cheque_conta foreign key (conta) references conta_bancaria (cod_conta) on delete cascade,
	constraint fk_lancamento foreign key (lancamento) references lancamento (codigo) on delete cascade
	
);