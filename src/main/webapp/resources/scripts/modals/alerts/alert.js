// modal z alertem
$("#alertDialog").dialog({
    autoOpen: false,
    modal: true
});

$("#alertClose").on("click", function() {
    $("#alertDialog").dialog("close");
});