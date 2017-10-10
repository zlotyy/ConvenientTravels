$("#carDialog").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("#car").on("click", function () {
    $("#carDialog").dialog("open");
});

$("#dialogClose, button[title='Close']").on("click", function () {
    //$("#carTable").find("tr:gt(1)").remove();
    $("#carDialog").dialog("close");
});

$('#addNextCar').on('click', function(){
    $('#addCarForm').submit();
});

