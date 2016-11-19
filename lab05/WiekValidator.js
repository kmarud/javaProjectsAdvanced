var validate = function(text) {

    if (isNaN(text)){
    	return "Nie liczba !";
    } else if( text < 0 ){
    	return "Liczba musi być więszka od 0";
    } else if( text > 120) {
    	return "Liczba musi być mniejsza od 120";
    } else{
    	return "OK";
    }
}
