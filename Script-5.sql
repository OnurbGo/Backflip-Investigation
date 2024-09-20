CREATE TABLE Cenas (
    Id_cenas INT NOT NULL PRIMARY KEY,
    titulo VARCHAR(50),
    descricao VARCHAR(650)
);

CREATE TABLE items (
    Id_itens INT NOT NULL PRIMARY KEY,
    Id_cenas INT,
    Nome Varchar (100),
    Descricao VARCHAR(255),
    Resultado VARCHAR(500),
    Carregavel BOOLEAN,
    combined_item_id INT,
   	FOREIGN KEY (Id_cenas) REFERENCES Cenas(Id_cenas),
    FOREIGN KEY (combined_item_id) REFERENCES items(id_itens)
);


CREATE TABLE Login (
    Id_save INT NOT NULL PRIMARY KEY,
    Id_cenas INT,
    Id_itens INT,
    FOREIGN KEY (Id_itens) REFERENCES items(Id_itens),
    FOREIGN KEY (Id_cenas) REFERENCES Cenas(Id_cenas)
);


CREATE TABLE usuarios (
    Id_player INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cena_id INT,
    FOREIGN KEY (cena_id) REFERENCES Cenas(Id_cenas)
);

CREATE TABLE Inventory (
    inventory_id INT AUTO_INCREMENT PRIMARY KEY,
    Id_player INT,
    Id_item INT,
    FOREIGN KEY (Id_item) REFERENCES Items(Id_itens),
    FOREIGN KEY (Id_player) REFERENCES Usuarios(Id_player)
);

INSERT INTO cenas (Id_cenas, titulo, descricao) VALUES
(1,'Introdução', 'No inicio do dia, você é chamado a uma cena de crime, a esposa do grande inventor Jhon Backflip pediu pelo socorro da policia ao encontrar o corpo ja sem vida de seu marido jogado em seu escritorio. Entrando na sala você vê algumas coisas que chamam a sua atenção, o "Corpo Do John", uma "Capsula De Municão Desconhecida", uma "Comoda Movida" e Marcas De "Pegadas De Lama" No Chão. As seguintes pessoas estão presente: O Detetive Don Bigodone(você). A Fachineira Joana Samambaia. O vizinho James Fubacake. O corpo do john já sem vida. A sua esposa Amelia Hotwhells. O xerife. E seu melhor amigo Willian Frontflip'),
(2,'O Armeiro', 'Ao entrar no estabelecimento, você se encontra com o dito cujo Armeiro conhecido também por Philip Gunsman'),
(3,'Delegacia', 'Estando na delegacia, chegou a hora da verdade. Quem é o verdadeiro culpado? você pode conversar com Willian, a esposa de Backflip, e Grassfield');

/*
(2, 'Willian Frontflip', 'USE acusação', 'Se não fosse o detetive encherido com esse bigode esnobe, nunca teriam me descoberto'),
(3, 'Fachineira Joana Samambaia', 'USE comoda movida', 'Eu só limpo os aposentos do Sr.Backflip, movi a comoda para limpar e logo em seguida coloquei no canto'),
(4, 'Vizinho James Fubacake', 'USE conversar', 'Bem que notei a presença de mais alguém vindo junto do John, se houve tiros, não consegui ouvir nada'),
(5, 'Amelia Hotwhells', 'USE acusação', 'Eu não mataria meu próprio marido, ele era um homem perfeito, eu não tenho motivos para mata-lo'),
(6, 'Philip Gunsman', 'USE municao 9mm', 'Só conheço dois sujeitos que tem esse tipo de munição, pode ser o Jhoseph Grassfield ou Willian Frontflip'),
(7, 'Jhoseph Grassfield', 'USE conversar', 'eu tenho essa arma mas isso não significa que eu sou culpado'),
(8, 'Xerife', 'USE conversar', 'Infelizmente sei tanto quanto você');
*/

INSERT INTO items (Id_itens, Id_Cenas,combined_item_id, Nome, Descricao, Resultado, Carregavel) VALUES
(1, 1, null, 'MUNICAO_DESCONHECIDO', 'Uma cápsula de munição não reconhecível', 'Você descobre que essa cápsula é do calibre 9mm', 1),
(2, 1, null, 'PEGADAS_DE_LAMA', 'Tem um rastro de pegadas que passam por todo cômodo', 'As pegadas batem com o formato dos sapatos do John, além de que os sapatos dele estão sujos de lama', 0),
(3, 1, null, 'COMODA_MOVIDA', 'Uma cômoda claramente movida de lugar, tanto pela sujeira que marca seu lugar de origem quanto pelo rastro que as rodinhas deixaram', 'A empregada diz ter movido o móvel para limpar a parede, porém esqueceu de colocá-lo de volta no lugar', 0),
(4, 1, null, 'CORPO_JOHN', 'Tem um buraco de bala no colete mas não tem buraco de saída no terno, ou seja, o John tirou o terno antes de ser baleado, provavelmente era alguém próximo, pois ele se sentiu à vontade para tirar o terno.', 'Interessante, mas não acho que seja desse jeito', 0),
(5, 1, null, 'PISTA_VISITA', 'O vizinho diz que alguém veio visitar o John tarde da noite, também diz não ter escutado nenhum barulho apesar de um tiro ter sido disparado ao lado de seu apartamento.', 'Ele escutou a chegada de alguma visita tarde da noite ao John, apesar de não ter visto quem era. Ele também diz que se houve disparo ele não ouviu nada, possivelmente houve o uso de um silenciador.', 1),
(6, 1, null, 'PISTA_ARMA DO CRIME', 'Sabemos que a arma do crime era pequena e provavelmente comportava um silenciador', 'USE pista arma do crime em xerife', 1),
(7, 2, null, 'PISTA_SUSPEITOS', 'Você tem 3 suspeitos, sendo eles: um vizinho do John, um homem que mora muito longe e por fim Willian Frontflip', 'Foi você!!!', 1),
(8, 2, null, 'MUNICAO_9MM', 'Uma munição de calibre pequeno', 'Essa munição é usada em muitas armas, mas principalmente armas pequenas', 1);

INSERT INTO items (Id_itens, Id_Cenas,combined_item_id, Nome, Descricao, Resultado, Carregavel) VALUES
(9, 1, 1, 'Detetive_Don_Bigodon','Ele fica olhando a cena de crime de forma seria e cínica, parece até que nem pisca','Então Parece que essa capsula é de uma arma do calibre 9mm', 0);


SELECT i.Id_itens, i.Nome, i.Descricao FROM inventory inv JOIN items i ON inv.Id_item = i.Id_itens WHERE inv.Id_player = 1;

insert into inventory (inventory_id, Id_player, Id_item) values
(1,1,1),
(2,1,2);

update usuarios
set cena_id = 2
where Id_player = 1;


show tables from sqltextadventure;
select * from cenas;	 
select * from items;
select * from usuarios; 
select * from inventory;

drop database sqltextadventure
create database sqltextadventure
use sqltextadventure

drop table cenas;
drop table items;
drop table usuarios; 
drop table inventory;