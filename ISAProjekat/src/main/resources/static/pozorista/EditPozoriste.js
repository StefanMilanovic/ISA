$(document).ready(function(){
	//console.log("Usao na profil bioskopa.");

	$.ajax({
		url:"../pozoristeController/getSelectedPozoriste",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data == null){
				console.log("Data je null");
			}
			else{
			//	console.log("PISE DATA");
			//	console.log(data);
				
				popuniData(data);
				
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Ne valja nesto.")
			console.log(textStatus);
		}
	});		
});

$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});

function popuniData(data){
	
	document.getElementById('id').value=data.id;
	document.getElementById('idd').value=data.id;
	document.getElementById('naziv').value=data.naziv;	
	document.getElementById('adresa').value = data.adresa;
	document.getElementById('opis').value = data.opis;
}

function izmeniBioskop(){
	
	var $form = $("#editujBioskop_froma");
	var data = getFormData($form);
	
	if(data.naziv=="" || data.adresa=="" || data.opis==""){
		alert("Molimo Vas popunite sva polja.");
		return;
	}
	else{
		var p = JSON.stringify(data);
		//console.log(data);
		
		$.ajax({
			url:"../pozoristeController/editPozoriste",
			type: "PUT",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(data){
				if(data){
					console.log("USPESNO IZMENJENO");
					top.location.href="../pozorista/profil_pozorista.html";
				}
				else{
					console.log("Doslo je do greske.");
					top.location.href="../pozorista/profil_pozorista.html";
				}
			},
			error: function (textStatus, errorThrown) {
				top.location.href="../pozorista/profil_pozorista.html";
				console.log(textStatus);				
			}
		});
	}
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}