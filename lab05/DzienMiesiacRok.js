var validate = function(input) {

    var regex = new RegExp('^[0-3]?[0-9]\/[01][0-9]\/[12][0-9]{3}$', 'g');
   
    if (!regex.test(input)) {
        //showFieldValidation(input, false);
        return "Niepoprawny format: dd/mm/rrrr !";
    } else {
        //showFieldValidation(input, true);
        return "OK";
    }    
};




