$(document).ready(function(){
	console.log("Usao na profil pozorista.");
	
	var aktivni_korisnik = null;
	
console.log("Usao na profil bioskopa.");
	
	$.ajax({
		url:"../bioskopController/getLoggedUser",
		type:"GET",
		dataType:"json",
		success(data){
			if(data==null){
				console.log("Sesija ne postoji");
			}
			else{
				aktivni_korisnik = data;
			}
		}
	});
	
	$.ajax({
		url:"../pozoristeController/getSelectedPozoriste",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data == null){
				console.log("Data je null");
			}
			else{
				console.log("PISE DATA");
				console.log(data);
				
				getSale(aktivni_korisnik, data);
				
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Ne valja nesto.")
			console.log(textStatus);
		}
	});		
});

function getSale(aktivni_korisnik, data){
	
	var selectedPozoriste = data;
	
	$.ajax({
		
		url:"../pozoristeController/getSelectedPozoristeSale",
		type:"GET",
		dataType:"json",
		success:function(salaList){
			if(salaList==null){
				console.log("Pozoriste nema sala.");				
				getProjekcije(aktivni_korisnik, selectedPozoriste, salaList);
			}
			
			else{
				console.log("Pozoriste ima sale.");
				getProjekcije(aktivni_korisnik, selectedPozoriste, salaList);
			}
		}
		
	});
}

function getProjekcije(aktivni_korisnik, selectedBioskop, salaList){
	
	$.ajax({
		
		url:"../pozoristeController/getSelectedPozoristeProjekcije",
		type:"GET",
		dataType:"json",
		success:function(projekcijaList){
			if(projekcijaList==null){
				console.log("Pozoriste nema projekcijaList.");				
				ispisiProfil(aktivni_korisnik, selectedBioskop, salaList, projekcijaList);
			}
			
			else{
				console.log("Pozoriste ima projekcijaList.");
				ispisiProfil(aktivni_korisnik, selectedBioskop, salaList, projekcijaList);
			}
		}
		
	});
}

function ispisiProfil(aktivni_korisnik, data, salaList, projekcijaList){
	var korisnik = aktivni_korisnik;
	console.log("KORISNIK JE: ");
	console.log(korisnik);
	
	
	var options="";
	var projekcije="";
	var iterator_dugmad = 0;
	
	if(salaList!=null){
			
			$.each(salaList, function(index, value){
				var text="<option>"+value.naziv+"</option>";
				options += text;
			});			
			
	}
	
	var adminove_opcije=false;
	var top_dugmad="";
	var desno_dugme=""
	var ocena_opcije="";
	var vidljiv="style=\"visibility: hidden;\" ";
	
	if(korisnik==null){
		desno_dugme="<button style=\"float:right;\" id=\"login_dugme\">LogIn</button>";
	}
	
else if(korisnik.tipKorisnika=="ADMIN_OBJ"){
		
		adminove_opcije= true;
		
		top_dugmad=
			"<button onclick=\"location.href='editPozoriste.html'\" id=\"edit_button\">Edit</button>"+
			"<button onclick=\"location.href='dodaj_projekciju.html'\" id=\"edit_button\">Dodaj projekciju</button>";
	
		desno_dugme="<button style=\"float:right;\" id=\"logout_dugme\">LogOut</button>";
		
		 ocena_opcije=
			"<select id=\"oceni_select\">" +
				"<option>5</option>" +
				"<option>4</option>"+
				"<option>3</option>"+
				"<option>2</option>"+
				"<option>1</option>"+
			"</select>"+
			"<button id=\"oceni_dugme\">Oceni</button>";
	}
	
	else{
		desno_dugme="<button style=\"float:right;\" id=\"logout_dugme\">LogOut</button>";
		ocena_opcije=
			"<select id=\"oceni_select\">" +
				"<option>5</option>" +
				"<option>4</option>"+
				"<option>3</option>"+
				"<option>2</option>"+
				"<option>1</option>"+
			"</select>"+
			"<button id=\"oceni_dugme\">Oceni</button>";
		
		
	}
	//PROJEKCIJE
	
	$.each(projekcijaList, function(index,value){
		
		var adzxc="";
		if(adminove_opcije){
				adzxc = 
				"<button id=\"izmeni"+iterator_dugmad+" \""+">Izmeni</button>"+
				"<button id=\"obrisi"+iterator_dugmad+" \""+">Obrisi</button>";
		}
		
		
		var text= 
			
		"<div class=\"jedna_projekcija_div\">"+
			"<div class = \"jedna_projekcija_thumbnail\">"+			
			"</div>" +
			
			"<div class=\"projekcija_dugmad_div\">"+
				adzxc+
			"</div>"+					
			
			"<div id=\"jedna_projekcija_content"+iterator_dugmad+" \""+" class = \"jedna_projekcija_content\">"+
				"<div id=\"naslov_projekcije\" class=\"naslov_projekcije\">"+
					"<a href=\"/bioskopi/ProjekcijaFilma.html\">"+value.naziv+"</a>"+
					"<label class=\"labela_class\" id=\"id_label"+iterator_dugmad+" \" "+">"+value.id+"</label>"+
				"</div>"+
				"<div class=\"opis_projekcije\">"+
					"<p>"+ value.opis +"</p>"+
				"</div>"+
				"<div class=\"ocena_projekcije\">"+
					"<h4>Ocena:"+value.prosecna_ocena.toPrecision(3)+" Broj glasova: "+value.broj_glasova+" Cena:"+value.cena+" Sala:"+value.poz_sala.naziv+" Od: "+value.termin_od+" Do: "+value.termin_do+"</h4>"+
				"</div>"+
			"</div>"+			
		"</div>" +
		"<div class=\"razmak_projekcija_div\">" +
		"</div>";
		
		projekcije+=text;
		iterator_dugmad = iterator_dugmad+1; 
	});
		

	//ZAGLAVLJE	
		
	var top_text="<div class=\"top_div\">"+
	top_dugmad+
	ocena_opcije+
	desno_dugme+
	"</div>";
	
	var text=
		"<div class=\"header_div\">"+
			"<div class=\"image_div\">"+		
			"</div>"+
			"<div class=\"content_div\">"+
				"<div class=\"name_and_address_div\">"+
					"<div class=name_div>"+
						"<h1>"+data.naziv+"</h1>"+
					"</div>"+
					"<div class=address_div>"+
						"<h3>"+data.adresa+"</h3>"+
					"</div>"+
				"</div>"+
				"<div class=\"desc_div\">"+
					"<p>"+data.opis+"</p>"+
				"</div>"+
			"</div>"+
		"</div>"+
		
		"<div class=\"central_div\">"+		
			"<div class=\"tickets_div\">" +
				"<div class=\"tickets_div_text\">" +
					"<h3>Brza rezervacija</h3>" +
				"</div>" +
				"<div class=\"tickets_div_content\">" +
				"</div>"+
			"</div>"+
			"<div class=\"repertoar_div\">" +
				"<div class=\"repertoar_div_text\">" +
					"<h3>Repertoar</h3>" +
				"</div>" +
				"<div id =\"repertoar_content_div\" class=\"repertoar_content_div\">"+
				"</div>" +
			"</div>"+
		"</div>"+
		"<div class=\"sale_div\">" +
			"<div class=\"sale_div_text\">" +
				"<h3>Prikaz sala</h3>" +
			"</div>" +
			"<div class=\"combo_box_div\">" +
				"<select id=\"sale_select\">" +
				"</select>" +
			"</div>" +
			"<div class=\"jedna_sala_div\">" +
				"" +
			"</div>"+
		"</div>"+
		"<div class=\"stats_div\">"+
				
		"</div>";
		
		
		$("#body").append(top_text);
		$("#body").append(text);
		$("#sale_select").append(options);
		$("#repertoar_content_div").append(projekcije);
}


