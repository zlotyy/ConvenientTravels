$(function () {
    $('[name=datetimepicker]').datetimepicker({
        locale: 'pl',
        format: 'YYYY-MM-DD HH:mm'
    });
});

$( document ).ready(function() {
    $("#RoundTrip").change(function(){
        $("#DateTimePicker_RoundTrip_Departure, #DateTimePicker_RoundTrip_Arrival").find("input").prop('disabled', !this.checked);
    })
})