select * from produto
where QUANTIDADE =0 and  VALOR < 100 and UNIDADE= 'UN' and DESCRICAO like '%63%' 
order by QUANTIDADE

select *,(QUANTIDADE * VALOR) as Total from produto
where DESCRICAO not like '%Bola%'

select * from produto
where QUANTIDADE !=5

/*Exerc 1*/
select * from produto
where UNIDADE = "UN"

/*Exerc 2*/
select * from produto
where CODIGO_CLASSIFICACAO = 020 and QUANTIDADE >20

/*Exerc 3*/
select * from produto
where DESCRICAO like '%bala %' and QUANTIDADE >=6 /and CODIGO_CLASSIFICACAO =3/ 

/*Exerc 4*/
select * from produto
where DESCRICAO like '%martelo%' and CODIGO_CLASSIFICACAO =17
select * from CLASSIFICACAO

/*Exerc 5*/
select * from produto
where CODIGO_CLASSIFICACAO =2 and UNIDADE= 'CX' and QUANTIDADE <5

/*Exerc 6*/
select * from produto
where CODIGO_CLASSIFICACAO =2 and UNIDADE != 'CX' and QUANTIDADE >=10 and QUANTIDADE <=50

/*Exerc 7*/
select * from produto
where DESCRICAO like '%bola%' and CODIGO_CLASSIFICACAO =8 or DESCRICAO like '%camiseta%' and CODIGO_CLASSIFICACAO =21

/*Exerc 8*/
select * from produto
where UNIDADE= 'PCT' and CODIGO_CLASSIFICACAO =3
select * from CLASSIFICACAO

/*Exerc 9*/ 
select * from nomedatabeladeunidade
Where CODIGO_CLASSIFICACAO=6

select * from CLASSIFICACAO

/*Exerc 10*/
select * from produto
where QUANTIDADE >6 and QUANTIDADE <10

/*Exerc 11*/
select * from produto
where DESCRICAO like 'O%' and UNIDADE = 'L'

/*Exerc 12*/
select *,(QUANTIDADE * VALOR = 20) as valortotal  from produto
where CODIGO_CLASSIFICACAO = 015 and UNIDADE = 'CX'
