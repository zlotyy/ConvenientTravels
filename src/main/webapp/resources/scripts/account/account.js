$('.edit').click(function(){
   $(this).hide();
   $('.save, .cancel').show();
   $("input").prop('disabled', false);
});

$('.save').click(function(){
    $(this).hide();
    $('.cancel').hide();
    $('.edit').show();
    $("input").prop('disabled', true);
});

$('.cancel').click(function(){
    $(this).hide();
    $('.save').hide();
    $('.edit').show();
    $("input").prop('disabled', true);
});