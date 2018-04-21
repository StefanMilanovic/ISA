var CHART =null;
var posecenje_data=null;
var global_chart=null;
var global_sala_list = null;
var global_sedista_list = null;


$(document).ready(function(){
	
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
		url:"../bioskopController/getSelectedBioskop",
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
	
	var selectedBioskop = data;
	console.log("USAO U GET SALE")
	
	$.ajax({
		
		url:"../bioskopController/getSelectedBioskopSale",
		type:"GET",
		dataType:"json",
		success:function(salaList){
			if(salaList==null){
				console.log("Bioskop nema sala.");				
				global_sala_list = salaList;
				getSedista(aktivni_korisnik, selectedBioskop, salaList);								
			}
			
			else{
				console.log("Bioskop ima sale.");
				global_sala_list = salaList;
				console.log(global_sala_list);
				getSedista(aktivni_korisnik, selectedBioskop, salaList);
			}
		}
		
	});
}

function getSedista(aktivni_korisnik, selectedBioskop, salaList){
	$.ajax({
		url:"../bioskopController/getSedista",
		type:"GET",
		dataType:"json",
		success:function(sedistaList){
			console.log("SEDISTA LIST");
			console.log(sedistaList);
			getProjekcije(aktivni_korisnik, selectedBioskop, salaList, sedistaList);
		}
	});
}

function getProjekcije(aktivni_korisnik, selectedBioskop, salaList, sedistaList){
	
	$.ajax({
		
		url:"../bioskopController/getSelectedBioskopProjekcije",
		type:"GET",
		dataType:"json",
		success:function(projekcijaList){
			if(projekcijaList==null){
				console.log("Bioskop nema projekcijaList.");				
				ispisiProfil(aktivni_korisnik, selectedBioskop, salaList, sedistaList, projekcijaList);
			}
			
			else{
				console.log("Bioskop ima projekcijaList.");
				ispisiProfil(aktivni_korisnik, selectedBioskop, salaList, sedistaList, projekcijaList);
			}
		}
		
	});
}

function ispisiProfil(aktivni_korisnik, data, salaList, sedistaList, projekcijaList){	
	var korisnik = aktivni_korisnik;
	console.log("KORISNIK JE: ");
	console.log(korisnik);
	
	var options="";
	var projekcije="";
	var iterator_dugmad = 0;
	var iterator_dugmad2 = 0;
	global_sedista_list = sedistaList;
	
	
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
				
		iterator_dugmad2=iterator_dugmad2 + 1;
		
		top_dugmad=
			"<button onclick=\"location.href='editBioskop.html'\" id=\"edit_button\">Edit</button>"+
			"<button onclick=\"location.href='dodaj_projekciju.html'\" id=\"edit_button\">Dodaj projekciju</button>" +
			"<button onclick=\"location.href='editujSalu.html'\" id=\"edit_button\">Edituj Salu</button>";
	
		desno_dugme="<button style=\"float:right;\" id=\"logout_dugme\">LogOut</button>"+
					"<button style=\"float:right;\" id=\"promeni_dugme\">PromeniPod</button>";
		
		
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
			"</div>"
			+					
			
			"<div id=\"jedna_projekcija_content"+iterator_dugmad+" \""+" class = \"jedna_projekcija_content\">"+
				"<div id=\"naslov_projekcije\" class=\"naslov_projekcije\">"+
					"<a href=\"/bioskopi/ProjekcijaFilma.html\">"+value.naziv+"</a>"+
					"<label class=\"labela_class\" id=\"id_label"+iterator_dugmad+" \" "+">"+value.id+"</label>"+
				"</div>"+
				"<div class=\"opis_projekcije\">"+
					"<p>"+ value.opis +"</p>"+
				"</div>"+
				"<div class=\"ocena_projekcije\">"+
					"<h4>Ocena:"+value.prosecna_ocena.toPrecision(3)+" Broj glasova: "+value.broj_glasova+" Cena:"+value.cena+" Sala:"+value.sala.naziv+" Od: "+value.termin_od+" Do: "+value.termin_do+"</h4>"+
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
				"<div id=\"tickets_div_content\" class=\"tickets_div_content\">" +
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
				"<select onChange=\"ispisiSedista()\" id=\"sale_select\">" +
				"</select>" +
			"</div>" +
			"<div class=\"jedna_sala_div\">" +
				"<div class=\"vip_zona\">" +
					"<form id=\"vip_zona_form_id\">"+
						
					"</form>"+
				"</div>" +
				"<div class=\"normalna_zona\">" +
					"<form id=\"normalna_zona_form_id\">"+
				
					"</form>"+
				"</div>"+
			"</div>"+
		"</div>"+
		"<div  id =\"stats_div\" class=\"stats_div\">"+
			"<div class =\"graff_div\">" +
				"<canvas id=\"myChart\"></canvas>"+
			"</div>"+
			"<div class =\"graf_opcije_div\">" +
				"<p>Prosecna ocena: " +data.prosecna_ocena+"</br>Ukupan prihod: "+data.ukupan_prihod+"</br></br></p>"+	
				"<select id=\"graf_opcija\" onChange=\"podesiGraf()\">" +
					"<option>Po danima</option>" +
					"<option>Po nedeljama</option>" +
					"<option>Po mesecima</option>" +
				"</select>"+
			"</div>"+
		"</div>";
		var ctx = $("#myChart");
		console.log("GRAAAAAAAAAAAAAAAAAAAAAAAF");
		console.log(ctx);
		CHART=ctx;
		
		$("#body").append(top_text);
		$("#body").append(text);
		$("#sale_select").append(options);
		$("#repertoar_content_div").append(projekcije);
		
		pronadjiMesece(data);
		ispisiSedista();
		findKarte();
		
}

function findKarte(){
	$.ajax({
		url:"../bioskopController/nadjiKarte",
		type:"GET",
		dataType:"json",
		success:function(data){
			console.log(data);
			ispisiKarte(data);
		}
		
	});
}

function ispisiKarte(karte){
	
	var iterator_karte = 0;
	
	$.each(karte,function(index,value){	
		
		if(value.korisnik==null){
			var text =""
				var naziv = value.projekcija.naziv;
				console.log(naziv);
				
				//"<div id=\"jedna_projekcija_content"+iterator_dugmad+" \""+" class = \"jedna_projekcija_content\">"+
				
				text=text+
				"<div class=\"karta\">" +
				
				"<label id=\"sifra"+iterator_karte+" \""+" style=\"visibility:hidden;\">"+value.id+"</label>"  +
				"<p>"+naziv+" od:"+value.projekcija.termin_od+" do: "+value.projekcija.termin_do+" "+value.projekcija.sala.naziv+" Sediste: "+value.sediste.id+"Cena: "+value.projekcija.cena+" Popust:10%</p>" +
				"<button id=\"rezervisi"+iterator_karte+" \""+">Rezervisi</button"+
				
				"</div>";
				$("#tickets_div_content").append(text);
				iterator_karte = iterator_karte + 1;
		}
		
	});
	
}

function ispisiSedista(){
	var x = $('#sale_select :selected').text();
	var disabled="";
	
	 document.getElementById("vip_zona_form_id").innerHTML = "";
	 document.getElementById("normalna_zona_form_id").innerHTML = "";
	
	console.log(x);
	console.log(global_sala_list);
	
	$.each(global_sala_list, function(index,value){
		if(x.localeCompare(value.naziv)==0){
			if(value.vip_enabled){
				disabled="enabled";
			}
			else{
				disabled="disabled";
			}
		}
	});
	
	var iterator_vip=0;
	var iterator_normal = 0;
	
	var text_vip ="";
	var text_normal="";
	
	
	
	$.each(global_sedista_list, function(key,value){
		if(x.localeCompare(key)==0){
			console.log(global_sedista_list)
			$.each(value, function(index,vrednost){
				iterator_vip = iterator_vip+1;
				if(vrednost.vip){
					if(iterator_vip == 10){
						iterator_vip = 0;
						text_vip = text_vip +
						"<input type=\"radio\"><br>";
					}
					else{
						text_vip = text_vip +
						"<input type=\"radio\" "+disabled+" >";
					}					
				}
				else{
					iterator_normal = iterator_normal+1;
					if(iterator_normal == 10){
						iterator_normal = 0;
						text_normal= text_normal+
						"<input type=\"radio\"><br>";
					}
					else{
						text_normal= text_normal+
						"<input type=\"radio\">";
					}
				}
			});
		}
	});
	
	$("#vip_zona_form_id").append(text_vip);
	$("#normalna_zona_form_id").append(text_normal);
	
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
		oceniBioskop(x);
	}
	else if(button_id.includes("logout_dugme")){
		logOut();
		top.location.href="../index.html";
	}
	else if(button_id.includes("login_dugme")){
		top.location.href="../login/login.html";
	}
	else if(button_id.includes("rezervisi")){
		button_id = button_id.replace("rezervisi","");
		console.log(button_id);	
		
		var id_labele = "sifra"+button_id;
		console.log(id_labele);
		var x=document.getElementById(id_labele).textContent;
		console.log("ID LABELE JE: " + x);
		rezervisiKartu(x);
	}
	else if(button_id.includes("promeni_dugme")){
		top.location.href="../login/AdminAzuriraj.html";
	}
});

