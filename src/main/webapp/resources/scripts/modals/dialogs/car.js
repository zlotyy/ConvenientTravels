$("#carDialog").dialog({
    autoOpen: false,
    modal: true,
    width: 500
});

$("#dialogClose").on("click", function () {
    $("#carsTable").find("tr:gt(1)").remove();
    $("#carDialog").dialog("close");
});

$("button[title='Close'], #dialogSubmit").on("click", function () {
    $("#carDialog").dialog("close");
});

$('#addNextCar').on('click', function(){
    $('#addCarForm').submit();
});

function showCarModal(){
    $("#carDialog").dialog("open");
}


// dodaj kolejny samochod
$(document).ready(function () {
    $("#addNextCar").on("click", function () {

        $("#carsTable").append(
            "<tr>\n" +
            "                        <td><input name=\"carBrand\" class=\"form-control\" required=\"required\" /></td>\n" +
            "                        <td><input name=\"carModel\" class=\"form-control\" required=\"required\" /></td>\n" +
            "                        <td><input name=\"color\" class=\"form-control\" required=\"required\" /></td>\n" +
            "                    </tr>"

        );

    });
});

// potwierdz samochody Ajaxem
$(document).ready(function () {

    $("#dialogSubmit").on("click", function (e) {

        var carsArray = [];
        $("td").find("input").each(function () {
            var car;
            car.carBrand = $(this).val();
            car.color = $(this).val();
            carsArray.push(car);
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
            }
        });
    });
});


// pobierz samochody uzytkownika do modala
$("#chooseCars, #changeCars").click(function () {
    showCarModal();

    $.ajax({
        type: 'GET',
        url: '/car/getCars',
        data: {},
        success: function(result) {
            if(result) {
                $("#carsTable").empty();

                result.forEach(function (element, index) {
                    var brand = element.carBrand;
                    var model = element.carModel;
                    var color = element.color;
                    console.log(brand, model,color);

                    $("#carsTable").append(
                        "<tr>\n" +
                        "                        <td><input name=\"carBrand\" class=\"form-control\" required=\"required\" value=" + brand + " /></td>\n" +
                        "                        <td><input name=\"carModel\" class=\"form-control\" required=\"required\" value=" + model + " /></td>\n" +
                        "                        <td><input name=\"color\" class=\"form-control\" required=\"required\" value=" + color + " /></td>\n" +
                        "                    </tr>"
                    );
                });
            }
        }
    });
});