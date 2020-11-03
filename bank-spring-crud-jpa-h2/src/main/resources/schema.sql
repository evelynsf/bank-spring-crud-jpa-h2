create table if not exists usuario (
    id bigint,
    data_cadastro timestamp not null,
    nome varchar(30) not null,
    username varchar(15) not null,
    password varchar(10) not null,
    telefone varchar(11),
    email varchar(100),
    perfil char(1) not null,
    status char(1) not null,
    primary key (id),
    unique key (username)
);

create table if not exists cliente (
    id bigint primary key,
    data_cadastro timestamp not null,
    nome varchar(30) not null,
    cpfcnpj varchar(14) not null,
    logradouro varchar(50) not null,
    cidade varchar(40) not null,
    uf char(2) not null,
    cep char(8) not null,
    telefone varchar(11),
    email varchar(100)
);

create table if not exists livro_caixa (
    id bigint,
    clienteid bigint not null,
    data_lancamento timestamp not null,
    descricao varchar(50) not null,
    tipo char(1) not null,
    valor decimal(12,2) not null,  
    primary key (id),
    foreign key (clienteid) references cliente(id)
);

create sequence if not exists hibernate_sequence start with 100;