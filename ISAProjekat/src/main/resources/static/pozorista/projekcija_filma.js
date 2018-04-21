$(document).ready(function(){
	console.log("Usao na profil projekcije.");
	
	var aktivni_korisnik=null;
	
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
		url:"../pozoristeController/getSelectedProjekcijaProfil",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data == null){
				console.log("Data je null");
			}
			else{
				console.log("PISE DATA");
				console.log(data);
				
				ispisiPodatke(aktivni_korisnik, data);
				
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Ne valja nesto.")
			console.log(textStatus);
		}
	});		
});

function ispisiPodatke(aktivni_korisnik, data){
	
	console.log(data);
	console.log("AKTIVNI KORISNIK");
	console.log(aktivni_korisnik)
	
	var zaglavlje = "";
	if(aktivni_korisnik!=null){
		zaglavlje=
		"<select id=\"oceni_select\">" +
			"<option>5</option>" +
			"<option>4</option>" +
			"<option>3</option>" +
			"<option>2</option>" +
			"<option>1</option>" +
		"</select>" +
		"<button onclick=\"glasajProjekcija()\">Glasaj</button>"+
		"<button style=\"float: right;\" onclick=\"logOut()\">LogOut</button>";
	}
	else{
		zaglavlje = "<button style=\"float: right;\" onclick=\"location.href='../login/login.html'\">LogIn</button>";
	}
	
	var text = 
		"<div class=\"top_zaglavlje_div\">" +
			zaglavlje+
		"</div>"+
		"<div class=\"slika_div\"></div>"+
		"<div class=\"content_div\">"+
			"<div class=\"informacije_div\">"+
				"<div class=\"naziv_div\">"+
					"<h3>"+data.naziv+"</h3>"+
				"</div>"+
				"<div class=\"info_div\">"+
					"<h4>Zanr: "+data.zarn+"</h4>"+
				"</div>"+
				"<div class=\"info_div\">"+
					"<h4>Reditelj: "+data.ime_reditelja+"</h4>"+
				"</div>"+
				"<div class=\"info_div\">"+
					"<h4>Trajanje: "+data.trajanje+"</h4>"+
				"</div>"+
				"<div class=\"info_div\">"+
					"<h4>Ocena :"+data.prosecna_ocena.toPrecision(3)+"</h4>"+
				"</div>"+
				"<div class=\"info_div\">"+
					"<h4>Glumci:</h4>"+
				"</div>"+
				"<div class=\"glumci_div\">"+
					"<p>"+data.spisak_glumaca+"</p>"+
				"</div>"+
			"</div>"+
			"<div class=\"opis_i_sale_div\">"+
				"<div class=\"opis_div\">"+
					"<p>"+data.opis+"</p>"+
				"</div>"+
				"<div id=\"sale_div\" class=\"sale_div\"></div>"+
			"</div>"+
		"</div>";
	
	$("#body").append(text);
	
	nadjiSveSlicne();
}

function glasajProjekcija(){
	var x = $('#oceni_select :selected').text();
	var vr_ocene = JSON.stringify(x);
	
	$.ajax({
		url:"../bioskopController/oceniProjekciju",
		type:"PUT",
		data:{id: vr_ocene},
		dataType:"json",
		contentType:"application/json",
		success:function(data){
			if(data==null){
				console.log("Neuspesno ocenjivanje");
			}
			else{
				console.log("Uspesno ocenjivanje");
				top.location.href="/bioskopi/ProjekcijaFilma.html"
			}
		},
		error:function(textStatus,errorThrown){
			console.log(textStatus);
		}
	})
}

function logOut(){
	$.ajax({
		url:"../korisnikController/odjava",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data){
				top.location.href="../index.html"
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

function nadjiSveSlicne(){
	$.ajax({
		url:"../pozoristeController/getSlicneProjekcije",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data!=null){
				console.log("Vratio data uspesno.");
				var text="";					
				$.each(data,function(index,value){
					text+="<p>Sala: "+value.poz_sala.naziv+", Termin od: "+value.termin_od+", Termin do: "+value.termin_do+", Cena:"+value.cena+"</p></br>";
					console.log(value);
				});
				$("#sale_div").append(text);
			}
			else{
				console.log("Vratio je null.");
			}
		},
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}
	});
}