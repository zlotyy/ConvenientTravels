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