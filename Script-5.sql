CREATE TABLE Cenas (
    Id_cenas INT NOT NULL PRIMARY KEY,
    titulo VARCHAR(50),
    descricao VARCHAR(650)
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


INSERT INTO cenas (Id_cenas, titulo, descricao) VALUES
(0,'Introdução', 'No inicio do dia, você é chamado a uma cena de crime, a esposa do grande inventor Jhon Backflip pediu pelo socorro da policia ao encontrar o corpo ja sem vida de seu marido jogado em seu escritorio. Entrando na sala você vê algumas coisas que chamam a sua atenção, o "Corpo Do John", uma "Capsula De Municão Desconhecida", uma "Comoda Movida" e Marcas De "Pegadas De Lama" No Chão. As seguintes pessoas estão presente: O Detetive Don Bigodone(você). A Fachineira Joana Samambaia. O vizinho James Fubacake. O corpo do john já sem vida. A sua esposa Amelia Hotwhells. O xerife. E seu melhor amigo Willian Frontflip'),
(1,'O Armeiro', 'Ao entrar no estabelecimento, você se encontra com o dito cujo Armeiro conhecido também por Philip Gunsman'),
(2,'Delegacia', 'Estando na delegacia, chegou a hora da verdade. Quem é o verdadeiro culpado? você pode conversar com Willian, a esposa de Backflip, e Grassfield');

INSERT INTO itens (Id_itens, Id_Cenas, Nome, Descricao, Erro, Interagir, Carregavel) VALUES
(0, 0,'MUNICAO DESCONHECIDO' , 'Capsula de munição desconhecida', 'Parece que isso não pode ser usado assim', 'USE Capsula de munição desconhecida', 1),
(1, 0,'PEGADAS DE LAMA' , 'Essas pegadas batem com os sapatos do john ou seja ele só entrou em casa com os sapatos sujos, bem que estava chovendo ontem.', 'Creio que não esteja olhando da maneira certa', 'USE Pegada em Corpo Do Jhon', 0),
(2, 0,'COMODA MOVIDA' , 'Ela diz que cuida da limpeza da casa e que tinha retirado o móvel do lugar para limpar, porém esqueceu de colocar no lugar de volta.', 'Acredito que isso não funciona dessa forma', 'USE Comoda em Fachineira Joana Samambaia', 0),
(3, 0,'CORPO JOHN' , 'Tem um buraco de bala no colete mas não tem buraco de saída no terno, ou seja o john tirou o terno antes de ser baleado, provavelmente era alguém próximo pois ele se sentiu à vontade para tirar o terno.', 'Interessante, mas não acho que seja desse jeito', 'USE corpo de John', 1),
(4, 0,'PISTA VISITA' , 'Ele escutou a chegada de alguma visita tarde da noite ao John, apesar de não ter visto quem era. Ele também diz que se houve disparo ele não ouviu nada apesar de morar ao lado, possivelmente houve o uso de um silenciador.', 'Creio que não seja de modo que deve ser feito', 'USE James Fubacake', 1),
(5, 0,'PISTA ARMA DO CRIMA' , 'fale com o armeiro Philip Gunsman sobre isso, enquanto ele leva todo o pessoal para um interrogatorio na delegacia.', 'Não é sobre isso que o xerife deveria saber', 'USE pista arma do crime em xerife', 1),
(6, 1,'PISTA SUSPEITOS' , '', 'No fim das contas, esse não é quem queriamos', 'USE pista suspeitos', 1),
(7, 1,'MUNICAO 9MM' , 'Atualmente, uma pista crucial perante a cena do crime', 'Não parece que é pra ser usado dessa maneira', 'USE municao 9mm no Armeiro', 1)
(8, 2,'ACUSAR' , 'Willian frontflip fica indignado com tamanha acusação', 'você sente que está perto, mas falta algo', 'USE acusar Willian Frontflip', 0);

INSERT INTO pessoas (Id_pessoas, nome, Comando, Descricao, Erro) VALUES
(0, 'Detetive Don Bigodon', 'USE Capsula de munição desconhecida', 'Então Parece que essa capsula é de uma arma do calibre 9mm', 'Ele não quer ser importunado por coisas sem relevancias'),
(1, 'Willian Frontflip', 'USE acusação', 'Se não fosse o detetive encherido com esse bigode esnobe, nunca teriam me descoberto', 'Ele Parece suspeito mas não abre o bico'),
(2, 'Fachineira Joana Samambaia', 'USE comoda movida', 'eu só limpo os aposentos do Sr.Backflip, movi a comoda para limpar e logo em seguida coloquei no canto', 'ela não ta disposta a conversar com você'),
(3, 'Vizinho James Fubacake', 'USE conversar', 'Bem que notei a preseça de mais alguém vindo junto do John, se houve tiros, não consegui ouvir nada', 'ele ta caladão, mas parece que ta assustado'),
(4, 'Amelia Hotwhells', 'USE acusação', 'Eu não mataria meu próprio marido, ele era um homem perfeito, eu não tenho motivos para mata-lo', 'Ela demonstra grandes niveis de choque'),
(5, 'Philip Gunsman', 'USE municao 9mm', 'Só conheço dois sujeitos que tem esse tipo de munição, pode ser o Jhoseph Grassfield ou Willian Frontflip', 'Tente outra coisa com ele'),
(6, 'Jhoseph Grassfield', 'USE conversar', 'eu tenho essa arma mas isso não significa que eu sou culpado', 'Talvez não seja isso'),
(7, 'Xerife', 'USE conversar', 'Infelizmente sei tanto quanto você', 'ele parece que vai te auxiliar na investigação apenas tomando conta dos suspeitos');

	
show tables from textadventure;
select * from cenas;	 
select * from itens;
select * from pessoas; 
select * from usuarios; 

drop database sqltextadventure
create database sqltextadventure
use textadventure

drop table cenas;
drop table itens;
drop table pessoas;