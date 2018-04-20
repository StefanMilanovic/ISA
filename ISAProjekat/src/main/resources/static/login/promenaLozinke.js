$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});


function izmeniLozinku(){
	
	var $form = $("#promenaLozinke_froma");
	var data= getFormData($form);
	
	if(data.sifra.localeCompare(data.pass2)!=0){
		alert("Unesene lozinke nisu iste.");
		return;
	}
	
	
	var p = JSON.stringify(data);
	
	$.ajax({
		
		url:"/korisnikController/promenaLozinke",
		type:"PUT",
		data:p,
		contentType:"application/json",
		dataType:'json',
		success:function(data){
			if(data==null){
				console.log("Doslo je do greske");
			}
			else{
				top.location.href="/login/login.html";
			}
		},
		error:function(textStatus, errorThrown){
			console.log("Vraca error.");
			console.log(textStatus);
		}
		
	});
	
}

function getFormData($form){
	var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}
