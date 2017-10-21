// modal z alertem
$("[name=alertDialog]").dialog({
    autoOpen: false,
    modal: true
});

$("[name=alertClose]").on("click", function() {
    $("[name=alertDialog]").dialog("close");
});