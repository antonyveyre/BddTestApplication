/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  user
 * Created: 10 nov. 2017
 */

CREATE TABLE PERSONNE

(

id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),

prenom VARCHAR(20) NOT NULL,

nom VARCHAR(30),

genrem boolean,

age INTEGER,

CONSTRAINT primary_key PRIMARY KEY (id)

) ;

SELECT * FROM APP.PERSONNE FETCH FIRST 100 ROWS ONLY;


INSERT INTO APP.PERSONNE (PRENOM, NOM, GENREM, AGE) 
	VALUES ('Veyre', 'Antony', true, 34);
INSERT INTO APP.PERSONNE (PRENOM, NOM, GENREM, AGE) 
	VALUES ('Veyre', 'Antony', true, 34);
INSERT INTO APP.PERSONNE (PRENOM, NOM, GENREM, AGE) 
	VALUES ('Serge', 'Veyre', true, 35);
INSERT INTO APP.PERSONNE (PRENOM, NOM, GENREM, AGE) 
	VALUES ('Serge', 'Ivanov', true, 35);
INSERT INTO APP.PERSONNE (PRENOM, NOM, GENREM, AGE) 
	VALUES ('Serge', 'Ivanov', true, 35);
