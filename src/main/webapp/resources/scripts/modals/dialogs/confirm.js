// modal z potwierdzeniem
$("#confirmDialog").dialog({
    autoOpen: false,
    modal: true
});

$("#dialogClose").on("click", function () {
    $("#confirmDialog").dialog("close");
});