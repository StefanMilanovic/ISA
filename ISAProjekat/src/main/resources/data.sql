INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika)
VALUES ('adminfan@gmail.com','adminfan' ,'adminfan','adminfan','adminfan','060000000','ADMIN_FAN');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika)
VALUES ('a@a','a' ,'a','a','a','0','ADMIN_FAN');

INSERT INTO fan_zona (naziv)
VALUES ('fanZona1');
INSERT INTO fan_zona (naziv)
VALUES ('fanZona2');

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id)
VALUES ('123','2011-11-11' ,'Oglas naziv1',1,'opis','nekijpg..','1');


INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id)
VALUES ('123','2011-11-11' ,'Oglas naziv2',1,'opis2','nekijpg..','1');

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id)
VALUES ('13','2011-11-11' ,'Oglas naziv3',0,'opis3','nekijpg..','2');


