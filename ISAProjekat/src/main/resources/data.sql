INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('adminfan@gmail.com','adminfan' ,'adminfan','adminfan','adminfan','060000000','ADMIN_FAN',0,0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('a@a','a' ,'a','a','a','011','ADMIN_FAN',1,0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('r@r','r' ,'r','r','r','021','REGISTROVAN',0,0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('s@s','s' ,'s','s','s','022','ADMIN_SIST',0,0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('o@o','o' ,'o','o','o','022','ADMIN_OBJ',0,0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan)
VALUES ('p@p','p' ,'p','p','p','022','ADMIN_SIST',0,1);


INSERT INTO fan_zona (naziv)
VALUES ('fanZona1');
INSERT INTO fan_zona (naziv)
VALUES ('fanZona2');

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren)
VALUES ('123','2011-11-11' ,'Oglas naziv1',1,'opis','nekijpg..','1',1);


INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren)
VALUES ('123','2011-11-11' ,'Oglas naziv2',0,'opis2','nekijpg..','1',0);

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren)
VALUES ('13','2011-11-11' ,'Oglas naziv3',0,'opis3','nekijpg..','2',0);

INSERT INTO blagajna (naziv)
VALUES ('BlagajnaNaziv1');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id)
VALUES ('132' ,'rekvizitNaziv1','opis1','nekijpg..','1');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id)
VALUES ('222' ,'rekvizitNaziv2','opis2','nekijpg..','1');

