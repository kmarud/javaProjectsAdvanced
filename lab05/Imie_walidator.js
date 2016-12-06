var validate = function(input) {

    var regex = new RegExp('^[a-z]+$', 'gi');
   
    if (!regex.test(input)) {
        //showFieldValidation(input, false);
        return "Pole zawiera niedozwolone znaki!";
    } else {
        //showFieldValidation(input, true);
        return "OK";
    }    
};




