$(document).ready(function(){
	//console.log("Usao na profil bioskopa.");

	$.ajax({
		url:"../bioskopController/getSelectedBioskop",
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
	
	//console.log("----------ISPISUJE POSLATI DATA-----------");
//	console.log(data);
	
	if(data.naziv=="" || data.adresa=="" || data.opis==""){
		alert("Molimo Vas popunite sva polja.");
		return;
	}
	else{
		var p = JSON.stringify(data);
		//console.log(data);
		
		$.ajax({
			url:"../bioskopController/editBioskop",
			type: "PUT",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(data){
				if(data){
					console.log("USPESNO IZMENJENO");
					top.location.href="../bioskopi/profil_bioskopa.html";
				}
				else{
					console.log("Doslo je do greske.");
					top.location.href="../bioskopi/profil_bioskopa.html";
				}
			},	
			error: function (textStatus, errorThrown) {
				console.log("NE VALJA NESTO");
				top.location.href="../bioskopi/profil_bioskopa.html";
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

$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});
