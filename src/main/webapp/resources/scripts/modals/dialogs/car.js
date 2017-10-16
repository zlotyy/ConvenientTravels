$("#carDialog").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("#car").on("click", function () {
    showCarModal();
});

$("#dialogClose, #dialogSubmit,button[title='Close']").on("click", function () {
    //$("#carTable").find("tr:gt(1)").remove();
    $("#carDialog").dialog("close");
});

$('#addNextCar').on('click', function(){
    $('#addCarForm').submit();
});

function showCarModal(){
    $("#carDialog").dialog("open");
}