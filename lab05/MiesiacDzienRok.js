var validate = function(input) {

    var regex = new RegExp('^[01][0-9]\/[0-3][0-9]\/[12][0-9]{3}$', 'g');
   
    if (!regex.test(input)) {
        //showFieldValidation(input, false);
        return "Niepoprawny format: mm/dd/rrrr !";
    } else {
        //showFieldValidation(input, true);
        return "OK";
    }    
};




