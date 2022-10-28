CREATE DATABASE sise /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- Tablas miscelaneas 
use sise;

CREATE TABLE province (
  id_province tinyint(1) NOT NULL,
  name_province varchar(10) DEFAULT NULL,
  zone_percentage tinyint(1) not null,
  PRIMARY KEY (id_province)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE canton (
  id_canton int NOT NULL AUTO_INCREMENT,
  id_province tinyint(1) NOT NULL,
  name_canton varchar(45) DEFAULT NULL,
  PRIMARY KEY (id_canton),
  FOREIGN KEY ( id_province)
	REFERENCES province(id_province)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE district (
  id_district int NOT NULL AUTO_INCREMENT,
  id_canton int DEFAULT NULL,
  name_district varchar(45) DEFAULT NULL,
  PRIMARY KEY (id_district),
  FOREIGN KEY ( id_canton)
	REFERENCES canton(id_canton)
    ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Bulk Insert

INSERT INTO province
(id_province, name_province, zone_percentage)
VALUES
(1,'San José', 1)
,(2,'Alajuela', 2)
,(3,'Cartago', 3)
,(4,'Heredia', 4)
,(5,'Guanacaste', 5)
,(6,'Puntarenas', 6)
,(7,'Limón', 7);

-- Cargar lista de cantones completa

INSERT INTO canton
(id_province, name_canton)
VALUES
(1,'San José')
,(1,'Escazú')
,(1, 'Montes de Oca')
,(1, 'Moravia')
,(1, 'Tibas')
,(1, 'Alajuelita')
,(1, 'Vasquez de Coronado')
,(1, 'Acosta')
,(1, 'Turrubares')
,(1, 'Dota')
,(1, 'Curridabat')
,(1, 'Perez Zeledon')
,(1, 'Leon Cortes Castro')
,(1, 'Desamparados')
,(1, 'Puriscal')
,(1, 'Tarrazu')
,(1, 'Aserri')
,(1, 'Mora')
,(1, 'Goicoechea')
,(1, 'Santa Ana')
,(2,'Alajuela')
,(2,'Grecia')
,(2,'San Mateo')
,(2,'Atenas')
,(2,'Naranjo')
,(2,'Palmares')
,(2,'Poás')
,(2,'Orotina')
,(2,'San Carlos')
,(2,'Alfaro Ruiz')
,(2,'Valverde Vega')
,(2,'Upala')
,(2,'Los Chiles')
,(2,'Guatuso')
,(2,'San Ramón')
,(3,'Cartago')
,(3,'Paraiso')
,(3,'La Unión')
,(3,'Jimenez')
,(3,'Turrialba')
,(3,'Alvarado')
,(3,'Oreamuno')
,(3,'El Guarco')
,(4,'Heredia')
,(4,'Barva')
,(4,'Santo Domingo')
,(4,'Santa Barbara')
,(4,'San Rafael')
,(4,'San Isidro')
,(4,'Belén')
,(4,'Flores')
,(4,'San Pablo')
,(4,'Sarapiquí')
,(5,'Liberia')
,(5,'Nicoya')
,(5,'Santa Cruz')
,(5,'Bagaces')
,(5,'Carrillo')
,(5,'Cañas')
,(5,'Abangares')
,(5,'Tilarán')
,(5,'Nandayure')
,(5,'La Cruz')
,(5,'Hojancha')
,(6,'Puntarenas')
,(6,'Esparza')
,(6,'Buenos Aires')
,(6,'Montes de Oro')
,(6,'Osa')
,(6,'Quepos')
,(6,'Golfito')
,(6,'Coto Brus')
,(6,'Parrita')
,(6,'Corredores')
,(6,'Garabito')
,(7,'Limón')
,(7,'Pococí')
,(7,'Siquirres')
,(7,'Talamanca')
,(7,'Matina')
,(7,'Guacimo')
,(2, 'Zarcero')
,(2, 'Sarchí')
,(2, 'Río Cuarto')
,(6, 'Monteverde')
,(6, 'Puerto Jimenez')
;

SELECT id_canton, name_canton
from canton
where id_province = 1;

-- Delete for LAB

Delete from distric
where name_district = 'Carmen';

Delete from distric
where id_district = 65;

-- Update for LAB
-- Si estamos seguros de que es seguro lo que vamos a cambiar ejecutamos la sentencia de abajo para desactivar la seguridad de WorkBench
SET SQL_SAFE_UPDATES = 0;

UPDATE Canton
SET name_canton = 'Aguirre'
WHERE name_canton = 'Quepos';

-- Visualizar el cambio

SELECT *
FROM canton
WHERE id_province = 6;

-- Volver a cambiar el valor 

UPDATE Canton
SET name_canton = 'Quepos'
WHERE name_canton = 'Aguirre';

-- Colocar nuevamente la seguridad de integridad de WorkBench
SET SQL_SAFE_UPDATES = 1;

-- Cargar lista de distritos completa

-- San Jose
INSERT INTO district
(id_canton, name_district)
VALUES
(1, 'Carmen')
,(1, 'Merced')
,(1, 'Hospital')
,(1, 'Catedral')
,(1, 'Zapote')
,(1, 'San Francisco de Dos Ríos')
,(1, 'La Uruca')
,(1, 'Mata Redonda')
,(1, 'Pavas')
,(1, 'Hatillo')
,(1, 'San Sebastián')
,(2, 'Escazú')
,(2, 'San Antonio')
,(2, 'San Rafael')
,(14, 'Desamparados')
,(14, 'San Miguel')
,(14, 'San Juan de Dios')
,(14, 'San Rafael Arriba')
,(14, 'San Antonio')
,(14, 'Frailes')
,(14, 'Patarrá')
,(14, 'San Cristóbal')
,(14, 'Rosario')
,(14, 'Damas')
,(14, 'San Rafael Abajo')
,(14, 'Gravilias')
,(14, 'Los Guido')
,(15, 'Santiago')
,(15, 'Mercedes Sur')
,(15, 'Barbacoas')
,(15, 'Grifo Alto')
,(15, 'San Rafael')
,(15, 'Candelarita')
,(15, 'Desamparaditos')
,(15, 'San Antonio')
,(15, 'Chires')
,(16, 'San Marcos')
,(16, 'San Lorenzo')
,(16, 'San Carlos')
,(17, 'Aserrí')
,(17, 'Tarbaca')
,(17, 'Vuelta de Jorco')
,(17, 'San Gabriel')
,(17, 'Legua')
,(17, 'Monterrey')
,(17, 'Salitrillos')
,(18, 'Colón')
,(18, 'Guayabo')
,(18, 'Tabarcia')
,(18, 'Piedras Negras')
,(18, 'Picagres')
,(18, 'Jaris')
,(18, 'Quitirrisí')
,(19, 'Guadalupe')
,(19, 'San Francisco')
,(19, 'Calle Blancos')
,(19, 'Mata de Plátano')
,(19, 'Ipís')
,(19, 'Rancho Redondo')
,(19, 'Purral')
,(20, 'Santa Ana')
,(20, 'Salitral')
,(20, 'Pozos')
,(20, 'Uruca')
,(20, 'Piedades')
,(20, 'Brasil')
,(6, 'Alajuelita')
,(6, 'San Josecito')
,(6, 'San Antonio')
,(6, 'Concepción')
,(6, 'San Felipe')
,(7, 'San Isidro')
,(7, 'San Rafael')
,(7, 'Dulce Nombre de Jesús')
,(7, 'Patalillo')
,(7, 'Cascajal')
,(8, 'San Ignacio')
,(8, 'Guaitil')
,(8, 'Palmichal')
,(8, 'Cangrejal')
,(8, 'Sabanillas')
,(5, 'San Juan')
,(5, 'Cinco Esquinas')
,(5, 'Anselmo llorente')
,(5, 'León XIII')
,(5, 'Colima')
,(4, 'San Vicente')
,(4, 'San Jerónimo')
,(4, 'La Trinidad')
,(3, 'San Pedro')
,(3, 'Sabanilla')
,(3, 'Mercedes')
,(3, 'San Rafael')
,(9, 'San Pablo')
,(9, 'San Pedro')
,(9, 'San Juan de Mata')
,(9, 'San Luis')
,(9, 'Carara')
,(10, 'Santa María')
,(10, 'Jardín')
,(10, 'Copey')
,(11, 'Curridabat')
,(11, 'Granadilla')
,(11, 'Sánchez')
,(11, 'Tirrases')
,(12, 'San Isidro de El General')
,(12, 'El General')
,(12, 'Daniel Flores')
,(12, 'Rivas')
,(12, 'San Pedro')
,(12, 'Platanares')
,(12, 'Pejibaye')
,(12, 'Cajón')
,(12, 'Barú')
,(12, 'Río Nuevo')
,(12, 'Páramo')
,(12, 'La Amistad')
,(13, 'San Pablo')
,(13, 'San Andrés')
,(13, 'Llano Bonito')
,(13, 'San Isidro')
,(13, 'Santa Cruz')
,(13, 'San Antonio')
;


SELECT id_canton, name_canton
from canton
where id_province = 2;

-- Alajuela
INSERT INTO district
(id_canton, name_district)
VALUES
(21, 'Alajuela')
,(21, 'San José')
,(21, 'Carrizal')
,(21, 'San Antonio')
,(21, 'Guácima')
,(21, 'San Isidro')
,(21, 'Sabanilla')
,(21, 'San Rafael')
,(21, 'Río Segundo')
,(21, 'Desamparados')
,(21, 'Turrúcares')
,(21, 'Tambor')
,(21, 'La Garita')
,(21, 'Sarapiquí')
,(35, 'San Ramón')
,(35, 'Santiago')
,(35, 'San Juan')
,(35, 'Piedades Norte')
,(35, 'Piedades Sur')
,(35, 'San Rafael')
,(35, 'San Isidro')
,(35, 'Ángeles')
,(35, 'Alfaro')
,(35, 'Volio')
,(35, 'Concepción')
,(35, 'Zapotal')
,(35, 'Peñas Blancas')
,(35, 'San Lorenzo')
,(22, 'Grecia')
,(22, 'San Isidro')
,(22, 'San José')
,(22, 'San Roque')
,(22, 'Tacares')
,(22, 'Puente de Piedra')
,(22, 'Bolívar')
,(23, 'San Mateo')
,(23, 'Desmonte')
,(23, 'Jesús María')
,(23, 'Labrador')
,(24, 'Atenas')
,(24, 'Jesús')
,(24, 'Mercedes')
,(24, 'San Isidro')
,(24, 'Concepción')
,(24, 'San José')
,(24, 'Santa Eulalia')
,(24, 'Escobal')
,(25, 'Naranjo')
,(25, 'San Miguel')
,(25, 'San José')
,(25, 'Cirrí Sur')
,(25, 'San Jerónimo')
,(25, 'San Juan')
,(25, 'El Rosario')
,(25, 'Palmitos')
,(26, 'Palmares')
,(26, 'Zaragoza')
,(26, 'Buenos Aires')
,(26, 'Santiago')
,(26, 'Candelaria')
,(26, 'Esquipulas')
,(26, 'La Granja')
,(27, 'San Pedro')
,(27, 'San Juan')
,(27, 'San Rafael')
,(27, 'Carrillos')
,(27, 'Sabana Redonda')
,(28, 'Orotina')
,(28, 'El Mastate')
,(28, 'Hacienda Vieja')
,(28, 'Coyolar')
,(28, 'La Ceiba')
,(29, 'Quesada')
,(29, 'Florencia')
,(29, 'Buenavista')
,(29, 'Aguas Zarcas')
,(29, 'Venecia')
,(29, 'Pital')
,(29, 'La Fortuna')
,(29, 'La Tigra')
,(29, 'La Palmera')
,(29, 'Venado')
,(29, 'Cutris')
,(29, 'Monterrey')
,(29, 'Pocosol')
,(82, 'Zarcero')
,(82, 'Laguna')
,(82, 'Tapesco')
,(82, 'Guadalupe')
,(82, 'Palmira')
,(82, 'Zapote')
,(82, 'Brisas')
,(83, 'Sarchí Norte')
,(83, 'Sarchí Sur')
,(83, 'Toro Amarillo')
,(83, 'San Pedro')
,(83, 'Rodríguez')
,(32, 'Upala')
,(32, 'Aguas Claras')
,(32, 'San José (Pizote)')
,(32, 'Bijagua')
,(32, 'Delicias')
,(32, 'Dos Ríos')
,(32, 'Yolillal')
,(32, 'Canalete')
,(33, 'Los Chiles')
,(33, 'Caño Negro')
,(33, 'El Amparo')
,(33, 'San Jorge')
,(34, 'San Rafael')
,(34, 'Buenavista')
,(34, 'Cote')
,(34, 'Katira')
,(84, 'Río Cuarto')
,(84, 'Santa Rita')
,(84, 'Santa Isabel')
;

SELECT id_canton, name_canton
from canton
where id_province = 3;

-- Cartago
INSERT INTO district
(id_canton, name_district)
VALUES
(36, 'Oriental')
,(36, 'Occidental')
,(36, 'Carmen')
,(36, 'San Nicolás')
,(36, 'Aguacaliente')
,(36, 'Guadalupe')
,(36, 'Corralillo')
,(36, 'Tierra Blanca')
,(36, 'Dulce Nombre')
,(36, 'Llano Grande')
,(36, 'Quebradilla')
,(37, 'Paraíso')
,(37, 'Santiago')
,(37, 'Orosi')
,(37, 'Cachí')
,(37, 'Llanos de Santa Lucía')
,(38, 'Tres Ríos')
,(38, 'San Diego')
,(38, 'San Juan')
,(38, 'San Rafael')
,(38, 'Concepción')
,(38, 'Dulce Nombre')
,(38, 'San Ramón')
,(38, 'Río Azul')
,(39, 'Juan Viñas')
,(39, 'Tucurrique')
,(39, 'Pejibaye')
,(39, 'La Victoria')
,(40, 'Turrialba')
,(40, 'La Suiza')
,(40, 'Peralta')
,(40, 'Santa Cruz')
,(40, 'Santa Teresita')
,(40, 'Pavones')
,(40, 'Tuis')
,(40, 'Tayutic')
,(40, 'Santa Rosa')
,(40, 'Tres Equis')
,(40, 'La Isabel')
,(40, 'Chirripó')
,(41, 'Pacayas')
,(41, 'Cervantes')
,(41, 'Capellades')
,(42, 'San Rafael')
,(42, 'Cot')
,(42, 'Potrero Cerrado')
,(42, 'Cipreses')
,(42, 'Santa Rosa')
,(43, 'El Tejar')
,(43, 'San Isidro')
,(43, 'Tobosi')
,(43, 'Patio de Agua')
;

SELECT id_canton, name_canton
FROM canton
WHERE id_province = 4;

-- Heredia
INSERT INTO district
(id_canton, name_district)
VALUES
(44, 'Heredia')
,(44, 'Mercedes')
,(44, 'San Francisco')
,(44, 'Ulloa')
,(44, 'Varablanca')
,(45, 'Barva')
,(45, 'San Pedro')
,(45, 'San Pablo')
,(45, 'San Roque')
,(45, 'Santa Lucía')
,(45, 'San José de la Montaña')
,(46, 'Santo Domingo')
,(46, 'San Vicente')
,(46, 'San Miguel')
,(46, 'Paracito')
,(46, 'Santo Tomás')
,(46, 'Santa Rosa')
,(46, 'Tures')
,(46, 'Pará')
,(47, 'Santa Bárbara')
,(47, 'San Pedro')
,(47, 'San Juan')
,(47, 'Jesús')
,(47, 'Santo Domingo')
,(47, 'Purabá')
,(48, 'San Rafael')
,(48, 'San Josecito')
,(48, 'Santiago')
,(48, 'Ángeles')
,(48, 'Concepción')
,(49, 'San Isidro')
,(49, 'San José')
,(49, 'Concepción')
,(49, 'San Francisco')
,(50, 'San Antonio')
,(50, 'La Ribera')
,(50, 'La Asunción')
,(51, 'San Joaquín')
,(51, 'Barrantes')
,(51, 'Llorente')
,(52, 'San Pablo')
,(52, 'Rincón de Sabanilla')
,(53, 'Puerto Viejo')
,(53, 'La Virgen')
,(53, 'Horquetas')
,(53, 'Llanuras del Gaspar')
,(53, 'Cureña')
;

SELECT id_canton, name_canton
FROM canton
WHERE id_province = 6;

-- Puntarenas
INSERT INTO district
(id_canton, name_district)
VALUES
(65, 'Puntarenas')
,(65, 'Pitahaya')
,(65, 'Chomes')
,(65, 'Lepanto')
,(65, 'Paquera')
,(65, 'Manzanillo')
,(65, 'Guacimal')
,(65, 'Barranca')
,(65, 'Isla del Coco')
,(65, 'Cóbano')
,(65, 'Chacarita')
,(65, 'Chira')
,(65, 'Acapulco')
,(65, 'El Roble')
,(65, 'Arancibia')
,(66, 'Espíritu Santo')
,(66, 'San Juan Grande')
,(66, 'Macacona')
,(66, 'San Rafael')
,(66, 'San Jerónimo')
,(66, 'Caldera')
,(67, 'Buenos Aires')
,(67, 'Volcán')
,(67, 'Potrero Grande')
,(67, 'Boruca')
,(67, 'Pilas')
,(67, 'Colinas')
,(67, 'Chánguena')
,(67, 'Biolley')
,(67, 'Brunka')
,(68, 'Miramar')
,(68, 'La Unión')
,(68, 'San Isidro')
,(69, 'Puerto Cortés')
,(69, 'Palmar')
,(69, 'Sierpe')
,(69, 'Bahía Ballena')
,(69, 'Piedras Blancas')
,(69, 'Bahía Drake')
,(70, 'Quepos')
,(70, 'Savegre')
,(70, 'Naranjito')
,(71, 'Golfito')
,(71, 'Guaycará')
,(71, 'Pavón')
,(72, 'San Vito')
,(72, 'Sabalito')
,(72, 'Aguabuena')
,(72, 'Limoncito')
,(72, 'Pittier')
,(72, 'Gutiérrez Braun')
,(73, 'Parrita')
,(74, 'Corredor')
,(74, 'La Cuesta')
,(74, 'Canoas')
,(74, 'Laurel')
,(75, 'Jacó')
,(75, 'Tárcoles')
,(75, 'Lagunillas')
,(85, 'Monteverde')
,(86, 'Puerto Jiménez')
;

SELECT id_canton, name_canton
FROM canton
WHERE id_province = 5;

-- Guana
INSERT INTO district
(id_canton, name_district)
VALUES
(54, 'Liberia')
,(54, 'Cañas Dulces')
,(54, 'Mayorga')
,(54, 'Nacascolo')
,(54, 'Curubandé')
,(55, 'Nicoya')
,(55, 'Mansión')
,(55, 'San Antonio')
,(55, 'Quebrada Honda')
,(55, 'Sámara')
,(55, 'Nosara')
,(55, 'Belén de Nosarita')
,(56, 'Santa Cruz')
,(56, 'Bolsón')
,(56, 'Veintisiete de Abril')
,(56, 'Tempate')
,(56, 'Cartagena')
,(56, 'Cuajiniquil')
,(56, 'Diriá')
,(56, 'Cabo Velas')
,(56, 'Tamarindo')
,(57, 'Bagaces')
,(57, 'La Fortuna')
,(57, 'Mogote')
,(57, 'Río Naranjo')
,(58, 'Filadelfia')
,(58, 'Palmira')
,(58, 'Sardinal')
,(58, 'Belén')
,(59, 'Cañas')
,(59, 'Palmira')
,(59, 'San Miguel')
,(59, 'Bebedero')
,(59, 'Porozal')
,(60, 'Las Juntas')
,(60, 'Sierra')
,(60, 'San Juan')
,(60, 'Colorado')
,(61, 'Tilarán')
,(61, 'Quebrada Grande')
,(61, 'Tronadora')
,(61, 'Santa Rosa')
,(61, 'Líbano')
,(61, 'Tierras Morenas')
,(61, 'Arenal')
,(61, 'Cabeceras')
,(62, 'Carmona')
,(62, 'Santa Rita')
,(62, 'Zapotal')
,(62, 'San Pablo')
,(62, 'Porvenir')
,(62, 'Bejuco')
,(63, 'La Cruz')
,(63, 'Santa Cecilia')
,(63, 'La Garita')
,(63, 'Santa Elena')
,(64, 'Hojancha')
,(64, 'Monte Romo')
,(64, 'Puerto Carrillo')
,(64, 'Huacas')
,(64, 'Matambú')
;

SELECT id_canton, name_canton
FROM canton
WHERE id_province = 7;

-- Limon
INSERT INTO district
(id_canton, name_district)
VALUES
(76, 'Limón')
,(76, 'Valle La Estrella')
,(76, 'Río Blanco')
,(76, 'Matama')
,(77, 'Guápiles')
,(77, 'Jiménez')
,(77, 'La Rita')
,(77, 'Roxana')
,(77, 'Cariari')
,(77, 'Colorado')
,(77, 'La Colonia')
,(78, 'Siquirres')
,(78, 'Pacuarito')
,(78, 'Florida')
,(78, 'Germania')
,(78, 'El Cairo')
,(78, 'Alegría')
,(78, 'Reventazón')
,(79, 'Bratsi')
,(79, 'Sixaola')
,(79, 'Cahuita')
,(79, 'Telire')
,(80, 'Matina')
,(80, 'Batán')
,(80, 'Carrandi')
,(81, 'Guácimo')
,(81, 'Mercedes')
,(81, 'Pocora')
,(81, 'Río Jiménez')
,(81, 'Duacarí')
;

-- Consultas --
-- Todas provincias
Select * from province;

-- Todos los cantones
Select * from canton;

-- Todos los distritos
Select * from district;

-- Todos los cantones de una provincia
Select * 
from canton 
where id_province = 1;

Select * 
from canton 
where id_province in (Select id_province
						from province 
                        where name_province = 'San José');
                        
Select * 
from canton 
where id_province = (Select id_province 
						from province 
                        where name_province = 'San José');

-- Todos los distritos de un cantón
Select * 
from district 
where id_canton = 1;


Select * 
from district 
where id_canton in (Select id_canton 
					from canton 
                    where name_canton = 'San José');
                    
                    
Select * 
from district 
where id_canton = (Select id_canton 
					from canton 
                    where name_canton = 'San José');

-- Todos los distritos de una provincia
Select * 
from district 
where id_canton in (Select id_canton 
					from canton 
					where id_province = 1);
                    
                    
Select * 
from district 
where id_canton in (Select id_canton 
					from canton 
                    where id_province = (Select id_province 
											from province
                                            where name_province = 'San José')
					);



-- Join de las 3 tablas

Select p.id_province, p.name_province, c.id_canton, c.name_canton, id_district, name_district
from province as p 
inner join canton as c  on p.id_province = c.id_province
inner join distrito as d on c.id_canton = d.id_canton;

Select name_province, nombre_canton, nombre_distrito
from province as p 
inner join canton as c  on p.id_province = c.id_province
inner join distrito as d on c.id_canton = d.id_canton;

Select p.id_province, p.name_province, c.id_canton, c.nombre_canton, id_distrito, nombre_distrito
from province as p 
inner join canton as c  on p.id_province = c.id_province
inner join distrito as d on c.id_canton = d.id_canton
Where p.id_province = 1;


Select p.id_province, p.name_province, c.id_canton, c.nombre_canton, id_distrito, nombre_distrito
from province as p 
left join canton as c  on p.id_provincia = c.id_province
left join distrito as d on c.id_canton = d.id_canton
Where p.id_province = 1;

select * from province cross join canton;

select * from province as p  left join canton as c on c.id_province = p.id_province;
select * from canton as c  left join proprovinciaprovinciaprovinciavincia as p on c.id_provincia = p.id_province;

select * from province as p  right join canton as c on c.id_province = p.id_province;
select * from canton as c  right join provincia as p on c.id_province = p.id_province;

-- CREANDO SUCURSAL Y EMPLEADOS
CREATE TABLE branch (
id_branch INT NOT NULL,
address VARCHAR(32) DEFAULT NULL,
coord_x INT DEFAULT NULL,
coord_y INT DEFAULT NULL,
id_district INT NOT NULL,
id_canton INT NOT NULL,
id_province tinyint(1) NOT NULL,
PRIMARY KEY (id_branch),
foreign key (id_district) 
	references district(id_district)
	ON UPDATE CASCADE ON DELETE RESTRICT,
foreign key (id_canton) 
	references canton(id_canton)
	ON UPDATE CASCADE ON DELETE RESTRICT,
foreign key (id_province) 
	references province(id_province)
	ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE employee(
id_employee INT NOT NULL,
name_employee VARCHAR(32) DEFAULT NULL,
phone_number VARCHAR(32) DEFAULT NULL,
base_salary DOUBLE DEFAULT NULL,
id_branch INT NOT NULL,
PRIMARY KEY (id_employee),
FOREIGN KEY (id_branch)
	REFERENCES branch(id_branch)
	ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

