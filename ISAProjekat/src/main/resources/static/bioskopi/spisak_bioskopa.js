	$(document).on('click','a',function(e) { 
		e.preventDefault();
		var id = e.target.closest("div").childNodes[1].textContent;
		//console.log(id);
		getSelectedBiskop(id);
	});
	


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
									"<label class=\"labela\" id=\"key\">"+value.id+"</label>"+
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

function getSelectedBiskop(id){
	
	var id_data = JSON.stringify(id);
	
	$.ajax({
		url:"http://localhost:8099/bioskopController/findClickedBioskop",
		type:"POST",
		data:{id: id_data},
		dataType:"json",
		success:function(data){
			if(data==null){
				console.log("Data je null.");
			}
			else{
				console.log(data);
				top.location.href="/bioskopi/profil_bioskopa.html";
			}
		},
		
		error:function(textStatus, errorThrown){
			console.log(textStatus);
		}		
	});
	
	
}