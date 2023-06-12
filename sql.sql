create table funcao(
    codigo int primary key,
    funcao varchar(40)
);

insert into funcao values(1, 'ADMIN');
insert into funcao values(2, 'GARCOM');
insert into funcao values(3, 'COZINHA');

create table usuarios(
    login varchar(60) primary key,
    senha varchar(20),
    nome varchar(40),
    cpf varchar(15),
    funcao int,
    ativo boolean default true,
    foreign key(funcao) references funcao(codigo)
);

insert into usuarios values('bianca', '1234', 'Bianca Magro', '029.292.151-94', 1);
insert into usuarios values('vitor', '1234', 'Vitor Fraporti', '034.626.290-94', 2);
insert into usuarios values('joana', '1234', 'Joana Gerevini', '123.456.789-09', 3);

create table produtos(
    codigo serial primary key,
    nome varchar(40),
    preco decimal(10,2),
    ind_cozinha boolean,
    ativo boolean default true
);

insert into produtos values(1, 'Coca-Cola', 6.99, false);
insert into produtos values(2, 'Guaraná', 5.00, false);
insert into produtos values(3, 'Torta de frango', 12.50, true);
insert into produtos values(4, 'Café longo', 7.25, true);

create table comanda(
    codigo serial primary key,
    mesa int,
    data_criada VARCHAR(10),
    cliente varchar(20),
    ativo boolean default true
);

create table status(
    codigo int primary key,
    status varchar(10)
);

insert into status values(1, 'Solicitado');
insert into status values(2, 'Preparando');
insert into status values(3, 'Pronto');
insert into status values(4, 'Entregue');


create table pedido(
    codigo serial primary key,
    data_pedido VARCHAR(10),
    quantidade int,
    produto int,
    comanda int,
    status int,
    foreign key(status) references status(codigo),
    foreign key(produto) references produtos(codigo),
    foreign key(comanda) references comanda(codigo)
);
