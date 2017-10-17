<%@ page import="com.mvc.model.CarModel" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="carDialog" title="${dialogTitle}">
    <form:form action="/car" modelAttribute="user" method="post" >
        <div class="col-md-12">
            <table class="table" id="carTable">
                <thead>
                    <tr>
                        <th width="35%">Marka</th>
                        <th width="35%">Model</th>
                        <th width="30%">Kolor</th>
                    </tr>
                </thead>
                <tbody id="CarsTable">
                <%--<c:forEach var="car" items="${user.cars}">--%>
                    <tr>
                        <td><input name="carBrand" class="form-control" autofocus="autofocus" required="required" /></td>
                        <td><input name="carModel" class="form-control" required="required" /></td>
                        <td><input name="color" class="form-control" required="required" /></td>
                    </tr>
                <%--</c:forEach>--%>
                </tbody>
            </table>
        </div>

        <div class="col-md-6">
            <div class="form-group pull-left">
                <button type="button" id="addNextCar" class="btn btn-primary">Dodaj kolejny</button>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group pull-right">
                <button type="button" id="dialogSubmit" class="btn btn-primary">Potwierdź</button>
                <button type="reset" id="dialogClose" class="btn btn-primary">Odrzuć</button>
            </div>
        </div>
    </form:form>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        $("#addNextCar").on("click", function () {

            $("#CarsTable").append(
                "<tr>\n" +
                "                        <td><input name=\"carBrand\" class=\"form-control\" required=\"required\" /></td>\n" +
                "                        <td><input name=\"carModel\" class=\"form-control\" required=\"required\" /></td>\n" +
                "                        <td><input name=\"color\" class=\"form-control\" required=\"required\" /></td>\n" +
                "                    </tr>"

            );

        });
    });

    $(document).ready(function () {
        $("#dialogSubmit").on("click", function () {
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
                success: function(result) {
                    console.log(result);
                }
            });
        });
    });



    <%--$(document).ready(function () {--%>
        <%--$("#addNextCar").on("click", function () {--%>
            <%--var cars = ${user.cars};--%>
            <%--$.ajax({--%>
                <%--//dataType: "json",--%>
                <%--//data: {cars: cars},--%>
                <%--type: 'GET',--%>
                <%--url: '/car/add',--%>
                <%--success: function(result) {--%>
                    <%--alert("ok");--%>
                    <%--console.log(result);--%>

                    <%--$("#CarsTable").append(--%>
                        <%--"<tr>\n" +--%>
                        <%--"                        <td><input class=\"form-control\" required=\"required\" /></td>\n" +--%>
                        <%--"                        <td><input class=\"form-control\" required=\"required\" /></td>\n" +--%>
                        <%--"                        <td><input class=\"form-control\" required=\"required\" /></td>\n" +--%>
                        <%--"                    </tr>"--%>

                    <%--);--%>
                <%--}--%>
            <%--});--%>
        <%--});--%>
    <%--});--%>



</script>