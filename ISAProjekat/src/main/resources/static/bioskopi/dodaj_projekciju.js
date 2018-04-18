var lista = null;

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

	//console.log(data);
	
	if(data.naziv=="" || data.zanr=="" ||data.reditelj=="" || data.trajanje==""
		|| data.termin_od=="" || data.termin_do=="" || data.opis=="" || data.glumci=="" || data.cena=="" || isNaN(data.cena)==true){
		alert("Molimo Vas popunite sva polja.");
		return;
	}
	else{
		console.log("SALALIST");
		console.log(lista);
		
		var x = $('#sala_select :selected').text();
		console.log(x);
		
		var z=null;
		
		$.each(lista, function(index,value){
			console.log("IZLUDECUUUUUUUUUUUU");
			if(x.localeCompare(value.naziv)==0){
				z=value;
			}
		});
		
		var p = JSON.stringify(z);
		
		$.ajax({
			url:"../projekcija/setujSala",
			type:"POST",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(dataaaa){
				if(dataaaa!=null){
					dodajProjekciju2();
				}
				else{
					dodajProjekciju2();
				}
			},
			error: function (textStatus, errorThrown) {
				console.log("NE VALJA NESTO");
				console.log(textStatus);
			}
		});
	}
}

function dodajProjekciju2() {
	
		var $form = $("#dodajprojekciju_froma");
		var data= getFormData($form);
	
		console.log("SALJE NA SERVER");
		var p = JSON.stringify(data);
		console.log(data);
		
		$.ajax({
			url:"../projekcija/dodajProjekciju",
			type:"POST",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(data){
				if(data){
					console.log("USPESNO DODATO");
					top.location.href="../bioskopi/profil_bioskopa.html";
				}
				else{
					top.location.href="../bioskopi/profil_bioskopa.html";
				}
			},
			error: function (textStatus, errorThrown) {
				console.log("NE VALJA NESTO");
				console.log(textStatus);
			}
		});
	}	

function getSale(data){
	
	var selectedBioskop = data;
	console.log("USAO U GET SALE")
	
	$.ajax({
		
		url:"../bioskopController/getSelectedBioskopSale",
		type:"GET",
		dataType:"json",
		success:function(salaList){
			if(salaList==null){
				console.log("Bioskop nema sala.");		
				lista=salaList;
				popuniSale(selectedBioskop, salaList);				
			}
			
			else{
				console.log("Bioskop ima sale.");
				lista=salaList;
				popuniSale(selectedBioskop, salaList);				
			}
		}
		
	});
}

function popuniSale(selectedBioskop,salaList){
	var options="";
	
	var text= 
		
			$.each(salaList, function(index,value){
				var text="<option>"+value.naziv+"</option>";
				options += text;
			});
	
	console.log(options);
	$("#sala_select").append(options);
}

$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});