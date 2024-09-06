CREATE TABLE Cenas (
    Id_cenas INT NOT NULL PRIMARY KEY,
    titulo VARCHAR(50),
    descricao VARCHAR(600)
);

CREATE TABLE itens (
    Id_itens INT NOT NULL PRIMARY KEY,
    Id_cenas INT,
    Nome Varchar (100),
    Descricao VARCHAR(255),
    Erro VARCHAR(500),
    Interagir VARCHAR(100),
    Carregavel BOOLEAN,
    FOREIGN KEY (Id_cenas) REFERENCES Cenas(Id_cenas)
);


CREATE TABLE Pessoas (
    Id_pessoas INT NOT NULL PRIMARY KEY,
    nome VARCHAR(50),
    Comando VARCHAR(50),
    Descricao VARCHAR(500),
    Erro VARCHAR(500)
);


CREATE TABLE Login (
    Id_save INT NOT NULL PRIMARY KEY,
    Id_cenas INT,
    Id_itens INT,
    FOREIGN KEY (Id_itens) REFERENCES itens(Id_itens),
    FOREIGN KEY (Id_cenas) REFERENCES Cenas(Id_cenas)
);


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cena_id INT,
    FOREIGN KEY (cena_id) REFERENCES Cenas(Id_cenas)
);


INSERT INTO cenas(Id_cenas, titulo, descricao) VALUES
(0,'Introdução', 'No inicio do dia você é chamado a uma cena de crime, a esposa do grande invetor Jhon Backflip pediu pelo socorro da policia, ao encontrar o corpo ja sem vida de seu marido jogado em seu escritorio, ao entrar na sala você vê algumas coisas que chamam a sua atenção, o "Corpo Do John", uma "Capsula De Municão Desconhecida", uma "Comoda Movida" e Marcas De "Pegadas De Lama" No Chão. As seguintes pessoas estão presente: O Detetive Don Bigodone A Fachineira Joana Samambaia O vizinho James Fubacake O corpo do john já sem vida A sua esposa Amelia Hotwhells E seu melhor amigo Willian Frontflip'),
(1,'Armeiro', 'Ao entrar no estabelecimento, você se encontra com o dito cujo Armeiro conhecido também por'),
(2,'Acusção Final', 'Estando na delegacia, você pode conversar com Willian, a esposa de Backflip, e Grassfield');

INSERT INTO itens (Id_itens, Id_Cenas, Nome , Descricao, Erro, Interagir, Carregavel) VALUES
(0, 0,'MUNICAO DESCONHECIDO' , 'Capsula de munição desconhecida', 'Parece que isso não pode', 'USE Capsula de munição desconhecida', 1),
(1, 0,'PEGADAS DE LAMA' , 'Essas pegadas batem com os sapatos do john ou seja ele só entrou em casa com os sapatos sujos, bem que estava chovendo ontem.', 'ERROOOOOU', 'USE Pegada em Corpo Do Jhon', 0),
(2, 0,'COMODA MOVIDA' , 'Ela diz que cuida da limpeza da casa e que tinha retirado o móvel do lugar para limpar, porém esqueceu de colocar no lugar de volta.', 'ERROOOOOU', 'USE Comoda em Fachineira Joana Samambaia', 0),
(3, 0,'CORPO JOHN' , 'Tem um buraco de bala no colete mas não tem buraco de saída no terno, ou seja o john tirou o terno antes de ser baleado, provavelmente era alguém próximo pois ele se sentiu à vontade para tirar o terno.', 'ERROOOOOU', 'USE analisar corpo de John', 1),
(4, 0,'PISTA VISITA' , 'Ele escutou a chegada de alguma visita tarde da noite ao John, apesar de não ter visto quem era. Ele também diz que se houve disparo ele não ouviu nada apesar de morar ao lado, possivelmente houve o uso de um silenciador.', 'ERROOOOOU', 'USE James Fubacake', 1),
(5, 1,'MUNICAO 9MM' , 'Capsula de 9mm', 'balapoggers', 'USE bala no Armeiro', 1);

INSERT INTO pessoas (Id_pessoas, nome, Comando, Descricao, Erro) VALUES
(0, 'Detetive Don Bigodon', 'USE Capsula de munição desconhecida', 'Então Parece que essa capsula é de uma arma do calibre 9mm', 'Ele não quer ser importunado por coisas sem relevancias'),
(1, 'Willian Frontflip', 'USE acusação', 'Você não deveria se meter naquilo que você desconhece, olhar para a criatura é um convite a loucura', 'Ele Parece suspeito mas não abre o bico'),
(2, 'Fachineira Joana Samambaia', 'USE comoda movida', 'eu só limpo os aposentos do Sr.Backflip, movi a comoda para limpar e logo em seguida coloquei no canto', 'ela não ta segura de conversar com você'),
(3, 'Vizinho James Fubacake', 'USE conversar', 'Bem que notei a preseça de mais alguém vindo junto do John, se houve tiros, não consegui ouvir nada', 'ele ta caladão, mas parece que ta mais assustado'),
(4, 'Amelia Hotwhells', 'USE acusação', 'Eu não mataria meu próprio marido, ele era um homem perfeito, eu não tenho motivos para mata-lo', 'Ela demonstra grandes niveis de choque');

	
show tables from textadventure;
select * from cenas;	 
select * from itens;
select * from pessoas; 
select * from usuarios; 

drop database textadventure
create database textadventure
use textadventure

drop table cenas;
drop table itens;
drop table pessoas;