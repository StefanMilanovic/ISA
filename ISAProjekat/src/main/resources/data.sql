INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan)
VALUES ('adminfan@gmail.com','adminfan' ,'adminfan','adminfan','adminfan','060000000','ADMIN_FAN',0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan)
VALUES ('a@a','a' ,'a','a','a','011','ADMIN_FAN',1);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan)
VALUES ('r@r','r' ,'r','r','r','021','REGISTROVAN',0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan)
VALUES ('s@s','s' ,'s','s','s','022','ADMIN_SIST',0);

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan)
VALUES ('o@o','o' ,'o','o','o','022','ADMIN_OBJ',0);

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

INSERT INTO blagajna (naziv)
VALUES ('BlagajnaNaziv1');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id)
VALUES ('132' ,'rekvizitNaziv1','opis1','nekijpg..','1');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id)
VALUES ('222' ,'rekvizitNaziv2','opis2','nekijpg..','1');

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop1','Stefana Milanovica 66, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop2','Jefimije Zivkovic 14, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop3','Aleksandra Lupica 33, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop4','Jovice Cubrica 142, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into sala(naziv, bioskop)
values ('Sala1','1');

insert into sala(naziv, bioskop)
values ('Sala2','1');

