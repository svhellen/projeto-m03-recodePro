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
    preco_passagem DECIMAL(10),
    data_passagem VARCHAR(15)
);

CREATE TABLE reserva (
    id_reserva INTEGER PRIMARY KEY auto_increment,
    fk_Passagem_id_passagem INTEGER,
    fk_Cliente_id_cliente INTEGER
);
 
ALTER TABLE reserva ADD CONSTRAINT FK_reserva_2
    FOREIGN KEY (fk_Passagem_id_passagem)
    REFERENCES Passagem (id_passagem)
    ON DELETE SET NULL;
 
ALTER TABLE reserva ADD CONSTRAINT FK_reserva_3
    FOREIGN KEY (fk_Cliente_id_cliente)
    REFERENCES Cliente (id_cliente)
    ON DELETE SET NULL;
    
select * from Cliente;
select * from Passagem;
select * from reserva;

insert into Cliente values 
(default, 'Maria', 'Maria@email.com', '1234', '99999999999'),
(default, 'João', 'Joao@email.com', '1234', '99999999999'),
(default, 'José', 'Jose@email.com', '1234', '99999999999'),
(default, 'Clara', 'Clara@email.com', '1234', '99999999999'),
(default, 'Bruno', 'Bruno@email.com', '1234', '99999999999')
;

insert into Cliente values 
(default, 'Cristiane', 'Cristiane@email.com', '1234', '99999999999'),
(default, 'Nathan', 'Nathan@email.com', '1234', '99999999999'),
(default, 'Lucas', 'Lucas@email.com', '1234', '99999999999'),
(default, 'Gustavo', 'Gustavo@email.com', '1234', '99999999999'),
(default, 'Melissa', 'Melissa@email.com', '1234', '99999999999'),
(default, 'Miguel', 'Miguel@email.com', '1234', '99999999999'),
(default, 'Arthur', 'Arthur@email.com', '1234', '99999999999'),
(default, 'Isabelle', 'Isabelle@email.com', '1234', '99999999999'),
(default, 'Ana', 'Ana@email.com', '1234', '99999999999'),
(default, 'Ronaldo', 'Ronaldo@email.com', '1234', '99999999999'),
(default, 'Kaylane', 'Kaylane@email.com', '1234', '99999999999'),
(default, 'Jeferson', 'Jeferson@email.com', '1234', '99999999999'),
(default, 'Annie', 'Annie@email.com', '1234', '99999999999'),
(default, 'Michel', 'Michel@email.com', '1234', '99999999999'),
(default, 'Anthony', 'Anthony@email.com', '1234', '99999999999'),
(default, 'Gabrielle', 'Gabrielle@email.com', '1234', '99999999999')
;

insert into Passagem values
(default, 'Primeira Classe', 'São Paulo - SP', 'Aracaju - SE', 386.00, '24-03-2024'),
(default, 'Executiva', 'São Paulo - SP', 'Recife - PE', 293.00, '13-08-2024'),
(default, 'Econômica', 'São Paulo - SP', 'Salvador - BA', 357.00, '28-05-2024'),
(default, 'Econômica', 'São Paulo - SP', 'Maceio - AL', 349.00, '06-11-2024'),
(default, 'Executiva', 'São Paulo - SP', 'Rio de Janeiro - RJ', 338.00, '24-03-2023'),
(default, 'Econômica', 'Rio de Janeiro - RJ', 'São Paulo - SP', 386.00, '24-03-2023'),
(default, 'Econômica', 'Recife - PE',  'São Paulo - SP',357.00, '24-11-2024'),
(default, 'Executiva', 'Salvador - BA', 'São Paulo - SP', 338.00, '28-03-2024'),
(default, 'Primeira Classe', 'Aracaju - SE', 'São Paulo - SP', 293.00, '13-05-2024'),
(default, 'Econômica', 'Maceio - AL', 'São Paulo - SP', 349.00, '06-08-2023')
;

alter table Passagem modify preco_passagem decimal(10,2);

insert into reserva values
(default, 5, 1),
(default, 6, 1),
(default, 9, 2),
(default, 9, 3),
(default, 1, 4),
(default, 10, 4)
;

select r.id_reserva, c.nome_cliente, p.origem_passagem, p.destino_passagem, p.classe_passagem, p.data_passagem, p.preco_passagem 
from reserva as r, Passagem as p, Cliente as c 
where r.fk_Passagem_id_passagem = p.id_passagem and r.fk_Cliente_id_cliente = c.id_cliente;

select r.id_reserva, c.nome_cliente, p.origem_passagem, p.destino_passagem, p.classe_passagem, p.data_passagem, p.preco_passagem 
from reserva as r 
join Passagem as p on r.fk_Passagem_id_passagem = p.id_passagem join Cliente as c on r.fk_Cliente_id_cliente = c.id_cliente;
