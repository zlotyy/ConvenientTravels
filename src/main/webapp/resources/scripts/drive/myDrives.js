$("[name=myDrivesTableBody]").find("tr").hover(function(){
    $(this).css("background-color", "buttonface");
}, function(){
    $(this).css("background-color", "white");
});

// otworz modal z potwierdzeniem
$("[name=removeDrive]").on("click", function() {
    $("[name=confirmDialog]").dialog("open");
});