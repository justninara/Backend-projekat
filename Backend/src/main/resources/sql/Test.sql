DROP TABLE IF EXISTS kredit CASCADE;
DROP TABLE IF EXISTS tip_racuna CASCADE;
DROP TABLE IF EXISTS klijent CASCADE;
DROP TABLE IF EXISTS racun CASCADE;

DROP SEQUENCE IF EXISTS kredit_seq;
DROP SEQUENCE IF EXISTS tip_racuna_seq;
DROP SEQUENCE IF EXISTS klijent_seq;
DROP SEQUENCE IF EXISTS racun_seq;

CREATE TABLE kredit(
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    oznaka varchar(20) not null,
    opis varchar (500) not null );

CREATE TABLE tip_racuna(
	id integer NOT NULL,
    naziv VARCHAR(100) NOT NULL,
    oznaka varchar(20) not null,
    opis varchar (500) not null);

CREATE TABLE klijent (
	id integer NOT NULL,
    ime varchar (50) not null,
    prezime varchar (50) NOT NULL,
    broj_lk integer NOT NULL,
    kredit integer NOT NULL);

CREATE TABLE racun(
	id integer NOT NULL,
    naziv varchar(100) NOT NULL,
    oznaka varchar(20) not null,
    opis varchar (500) not null,
    tip_racuna integer not null,
    klijent integer not null);

ALTER TABLE kredit ADD CONSTRAINT PK_Kredit
	PRIMARY KEY(id);
ALTER TABLE tip_racuna ADD CONSTRAINT PK_Tipracuna
	PRIMARY KEY(id);
ALTER TABLE klijent ADD CONSTRAINT PK_Klijent
	PRIMARY KEY(id);
ALTER TABLE racun ADD CONSTRAINT PK_Racun
	PRIMARY KEY(id);

ALTER TABLE racun ADD CONSTRAINT FK_Racun_tipracuna
	FOREIGN KEY (tip_racuna) REFERENCES tip_racuna (id);
ALTER TABLE racun ADD CONSTRAINT FK_Racun_klijent
	FOREIGN KEY (klijent) REFERENCES klijent (id);
ALTER TABLE klijent ADD CONSTRAINT FK_Klijent_kredit
	FOREIGN KEY (kredit) REFERENCES kredit (id);

CREATE INDEX IDXFK_Racun_Tipracuna
	ON racun (tip_racuna);
CREATE INDEX IDXFK_Racun_Klijent
	ON racun (klijent);
CREATE INDEX IDXFK_Klijent_Kredit
	ON klijent (kredit);
	
CREATE SEQUENCE kredit_seq
INCREMENT 1;
CREATE SEQUENCE tip_racuna_seq
INCREMENT 1;
CREATE SEQUENCE klijent_seq
INCREMENT 1;
CREATE SEQUENCE racun_seq
INCREMENT 1;




INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(1, 'Gotovinski', 'GK', 'Gotovina se isplacuje na racun korisnika');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(4, 'Okvirni', 'OK', 'Po visini mjesecnih primanja korisnika');
INSERT INTO "kredit"("id", "naziv", "oznaka", "opis")
VALUES(2, 'Hipotekarni', 'HP', 'Kredit sa nekretninom kao osiguranjem');

INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(1, 'Studentski', 'ST', 'Studentski kredit bez kamate');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(2, 'Penzionerski', 'PZ', 'Penzionerski kredit bez kamate');
INSERT INTO "tip_racuna"("id", "naziv", "oznaka", "opis")
VALUES(3, 'Potrosacki', 'PT', 'Odobrava se za razlicite namjene');

INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES(1, 'Andrea', 'Gutai', '051032443','1');
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES(2, 'Biljana', 'Becic', '458645645','4');
INSERT INTO "klijent"("id", "ime", "prezime", "broj_lk", "kredit")
VALUES(3, 'Nina', 'Damjanovic', '123456789','2');