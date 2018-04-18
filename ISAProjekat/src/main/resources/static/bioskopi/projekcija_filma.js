$(document).ready(function(){
	console.log("Usao na profil projekcije.");

	$.ajax({
		url:"../bioskopController/getSelectedProjekcijaProfil",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data == null){
				console.log("Data je null");
			}
			else{
				console.log("PISE DATA");
				console.log(data);
				
				ispisiPodatke(data);
				
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Ne valja nesto.")
			console.log(textStatus);
		}
	});		
});

function ispisiPodatke(data){
	
	console.log(data);
	
	var text = 
		
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
					"<h4>Ocena :"+data.prosecna_ocena+"</h4>"+
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
				"<div class=\"sale_div\"></div>"+
			"</div>"+
		"</div>";
	
	$("#body").append(text);
			
}