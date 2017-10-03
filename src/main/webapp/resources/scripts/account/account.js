// sprawdz zgodnosc hasel
$('#newPassword, #confirmPassword').on('keyup', function () {
    if($('#newPassword').val() == $('#confirmPassword').val()){
        $('#newPassword, #confirmPassword').css('border-color', 'green');
    } else {
        $('#newPassword, #confirmPassword').css('border-color', 'red');
    }
});


function validate() {
    if(document.forms.passwordForm.oldPassword.value == ""){
        alert("Uzupelnij wszystkie pola");
        return false;
    }
    return true;
}

// zmien buttony po zatwierdzeniu formularza
$('.edit').click(function(){
   $(this).hide();
   $('.save, .cancel').show();
   $("input").prop('disabled', false);
});

$('.save').click(function(){
    $(this).hide();
    $('.cancel').hide();
    $('.edit').show();
    // $("input").prop('disabled', true);  WAŻNE ZEBY TEGO NIE BYLO !!! Jak jest to nie dziala edycja (najpierw sie wlacza disabled a pozniej leci modelAttribute do modelu
});

$('.cancel').click(function(){
    $(this).hide();
    $('.save').hide();
    $('.edit').show();
    $("input").prop('disabled', true);
});

// pokaz/ukryj pola z haslami
$('.change-password').click(function () {
   $('.change-password').hide();
   $('.save-password').show();
   $('#oldPassword, #newPassword, #confirmPassword').show();
});

$('.save-password').click(function () {
   $(this).hide();
   $('.change-password').show();
   $('#oldPassword, #newPassword, #confirmPassword').hide();
});
//
// $('#passwordForm').submit(function(){
//     $('#passwordForm').validator();
// });