$(document).on('click','button',function(e) { 
	var button_id = e.target.id;
	if	(button_id.includes("obrisi")){
		console.log("Obrisi");
		button_id = button_id.replace("obrisi","");
		console.log(button_id);
		
		var id_labele = "id_label"+button_id;		
		var x=document.getElementById(id_labele).textContent;
		console.log("ID LABELE JE: " + x);
		obrisiProjekciju(x);
		
	}
	else if(button_id.includes("izmeni")){
		console.log("Izmeni");
		button_id = button_id.replace("izmeni","");
		console.log(button_id);
		
		var id_labele = "id_label"+button_id;		
		var x=document.getElementById(id_labele).textContent;
		console.log("ID LABELE JE: " + x);
		
		editujProjekciju(x);
	}
	else if(button_id.includes("oceni_dugme")){
		console.log("oceni");
		var x = $('#oceni_select :selected').text();
		oceniPozoriste(x);
	}
	else if(button_id.includes("logout_dugme")){
		logOut();
		top.location.href="../index.html";
	}
	else if(button_id.includes("login_dugme")){
		top.location.href="../login/login.html";
	}
});

function logOut(){
	
	$.ajax({
		url:"../korisnikController/odjava",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data){
				top.href="index.html"
			}
			else{
				console.log("Neuspesno unistena sesija.");
			}
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}
	});
}

function oceniPozoriste(ocena){
	var vr_ocene = JSON.stringify(ocena);
	
	$.ajax({
		
		url:"../pozoristeController/oceniPozoriste",
		type: "PUT",
		data: {id: vr_ocene},
		dataType:"json",
		contentType:"application/json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno obrisano");
			}
			else{
				console.log("Uspesno ocenjeno");
				top.location.href="/pozorista/profil_pozorista.html";
			}
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}		
	});
}

function obrisiProjekciju(id){
	var id_projekcije = JSON.stringify(id);
	
	$.ajax({
		url:"../pozoristeController/obrisiProjekciju",
		type: "DELETE",
		data:{id: id_projekcije},
		dataType:"json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno obrisano.");
			}
			else{
				top.location.href="/pozorista/profil_pozorista.html";
			}			
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}		
	});
}

function editujProjekciju(id){
	var id_projekcije = JSON.stringify(id);
	$.ajax({
		url:"../pozoristeController/setSelektovanuProjekciju",
		type:"POST",
		data:{id: id_projekcije},
		dataType: "json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno postavljeno.");
			}
			else{
				top.location.href="/pozorista/edit_projekcija.html";
			}
		}
	});
}

$(document).on('click','a',function(e) { 
	e.preventDefault();
	var id = e.target.closest("div").childNodes[1].textContent;
	console.log(id);
	findSelectedProjekcija(id);
});

function findSelectedProjekcija(id){
	var id_projekcije = JSON.stringify(id);
	$.ajax({
		url:"../pozoristeController/findProjekciju",
		type: "POST",
		data:{id: id_projekcije},
		dataType:"json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno obrisano.");
			}
			else{
				top.location.href="/pozorista/ProjekcijaPredstave.html";
			}			
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}		
	});
}