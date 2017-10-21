// modal z potwierdzeniem
$("[name=confirmDialog]").dialog({
    autoOpen: false,
    modal: true
});

$("[name=dialogClose]").on("click", function () {
    $("[name=confirmDialog]").dialog("close");
});