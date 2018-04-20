INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('adminfan@gmail.com','adminfan' ,'adminfan','adminfan','adminfan','060000000','ADMIN_FAN',0,0,0,'BRONZANI');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('a@a','a' ,'a','a','a','011','ADMIN_FAN',1,0,0,'BRONZANI');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('r@r','r' ,'r','r','r','021','REGISTROVAN',0,0,0,'BRONZANI');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('s@s','s' ,'s','s','s','022','ADMIN_SIST',0,0,0,'BRONZANI');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('o@o','o' ,'o','o','o','022','ADMIN_OBJ',0,0,0,'BRONZANI');

INSERT INTO korisnik (email,sifra,ime, prezime,grad,telefon,tip_korisnika,bio_ulogovan,predefinisan,broj_poseta,status)
VALUES ('p@p','p' ,'p','p','p','022','ADMIN_SIST',0,1,0,'BRONZANI');


INSERT INTO fan_zona (naziv)
VALUES ('FANZONA');

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren,korisnik_id)
VALUES ('123','2011-11-11' ,'Oglas naziv1',1,'opis','nekijpg..','1',1,'3');


INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren,korisnik_id)
VALUES ('123','2011-11-11' ,'Oglas naziv2',0,'opis2','nekijpg..','1',0,'3');

INSERT INTO oglas (cena,datum,naziv,odobren, opis,slika,fan_zona_id,proveren,korisnik_id)
VALUES ('13','2011-11-11' ,'Oglas naziv3',0,'opis3','nekijpg..','1',0,'3');

INSERT INTO blagajna (naziv,fan_zona_id)
VALUES ('BlagajnaNaziv1','1');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id,rezervisan,korisnik_id)
VALUES ('132' ,'rekvizitNaziv1','opis1','nekijpg..','1',0,'2');


INSERT INTO rekvizit (cena,naziv, opis,slika,blagajna_id,rezervisan,korisnik_id)
VALUES ('222' ,'rekvizitNaziv2','opis2','nekijpg..','1',0,'2');

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop1','Stefana Milanovica 66, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop2','Jefimije Zivkovic 14, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop3','Aleksandra Lupica 33, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into bioskop (naziv, adresa, opis, pros_ocena, broj_glasova)
values ('Bioskop4','Jovice Cubrica 142, Novi Sad','Novi bioskop u gradu, 200 sala, besplatni prikazi prvih 300 godina.', 0,0);

insert into sala(naziv, bioskop_bioskop_id)
values ('Sala1','1');

insert into sala(naziv, bioskop_bioskop_id)
values ('Sala2','1');

insert into projekcija(naziv, zanr, ime_reditelja, trajanje, pros_ocena, broj_glasova, opis, cena, glumci, sala_sala_id, termin_od, termin_do)
values ('Dr.Strangelove','Komedija','Stenli Kubrik','1:30',0 , 0, 'Nakon sto jedan americki general samovoljno posalje nuklearni bombarder na SSSR, predsednik SAD i ambasador SSSR-a pokusavaju da zaustave taj avion.',
300.00,'Piter Stelers, Dzordz C. Scott','1','12:00','13:30');

insert into projekcija(naziv, zanr, ime_reditelja, trajanje, pros_ocena, broj_glasova, opis, cena, glumci, sala_sala_id, termin_od, termin_do)
values ('Ziveti','Drama','Akira Kurosava','2:33',0 , 0, 'Birokrata pokusava da nadje smisao u svom zivotu nakon sto otkrije da ima neizleciv rak',
350.00,'Takashi Shimura','2','13:00','15:33');


INSERT INTO bodovna_skala (bronzani_bod,srebrni_bod,zlatni_bod)
VALUES (2,3,4);


INSERT INTO ponuda (cena,status,oglas_id,korisnik_id)
VALUES ('132' ,'NISTA','1','3');

