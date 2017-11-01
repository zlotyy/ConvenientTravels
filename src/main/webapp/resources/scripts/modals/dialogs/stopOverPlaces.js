$("[name=stopOverPlacesDialog]").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("[name=stopOverPlacesClose]").on("click", function () {
    $("[name=stopOverPlacesTable]").find("tr:gt(0)").remove();
    $('[name=stopOverPlacesDialog]').dialog("close");
});

$("button[title='Close'], [name=stopOverPlacesSubmit]").on("click", function () {
    $("[name=stopOverPlacesDialog]").dialog("close");
});

// $('[name=stopOverPlacesSubmit]').on('click', function(){
//     $('[name=stopOverPlacesForm]').submit();
// });

function showStopOverPlacesModal(){
    $("[name=stopOverPlacesDialog]").dialog("open");
}


// dodaj kolejne miejsce
$(document).ready(function () {
    $("[name=addNextPlace]").on("click", function () {

        $("[name=stopOverPlacesTable]").append(
            "<tr>\n" +
            "                    <td><input name=\"stopOverCities\" class=\"form-control\" required /></td>\n" +
            "                    <td><input name=\"stopOverStreets\" class=\"form-control\" required /></td>\n" +
            "                    <td><button type=\"button\" name=\"removePlace\" title=\"Usuń\" class=\"btn btn-default delete\" >\n" +
            "                        <i class=\"glyphicon glyphicon-remove\" style=\"color: red\"></i>\n" +
            "                    </button></td>\n" +
            "                </tr>"
        );

    });
});

// usun miejsce    - WAZNE: Jesli element jest dynamicznie dodany, to nie mozna sobie tak po porstu wywolac onClicka, trzeba przekazac rodzica a pozniej dzieci
$(document).ready(function (e) {
    $("[name=stopOverPlacesTable]").on('click', 'button[name=removePlace]',function () {
        $(this).closest("tr").remove();
    });
});


// potwierdz miejsca Ajaxem
$(document).ready(function () {

    $("[name=stopOverPlacesSubmit]").on("click", function (e) {

        var placesArray = [];
        $("td").find("input").each(function(){
            placesArray.push($(this).val());
        });

        // zmiana obiektu na string
        var json_places = JSON.stringify(placesArray, null, 2);
        console.log(json_places);

        $.ajax({
            type: 'POST',
            url: '/drives/stopOverPlaces/confirm',
            data: {placesList: json_places},
            success: function () {
                console.log("Miejsca posrednie zapisane dla przejazdu");
            },
            error: function(jqXHR, exception) {
                console.log(jqXHR);
                console.log(exception);
            }
        });
    });
});

// pobierz samochody uzytkownika do modala
$("[name=chooseStopOverPlaces]").click(function () {
    showStopOverPlacesModal();

    // $.ajax({
    //     type: 'GET',
    //     url: '/car/getCars',
    //     data: {},
    //     success: function(result) {
    //         if(result) {                //jesli sa zapisane juz jakies samochody
    //             $("[name=carsTable]").empty();
    //
    //             result.forEach(function (element, index) {
    //                 var brand = element.carBrand;
    //                 var model = element.carModel;
    //                 var color = element.color;
    //                 console.log(brand, model,color);
    //
    //                 $("[name=carsTable]").append(
    //                     "<tr>\n" +
    //                     "                        <td><input name=\"carBrand\" class=\"form-control\" required=\"required\" value=" + brand + " /></td>\n" +
    //                     "                        <td><input name=\"carModel\" class=\"form-control\" required=\"required\" value=" + model + " /></td>\n" +
    //                     "                        <td><input name=\"color\" class=\"form-control\" required=\"required\" value=" + color + " /></td>\n" +
    //                     "                    <td><button type=\"button\" name=\"removeCar\" title=\"Usuń\" class=\"btn btn-default delete\" >\n" +
    //                     "                        <i class=\"glyphicon glyphicon-remove\" style=\"color: red\"></i>\n" +
    //                     "                    </button></td>\n" +
    //                     "                </tr>"
    //                 );
    //             });
    //         }
    //     }
    // });
});