function rezervisiKartu(x){
	$.ajax({
		url:"../bioskopController/rezervisiKartu",
		type: "PUT",
		data:{id : x},
		contentType:"application/json",
		dataType:'json',
		success:function(data){
			top.location.href="../bioskopi/profil_bioskopa.html";
		},
		error(){
			top.location.href="../bioskopi/profil_bioskopa.html";
		}
		
	});
}

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

function oceniBioskop(ocena){
	var vr_ocene = JSON.stringify(ocena);
	
	$.ajax({
		
		url:"../bioskopController/oceniBioskop",
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
				top.location.href="/bioskopi/profil_bioskopa.html";
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
		url:"../bioskopController/obrisiProjekciju",
		type: "DELETE",
		data:{id: id_projekcije},
		dataType:"json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno obrisano.");
			}
			else{
				top.location.href="/bioskopi/profil_bioskopa.html";
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
		url:"../bioskopController/setSelektovanuProjekciju",
		type:"POST",
		data:{id: id_projekcije},
		dataType: "json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno postavljeno.");
			}
			else{
				top.location.href="/bioskopi/edit_projekcija.html";
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
		url:"../bioskopController/findProjekciju",
		type: "POST",
		data:{id: id_projekcije},
		dataType:"json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno obrisano.");
			}
			else{
				top.location.href="/bioskopi/ProjekcijaFilma.html";
			}			
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}		
	});
}

