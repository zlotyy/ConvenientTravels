// zwroc alert gdy hasla sa rozne
$('#registerForm').submit(function (event) {
    if($('#password').val() != $('#confirmPassword').val()){
        event.preventDefault();
        // otworz alert
        $("#alertDialog").dialog("open");
    }
});

// sprawdz zgodnosc hasel
$('#password, #confirmPassword').on('keyup', function () {
    if(($('#password').val() != "" && $('#confirmPassword').val() != "") && ($('#password').val() == $('#confirmPassword').val())) {
        $('#password, #confirmPassword').css('border-color', 'green');
    } else {
        $('#password, #confirmPassword').css('border-color', 'red');
    }
});