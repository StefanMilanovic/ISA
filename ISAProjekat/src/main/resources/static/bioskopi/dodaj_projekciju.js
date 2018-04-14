function getFormData($form){
	var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}



function dodajProjekciju(){
	var $form = $("#dodajprojekciju_froma");
	var data= getFormData($form);

	console.log(data.naziv);
	
	if(data.naziv=="" || data.zanr=="" ||data.reditelj=="" || data.trajanje==""
		|| data.termin_od=="" || data.termin_dod=="" || data.opis=="" || data.glumci==""){
		alert("Molimo Vas popunite sva polja.");
		return;
	}
	else{
		var p = JSON.stringify(data);
		$.ajax({
			url:"http://localhost:8099/projekcija/dodajProjekciju",
			type:"POST",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(data){
				if(data){
					console.log("USPESNO DODATO");
				}
				else{
					console.log("Doslo je do greske.");
				}
			},
			error: function (textStatus, errorThrown) {
				console.log("NE VALJA NESTO");
				console.log(textStatus);
			}
		})
	}	
}