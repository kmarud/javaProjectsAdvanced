var validate = function(pesel) {
    var reg = /^[0-9]{11}$/;
    if(reg.test(pesel) == false) {
    return "Nieprawidłowa dlugość !";}
    else
    {
        var dig = (""+pesel).split("");
        var kontrola = (1*parseInt(dig[0]) + 3*parseInt(dig[1]) + 7*parseInt(dig[2]) + 9*parseInt(dig[3]) + 1*parseInt(dig[4]) + 3*parseInt(dig[5]) + 7*parseInt(dig[6]) + 9*parseInt(dig[7]) + 1*parseInt(dig[8]) + 3*parseInt(dig[9]))%10;
        if(kontrola==0) kontrola = 10;
        kontrola = 10 - kontrola;
        if(parseInt(dig[10])==kontrola)
        return "OK";
        else
        return "PESEL niepoprawny !";
    }

}


