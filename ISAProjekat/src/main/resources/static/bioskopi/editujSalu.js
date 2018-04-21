var global_list=null;

$(document).ready(function(){
		$.ajax({			
			url:"../bioskopController/getSelectedBioskopSale",
			type:"GET",
			dataType:"json",
			success:function(salaList){
				var global_list=salaList;
				popuniPodatke(salaList);
			}
			
		});
});

function popuniPodatke(salaList){
	var options="";
	$.each(salaList, function(index,value){
		var text="<option>"+value.naziv+"</option>";
		options += text;
	});
	
	$("#sala_select").append(options);
}

function editSalu(){
	var $form = $("#dodajprojekciju_froma");
	var data= getFormData($form);
	var p = JSON.stringify(data);
	
	var x = $('#sala_select :selected').text();
	
	var isChecked = $('#vip_enabled').prop('checked');
	
	console.log(x);
	//console.log(myRadio);
	
	$.ajax({
		url:"../bioskopController/editSala",
		type:"PUT",
		data: p,
		contentType:"application/json",
		success:function(data){
			top.location.href="../bioskopi/profil_bioskopa.html";
		},
		error:function(){
			top.location.href="../bioskopi/profil_bioskopa.html";			
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


$(document).on('click','#PROKLETODUGME',function(e) { 
	console.log("KLIKNUO JE DUGME");
	e.preventDefault();
});








