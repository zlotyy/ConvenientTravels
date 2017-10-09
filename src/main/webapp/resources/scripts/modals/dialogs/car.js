$("#carDialog").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("#car").on("click", function () {
    $("#carDialog").dialog("open");
});

$("#dialogClose, button[title='Close']").on("click", function () {
    $("#carTable").find("tr:gt(1)").remove();
    $("#carDialog").dialog("close");
});

$('#addNextCar').bind('click', function(){
    $('#carTable > tbody:last-child').append('<tr>\n' +
        '                        <td><input id="brand" class="form-control" autofocus="autofocus" required="required" /></td>\n' +
        '                        <td><input id="model" class="form-control" required="required" /></td>\n' +
        '                        <td><input id="carColor" class="form-control" required="required" /></td>\n' +
        '                    </tr>');
});

