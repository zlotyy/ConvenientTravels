$("#carDialog").dialog({
    autoOpen: false,
    modal: true
});

$("#car").on("click", function () {
    $("#carDialog").dialog("open");
});

$("#dialogClose").on("click", function () {
    $("#carDialog").dialog("close");
});