function pronadjiMesece(selectedBioskop){	
	$.ajax({
		url:"../bioskopController/getMesece",
		type:"get",
		dataType:"json",
		success:function(data){
			if(data!=null){
				posecenje_data = data;
				napraviGraf(posecenje_data,"Po danima");
			}
			else{
				console.log("Data je null.");
			}
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}
	});
}

function podesiGraf(){
	var x = $('#graf_opcija :selected').text();
	console.log(x);
	global_chart.destroy();
	napraviGraf(posecenje_data ,x);
}

function napraviGraf(data,nacin){
	console.log(data);
	
	var labels=["Februar", "Mart", "April"];
	var daniF=[]
	var daniM=[]
	var daniA=[]
	
	var brojPosetaF=0;
	var brojPosetaM=0;
	var brojPosetaA=0;
	
	var dnevenePoseteFebruar = [];
	var dnevnePoseteMart = [];
	var dnevnePoseteApril=[];
	
	var nedeljnePosete=[];
	var nedeljniBrojac=0;
	
	$.each(data, function(key,value){
		
	if(key.localeCompare("April")==0){
		$.each(value, function(index, vrednost){
			daniA.push(vrednost.broj_dana);
			dnevnePoseteApril.push(vrednost.broj_poseta_bio);
			brojPosetaA = brojPosetaA + vrednost.broj_poseta_bio;
			
			nedeljniBrojac = nedeljniBrojac +vrednost.broj_poseta_bio;
			if(index==6 || index ==13 || index ==20 || index ==27 || index == 29) {
				nedeljnePosete.push(nedeljniBrojac);
				nedeljniBrojac = 0;
			}
		});
	}
	else if (key.localeCompare("Mart")==0){
		$.each(value, function(index, vrednost){
			daniM.push(vrednost.broj_dana);
			dnevnePoseteMart.push(vrednost.broj_poseta_bio);
			brojPosetaM = brojPosetaM + vrednost.broj_poseta_bio;
		});
	}
	else{
		$.each(value, function(index, vrednost){
			daniF.push(vrednost.broj_dana);
			dnevenePoseteFebruar.push(vrednost.broj_poseta_bio);
			brojPosetaF = brojPosetaF + vrednost.broj_poseta_bio;
		});
	}
	});	

	var final_lables=[];
	var final_data=[];
	
	if(nacin.localeCompare("Po danima")==0){
		final_lables = daniA;
		final_data = dnevnePoseteApril;
	}else if(nacin.localeCompare("Po mesecima")==0){
		final_lables.push("Februar");
		final_lables.push("Mart");
		final_lables.push("April");
		final_data = [brojPosetaF, brojPosetaM, brojPosetaA];
	}
	else{
		final_lables.push("1-7");
		final_lables.push("8-14");
		final_lables.push("16-7");
		final_lables.push("15-21");
		final_lables.push("22-28");
		final_lables.push("28-30");
		final_data = nedeljnePosete;
	}
	var ctx = document.getElementById("myChart");
	global_chart = new Chart(ctx, {
	    type: 'bar',
	    data: {
	        labels: final_lables,
	        datasets: [{
	            label: 'Broj posetilaca',
	            data: final_data,
	            backgroundColor: [
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)',
	                'rgba(255, 99, 132, 0.2)',
	                'rgba(54, 162, 235, 0.2)',
	                'rgba(255, 206, 86, 0.2)',
	                'rgba(75, 192, 192, 0.2)',
	                'rgba(153, 102, 255, 0.2)',
	                'rgba(255, 159, 64, 0.2)',
	                'rgba(128, 186, 103, 0.2)'
	                
	                
	                
	            ],
	            borderColor: [
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)',
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)',
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)',
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)',
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)',
	                'rgba(255,99,132,1)',
	                'rgba(54, 162, 235, 1)',
	                'rgba(255, 206, 86, 1)',
	                'rgba(75, 192, 192, 1)',
	                'rgba(153, 102, 255, 1)',
	                'rgba(255, 159, 64, 1)',
	                'rgba(128, 186, 103, 1)'
	            ],
	            borderWidth: 1
	        }]
	    },
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    beginAtZero:true
	                }
	            }]
	        }
	    }
	});
}