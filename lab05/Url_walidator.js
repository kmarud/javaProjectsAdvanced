var validate = function(input) {
    var urlReg = new RegExp('^http:\/\/.+', 'i');
    if (!urlReg.test(input)) {
        //showFieldValidation(input, false);
        return "Poprawny adres zaczyna się od http://";
    } else {
       // showFieldValidation(input, true);
        return "OK";
    }    
};