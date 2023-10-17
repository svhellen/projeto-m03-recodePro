create database vivaviatravel
default character set utf8mb4
default collate utf8mb4_0900_ai_ci;

use vivaviatravel;

CREATE TABLE Cliente (
    id_cliente INTEGER PRIMARY KEY auto_increment,
    nome_cliente VARCHAR(40),
    email_cliente VARCHAR(80),
    senha_cliente VARCHAR(40),
    telefone_cliente VARCHAR(20)
); 

CREATE TABLE Passagem (
    id_passagem INTEGER PRIMARY KEY auto_increment,
    classe_passagem VARCHAR(40),
    origem_passagem VARCHAR(40),
    destino_passagem VARCHAR(40),
    data_passagem VARCHAR(15),
    preco_passagem DECIMAL(10,2)
);

CREATE TABLE reserva (
    id_reserva INTEGER PRIMARY KEY auto_increment,
    fk_Passagem_id INTEGER,
    fk_Cliente_id INTEGER
);
 
ALTER TABLE reserva ADD CONSTRAINT FK_reserva_2
    FOREIGN KEY (fk_Passagem_id)
    REFERENCES Passagem (id_passagem)
    ON DELETE SET NULL;
 
ALTER TABLE reserva ADD CONSTRAINT FK_reserva_3
    FOREIGN KEY (fk_Cliente_id)
    REFERENCES Cliente (id_cliente)
    ON DELETE SET NULL;
    
select * from Cliente;
select * from Passagem;
select * from reserva;
Select * from Reserva where fk_Cliente_id = 5; 

insert into Cliente(id_cliente, nome_cliente, email_cliente, senha_cliente, telefone_cliente) values 
(1, 'admin', 'admin@email.com', 'admin', ''),
(default, 'Maria', 'Maria@email.com', '1234', '99999999999'),
(default, 'João', 'Joao@email.com', '1234', '99999999999'),
(default, 'José', 'Jose@email.com', '1234', '99999999999'),
(default, 'Clara', 'Clara@email.com', '1234', '99999999999'),
(default, 'Bruno', 'Bruno@email.com', '1234', '99999999999')
;

insert into Passagem values
(default, 'Primeira Classe', 'São Paulo - SP', 'Aracaju - SE', '24-03-2024', 386.00),
(default, 'Executiva', 'São Paulo - SP', 'Recife - PE', '13-08-2024', 293.00),
(default, 'Econômica', 'São Paulo - SP', 'Salvador - BA', '28-05-2024', 357.00),
(default, 'Econômica', 'São Paulo - SP', 'Maceio - AL', '06-11-2024', 349.00),
(default, 'Executiva', 'São Paulo - SP', 'Rio de Janeiro - RJ', '24-03-2023', 338.00),
(default, 'Econômica', 'Rio de Janeiro - RJ', 'São Paulo - SP', '24-03-2023', 386.00),
(default, 'Econômica', 'Recife - PE',  'São Paulo - SP', '24-11-2024',357.00),
(default, 'Executiva', 'Salvador - BA', 'São Paulo - SP', '28-03-2024', 338.00),
(default, 'Primeira Classe', 'Aracaju - SE', 'São Paulo - SP', '13-05-2024', 293.00),
(default, 'Econômica', 'Maceio - AL', 'São Paulo - SP', '06-08-2023', 349.00)
;

alter table Passagem modify preco_passagem decimal(10,2);

insert into reserva values
(default, 5, 5),
(default, 6, 5),
(default, 9, 2),
(default, 9, 3),
(default, 1, 4),
(default, 10, 4)
;

select r.id_reserva, c.nome_cliente, p.origem_passagem, p.destino_passagem, p.classe_passagem, p.data_passagem, p.preco_passagem 
from reserva as r, Passagem as p, Cliente as c 
where r.fk_Passagem_id = p.id_passagem and r.fk_Cliente_id = c.id_cliente;

select r.id_reserva, c.nome_cliente, p.origem_passagem, p.destino_passagem, p.classe_passagem, p.data_passagem, p.preco_passagem 
from reserva as r 
join Passagem as p on r.fk_Passagem_id = p.id_passagem join Cliente as c on r.fk_Cliente_id = c.id_cliente
where r.fk_Cliente_id = 5;

select r.*, c.*, p.* 
from reserva as r 
join Passagem as p on r.fk_Passagem_id = p.id_passagem join Cliente as c on r.fk_Cliente_id = c.id_cliente
where r.fk_Cliente_id = 5;

use vivaviatravel;

select * from cliente;

select * from cliente where id_cliente = 5;

select * from passagem;

select * from passagem where destino_passagem like '%aracaj%';

select * from reserva;
