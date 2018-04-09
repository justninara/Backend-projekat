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



INSERT INTO "racun"("id", "naziv", "oznaka", "opis","tip racuna","klijent")

VALUES(1, 'Tekuci racun', 'TR', 'Racun za novcana sredstva za svakodnevnu upotrebu','1','2');

INSERT INTO "racun"("id", "naziv", "oznaka", "opis","tip racuna","klijent")

VALUES(2, 'Ziro racun', 'ZR', 'Racun za usmjeravanje brojnih prihoda','3','1');
INSERT INTO "racun"("id", "naziv", "oznaka", "opis","tip racuna","klijent")

VALUES(3, 'Stedni racun', 'SR', 'Stedni racun je racun bez ogranicenja broja uplata ili isplata','2','3');

