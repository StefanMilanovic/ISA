var lista = null;

$(document).ready(function(){
	//console.log("Usao na profil bioskopa.");

	$.ajax({
		url:"../pozoristeController/getSelektovanuProjekciju",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data == null){
				console.log("Data je null");
			}
			else{
			//	console.log("PISE DATA");
			//	console.log(data);
				
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
	
	var selectedProjekcija = data;
	console.log("USAO U GET SALE")	
	$.ajax({	
		url:"../pozoristeController/getSelectedPozoristeSale",
		type:"GET",
		dataType:"json",
		success:function(salaList){	
			if(salaList==null){
				console.log("Bioskop nema sala.");		
				lista=salaList;
				popuniData(selectedProjekcija, salaList);				
			}
			
			else{
				console.log("Bioskop ima sale.");
				lista=salaList;
				popuniData(selectedProjekcija, salaList);				
			}
		}		
	});
}


function popuniData(selectedProjekcija, salaList){
	
	var options="";
		$.each(salaList, function(index,value){
			var text="<option>"+value.naziv+"</option>";
			options += text;
		});
	
	$("#sala_select").append(options);
	
	document.getElementById('id').value=selectedProjekcija.id;
	document.getElementById('idd').value=selectedProjekcija.id;
	document.getElementById('naziv').value=selectedProjekcija.naziv;
	document.getElementById('zarn').value=selectedProjekcija.zarn;
	document.getElementById('ime_reditelja').value=selectedProjekcija.ime_reditelja;
	document.getElementById('trajanje').value=selectedProjekcija.trajanje;
	document.getElementById('termin_od').value=selectedProjekcija.termin_od;
	document.getElementById('termin_do').value=selectedProjekcija.termin_do;
	document.getElementById('cena').value=selectedProjekcija.cena;
	document.getElementById('opis').value=selectedProjekcija.opis;
	document.getElementById('spisak_glumaca').value=selectedProjekcija.spisak_glumaca;
	
}

function editProjekciju(){
	var $form = $("#dodajprojekciju_froma");
	var data= getFormData($form);

	//console.log(data);
	
	if(data.naziv=="" || data.zanr=="" ||data.reditelj=="" || data.trajanje==""
		|| data.termin_od=="" || data.termin_do=="" || data.opis=="" || data.glumci=="" || data.cena=="" || isNaN(data.cena)==true){
		alert("Molimo Vas popunite sva polja.");
		return;
	}
	else{
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
			url:"../projekcija/setujPozSala",
			type:"POST",
			data:p,
			contentType:"application/json",
			dataType:'json',
			success: function(dataaaa){
				if(dataaaa!=null){
					editProjekciju2();
				}
				else{
					editProjekciju2();
				}
			},
			error: function (textStatus, errorThrown) {
				console.log("NE VALJA NESTO");
				console.log(textStatus);
			}
		});
	}
}

function editProjekciju2(){
	var $form = $("#dodajprojekciju_froma");
	var data= getFormData($form);

	console.log("SALJE NA SERVER");
	var p = JSON.stringify(data);
	console.log(data);
	
	$.ajax({
		url:"../projekcija/editProjekcijuPoz",
		type:"PUT",
		data:p,
		contentType:"application/json",
		dataType:'json',
		success: function(data){
			if(data){
				console.log("USPESNO EDITOVANO");
				top.location.href="../pozorista/profil_pozorista.html";
			}
			else{
				console.log("NULL");
				top.location.href="../pozorista/profil_pozorista.html";
			}
		},
		error: function (textStatus, errorThrown) {
			console.log("ERROR");
			top.location.href="../pozorista/profil_pozorista.html";			
		}
	});
}

$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});


function getFormData($form){
	var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}
