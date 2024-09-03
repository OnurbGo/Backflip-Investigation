CREATE TABLE itens (
    Id_itens INT NOT NULL PRIMARY KEY,
    Id_cenas INT,
    Descricao VARCHAR(100),
    Erro VARCHAR(50),
    Interagir VARCHAR(100),
    Carregavel BOOLEAN
);

CREATE TABLE Cenas (
    Id_cenas INT NOT NULL PRIMARY KEY,
    Id_itens INT,
    titulo VARCHAR(50),
    descricao VARCHAR(600),
    FOREIGN KEY (Id_itens) REFERENCES itens(Id_itens)
);


create table Pessoas(
Id_pessoas Int not null primary key,
nome Varchar(50),
Comando Varchar(50),
Descricao Varchar(100),
Erro Varchar(200)
)

create table saveGame (
Id_save Int not null primary key,
Id_cenas Int,
foreign key (Id_cenas) references Cenas(Id_cenas)
)

create table itens_save(
Id_itens int not null primary key, 
Id_save int,
foreign key (Id_itens) references itens(Id_itens),
foreign key (Id_save) references saveGame(Id_save)
)

insert into cenas(Id_cenas,Id_itens,titulo,descricao) values (0,0,'Introdução','No inicio do dia você é chamado a uma cena de crime, a esposa do grande invetor Jhon Backflip pediu pelo socorro da policia, ao encontrar o corpo ja sem vida de seu marido jogado em seu escritorio, ao entrar na sala você vê algumas coisas que chamam a sua atenção, o "Corpo Do John", uma "Capsula De Municão Desconhecida", uma "Comoda Movida" e Marcas De "Pegadas De Lama" No Chão. As seguintes pessoas estão presente:
O Detetive Don Bigodone
A Fachineira Joana Samambaia
O vizinho James Fubacake
O corpo do john já sem vida
A sua esposa Amelia Hotwhells
E seu melhor amigo Willian Frontflip');
insert into cenas(Id_cenas,Id_itens,titulo,descricao) values (1,0,'Armeiro','Ao entrar no estabelecimento, você se encontra com o dito cujo Armeiro conhecido também por');
insert into cenas(Id_cenas,Id_itens,titulo,descricao) values (2,0,'Acusção Final','Estando na delegacia, você pode conversar com Willian, a esposa de Backflip, e Grassfield');


insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (0,0,'Capsula de munição desconhecida','Parece que isso não pode',0,'USE bala',1);
insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (1,0,'Essas pegadas batem com os sapatos do john ou seja ele so entrou em casa com os sapatos sujos, bem que estava chovendo ontem.','ERROOOOOU',1,'USE Pegada em Corpo Do Jhon',0);
insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (2,0,'Ela diz que cuida da limpeza da casa e que tinha retirado o movel do lugar para limpar, porém esqueceu de colocar no lugar de volta.','ERROOOOOU',1,'USE Comoda em Fachineira Joana Samambaia',0);
insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (3,0,'tem um buraco de bala no colete mas não tem buraco de saida no terno, ou seja o john tirou o terno antes de ser baleado, provavelmente era alguem proximo pois ele se sentiu a vontade para tirar o terno.','ERROOOOOU',1,'USE analisar corpo de John',1);
insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (4,0,'ele escutou a chegada de alguma visita tarde da noite ao john apesar de não ter visto quem era.
Ele Tambem diz que se ouve disparo ele não ouviu nada apesar de morar ao lado, Possivelmente houve o uso de um silenciador.
','ERROOOOOU',1,'USE James Fubacake',1);
insert into itens (Id_itens,Id_Cenas,Descricao,Erro,Interagir,Carregavel) values (5,1,'capsula de 9mm','balapoggers',1,'USE bala no Armeiro',1);


insert into pessoas (Id_pessoas,nome,Comando,Descricao,Erro) values (0,'Detetive Don Bigodon','USE Capsula de munição desconhecida','Então Parece que essa capsula é de uma arma do calibre 9mm','Ele não quer ser importunado por coisas sem relevancias');
insert into pessoas (Id_pessoas,nome,Comando,Descricao,Erro) values (1,'Willian Frontflip','USE acusação','Você não deveria se meter naquilo que você desconhece, olhar para a criatura é um convite a loucura','Ele Parece suspeito mas não abre o bico');
insert into pessoas (Id_pessoas,nome,Comando,Descricao,Erro) values (2,'Fachineira Joana Samambaia','USE comoda movida','eu só limpo os aposentos do Sr.Backflip, movi a comoda para limpar e logo em seguida coloquei no canto','ela não ta segura de conversar com você');
insert into pessoas (Id_pessoas,nome,Comando,Descricao,Erro) values (3,'Vizinho James Fubacake','USE conversar','Bem que notei a preseça de mais alguém vindo junto do John, se houve tiros, não consegui ouvir nada','ele ta caladão, mas parece que ta mais assustado');
insert into pessoas (Id_pessoas,nome,Comando,Descricao,Erro) values (4,'Amelia Hotwhells','USE acusação','Eu não mataria meu próprio marido, ele era um homem perfeito, eu não tenho motivos para mata-lo','Ela demonstra grandes niveis de choque');


show tables from sqltextadventure;
drop table cenas;
drop table itens;
drop table pessoas;
drop table itens_save;
drop table savegame;