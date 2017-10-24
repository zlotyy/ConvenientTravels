// sprawdz czy hasla sa puste
$(document).ready(function(){
    $('#oldPassword').on('blur keyup', function(){
        var input = $(this);
        if(input.val() == "") {
            $(this).css('border-color', 'red');
        } else {
            $(this).css('border-color', 'green');
        }
    });

    $('#newPassword').on('blur keyup', function(){
        var input = $(this);
        if(input.val() == "") {
            $(this).css('border-color', 'red');
        }
    });

    $('#confirmPassword').on('blur keyup', function(){
        var input = $(this);
        if(input.val() == "") {
            $(this).css('border-color', 'red');
        }
    });
});

// sprawdz zgodnosc hasel
$('#newPassword, #confirmPassword').on('keyup', function () {
    if(($('#newPassword').val() != "" && $('#confirmPassword').val() != "") && ($('#newPassword').val() == $('#confirmPassword').val())) {
        $('#newPassword, #confirmPassword').css('border-color', 'green');
    } else {
        $('#newPassword, #confirmPassword').css('border-color', 'red');
    }
});

// zatwierdz zmiany profilu
$("[name=saveProfileChanges]").on("click", function () {
    $('[name=profileForm]').submit();
});

// zmien buttony po zatwierdzeniu formularza
$('.edit').click(function(){
   $(this).hide();
   $('.save, .cancel').show();
   $("input").prop('disabled', false);
});
$('#profileForm').submit(function(){
    $('.save').hide();
    $('.cancel').hide();
    $('.edit').show();
    // $("input").prop('disabled', true);  WAŻNE ZEBY TEGO NIE BYLO !!! Jak jest to nie dziala edycja (najpierw sie wlacza disabled a pozniej leci modelAttribute do modelu
});
$('.cancel').click(function(){
    $(this).hide();
    $('.save').hide();
    $('.edit').show();
    $("#profileForm").find("input").prop('disabled', true);
});

// pokaz/ukryj pola z haslami
$('.change-password').click(function () {
   $(this).hide();
   $('.save-password').show();
   $('.cancel-password').show();
   $('#oldPassword, #newPassword, #confirmPassword').show();
   $('#wrongPassword').hide();
    $('#oldPassword, #newPassword, #confirmPassword').css('border-color', 'red');
});
$('#passwordForm').submit(function (event) {
    if($('#newPassword').val() != $('#confirmPassword').val()){
        event.preventDefault();
        // otworz alert
        $("#alertDialog").dialog("open");
    } else {
        $('.save-password').hide();
        $('.cancel-password').hide();
        $('.change-password').show();
        $('#oldPassword, #newPassword, #confirmPassword').hide();
    }
});
$('.cancel-password').click(function(){
    $('.save-password').hide();
    $(this).hide();
    $('.change-password').show();
    $('#oldPassword, #newPassword, #confirmPassword').hide();
    $('#wrongPassword').hide();
});

// otworz modal z potwierdzeniem
$("#deleteUser").on("click", function(e) {
    //e.preventDefault();
    $("#confirmDialog").dialog("open");
});