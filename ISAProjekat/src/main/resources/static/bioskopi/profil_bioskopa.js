$(document).ready(function(){
	console.log("Usao na profil bioskopa.");

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
				
				getSale(data);
				
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Ne valja nesto.")
			console.log(textStatus);
		}
	});		
});

function getSale(data){
	
	var selectedBioskop = data;
	
	$.ajax({
		
		url:"../bioskopController/getSelectedBioskopSale",
		type:"GET",
		dataType:"json",
		success:function(salaList){
			if(salaList==null){
				console.log("Bioskop nema sala.");				
				getProjekcije(selectedBioskop, salaList);
			}
			
			else{
				console.log("Bioskop ima sale.");
				getProjekcije(selectedBioskop, salaList);
			}
		}
		
	});
}

function getProjekcije(selectedBioskop, salaList){
	
	$.ajax({
		
		url:"../bioskopController/getSelectedBioskopProjekcije",
		type:"GET",
		dataType:"json",
		success:function(projekcijaList){
			if(projekcijaList==null){
				console.log("Bioskop nema projekcijaList.");				
				ispisiProfil(selectedBioskop, salaList, projekcijaList);
			}
			
			else{
				console.log("Bioskop ima projekcijaList.");
				ispisiProfil(selectedBioskop, salaList, projekcijaList);
			}
		}
		
	});
}

function ispisiProfil(data, salaList, projekcijaList){
	var options="";
	var projekcije="";
	
	
	if(salaList!=null){
			
			$.each(salaList, function(index, value){
				var text="<option>"+value.naziv+"</option>";
				options += text;
			});			
			
	}
	
	$.each(projekcijaList, function(index,value){
		var text= 
			
		"<div class=\"jedna_projekcija_div\">"+
			"<div class = \"jedna_projekcija_thumbnail\">"+			
			"</div>" +
			
			"<div class=\"projekcija_dugmad_div\">"+
				"<button>Izmeni</button>"+
				"<button>Obrisi</button>"+
			"</div>"+					
			
			"<div class = \"jedna_projekcija_content\">"+
				"<div class=\"naslov_projekcije\">"+
					"<h4>"+ value.naziv +"</h4>"+
					"<label id=\"projekcija_label_id\">"+value.id+"</label>"
				"</div>"+
				"<div class=\"opis_projekcije\">"+
					"<p>"+ value.opis +"</p>"+
				"</div>"+
				"<div class=\"ocena_projekcije\">"+
					"<h4>Ocena:"+value.prosecna_ocena+" Cena:"+value.cena+" Sala:"+value.ime_sale+"</h4>"+
				"</div>"+
			"</div>"+			
		"</div>" +
		"<div class=\"razmak_projekcija_div\">" +
		"</div>";
		
		projekcije+=text;
	});
		

		
		
	var top_text="<div class=\"top_div\">"+
	"<button onclick=\"location.href='editBioskop.html'\" id=\"edit_button\">Edit</button>"+
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