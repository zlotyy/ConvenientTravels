$("[name=carDialog]").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("[name=carDialogClose]").on("click", function () {
    $("[name=carsTable]").find("tr:gt(1)").remove();
    $('[name=carDialog]').dialog("close");
});

$("button[title='Close'], [name=carDialogSubmit]").on("click", function () {
    $("[name=carDialog]").dialog("close");
});

$('[name=addNextCar]').on('click', function(){
    $('[name=addCarForm]').submit();
});

function showCarModal(){
    $("[name=carDialog]").dialog("open");
}


// dodaj kolejny samochod
$(document).ready(function () {
    $("[name=addNextCar]").on("click", function () {

        $("[name=carsTable]").append(
            "<tr>\n" +
            "                    <td><input name=\"carBrand\" class=\"form-control\" autofocus=\"autofocus\" required=\"required\" /></td>\n" +
            "                    <td><input name=\"carModel\" class=\"form-control\" required=\"required\" /></td>\n" +
            "                    <td><input name=\"color\" class=\"form-control\" required=\"required\" /></td>\n" +
            "                    <td><button type=\"button\" name=\"removeCar\" title=\"Usuń\" class=\"btn btn-default delete\" >\n" +
            "                        <i class=\"glyphicon glyphicon-remove\" style=\"color: red\"></i>\n" +
            "                    </button></td>\n" +
            "                </tr>"
        );

    });
});

// usun samochod    - WAZNE: Jesli element jest dynamicznie dodany, to nie mozna sobie tak po porstu wywolac onClicka, trzeba przekazac rodzica a pozniej dzieci
$(document).ready(function (e) {
   $("[name=carsTable]").on('click', 'button[name=removeCar]',function () {
       $(this).closest("tr").remove();
   });
});

// potwierdz samochody Ajaxem
$(document).ready(function () {

    $("[name=carDialogSubmit]").on("click", function (e) {

        var carsArray = [];
        $("td").find("input").each(function(){
            carsArray.push($(this).val());
        });

        // zmiana obiektu na string
        var json_cars = JSON.stringify(carsArray, null, 2);
        console.log(json_cars);

        $.ajax({
            type: 'POST',
            url: '/car/confirm',
            data: {carsList: json_cars},
            success: function () {
                console.log("Samochody zapisane uzytkownikowi");
            },
            error: function(jqXHR, exception) {
                console.log(jqXHR);
                console.log(exception);
            }
        });
    });
});


// pobierz samochody uzytkownika do modala
$("[name=chooseCars], [name=changeCars]").click(function () {
    showCarModal();

    $.ajax({
        type: 'GET',
        url: '/car/getCars',
        data: {},
        success: function(result) {
            if(result) {                //jesli sa zapisane juz jakies samochody
                $("[name=carsTable]").empty();

                result.forEach(function (element, index) {
                    var brand = element.carBrand;
                    var model = element.carModel;
                    var color = element.color;
                    console.log(brand, model,color);

                    $("[name=carsTable]").append(
                        "<tr>\n" +
                        "                        <td><input name=\"carBrand\" class=\"form-control\" required=\"required\" value=" + brand + " /></td>\n" +
                        "                        <td><input name=\"carModel\" class=\"form-control\" required=\"required\" value=" + model + " /></td>\n" +
                        "                        <td><input name=\"color\" class=\"form-control\" required=\"required\" value=" + color + " /></td>\n" +
                        "                    <td><button type=\"button\" name=\"removeCar\" title=\"Usuń\" class=\"btn btn-default delete\" >\n" +
                        "                        <i class=\"glyphicon glyphicon-remove\" style=\"color: red\"></i>\n" +
                        "                    </button></td>\n" +
                        "                </tr>"
                    );
                });
            }
        }
    });
});