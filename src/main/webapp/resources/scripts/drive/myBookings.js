$("[name=myBookingsTableBody]").find("tr").hover(function(){
    $(this).css("background-color", "buttonface");
}, function(){
    $(this).css("background-color", "white");
});