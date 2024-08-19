create table Cenas(
Id Integer,
Id_iten Integer,
titulo Varchar(50),
descricao Varchar(500)
)

create table itens(
Id Integer,
Id_Cena Integer,
Descricao Varchar (100),
Erro Varchar(50),
Interagir Varchar(100),
Carregavel Boolean
)

create table Pessoas(
Id Integer,
nome Varchar(50),
Comando Varchar(50),
Descricao Varchar(100),
Erro Varchar(200)
)


insert into cenas(Id,titulo,descricao) values (0,'Introdução','O grande inventor jhon backflip foi morto a sangue frio precisamos descobrir quem fez tal atrocidade');
insert into cenas(Id,titulo,descricao) values (1,'Invetigação','Estamos cada vez mais perto do culpado e achamos uma passagem secreta ao puxar um dos livros a passagem leva até uma catacumba sombria');

insert into itens (Id,Id_Cena,Descricao,Erro,Interagir,Carregavel) values (1,0,'Capsula de munição desconhecida','Parece que isso não pode',0,'USE bala',1);
insert into itens (Id,Id_Cena,Descricao,Erro,Interagir,Carregavel) values (1,1,'Adaga','Parece que isso não pode ser usado deste jeito',1,'USE Adaga',1);

insert into pessoas (Id,nome,Comando,Descricao,Erro) values (0,'Detetive Don Bigodon','USE Capsula de munição desconhecida','Então Parece que essa capsula é de uma arma do calibre 9mm','Ele não quer ser importunado por coisas sem relevancias');
insert into pessoas (Id,nome,Comando,Descricao,Erro) values (0,'Willian Frontflip','USE Adaga','Você não deveria se meter naquilo que você desconhece, olhar para a criatura é um convite a loucura','Ele Parece suspeito mas não abre o bico');


select * from cenas
where Id = 0;

select * from itens
where Id_Cena = 0;

select * from pessoas
where nome like '%Don%';


/*show tables from bancodedados1;
drop table cenas;
drop table itens;
drop table pessoas;
*/