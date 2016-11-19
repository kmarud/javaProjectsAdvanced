var validate = function(text) {
  
    var mailReg = new RegExp('[0-9a-zA-Z_.-]+@[0-9a-zA-Z.-]+\.[a-zA-Z]{2,3}', 'gi');
   //var mailReg = new RegExp('^a.*','g');
    if (!mailReg.test(text)) {
       // showFieldValidation(input, false);
       //alert("zle");
        return "Email invalid"
        
    } else {
       // showFieldValidation(input, true);
        return "OK";
    }    
}
