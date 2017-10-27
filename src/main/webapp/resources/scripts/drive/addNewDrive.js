$(function () {
    $('[name=datetimepicker]').datetimepicker({
        locale: 'pl',
        format: 'YYYY-MM-DD HH:mm'
    });
});

$( document ).ready(function() {
    $("#RoundTrip").change(function(){
        $("#DateTimePicker_RoundTrip").find("input").prop('disabled', !this.checked);
        $("#DateTimePicker_RoundTrip").find("input").val('');
    })
})