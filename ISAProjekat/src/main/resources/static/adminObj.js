function izlogujSe(){
	$.ajax({
		
		url:"/korisnikController/odjava",
		type:"GET",
		dataType:"json",
		success:function(data){
			if(data){
				top.href="index.html"
			}
			else{
				console.log("Neuspesno unistena sesija.");
			}
		},
	error:function(textStatus, errorThrown){
		console.log(textStatus);
	}
		
	});
}