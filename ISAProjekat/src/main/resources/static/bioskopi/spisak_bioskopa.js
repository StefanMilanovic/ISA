$(document).ready(function(){
	console.log("Dokument spreman.");
	
	$.ajax({
		url:"http://localhost:8099/bioskopController/getBioskopi",
		type:"GET",
		dataType:"json",
		success: function(data){
			//console.log(data);
			$.each(data, function(index, value){
				console.log(value);
				var text=
					"<div class=\"bioskop_div\">" +
						"<div class=\"thumbnail_div\">" +
						
						"</div>" +
						"<div class=\"podaci_div\">"+
							"<div class=\"naziv_div\">"+
								"<div class=\"ime_div\">"+
									"<a href=\"/bioskopi/profil_bioskopa.html\">"+value.naziv+"</a>"+
								"</div>"+
								"<div class=\"adresa_div\">"+
									"<h5>"+value.adresa+"</h5>"+
								"</div>"+
							"</div>"+
							"<div class=\"opis_div\">"+
								"<p>"+value.opis+"</p>"+
							"</div>"+					
							"<div class=\"ocena_div\">"+
								"<h5>Prosecna ocena:"+value.prosecna_ocena+"</h5>"+
							"</div>"+
							"</div>"+
						
					"</div>"+
				"<div class=\"razmak_div\"></div>";
				$("#container_div").append(text);
			});
		},
		error:function(textStatus,errorThrown){
			console.log("Ne valja nesto");
			console.log(textStatus);
		}
	});
});