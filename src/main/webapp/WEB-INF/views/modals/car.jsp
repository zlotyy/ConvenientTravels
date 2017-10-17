<%@ page import="com.mvc.model.CarModel" %>
<%@ page import="java.util.List" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="carDialog" title="${dialogTitle}">
    <form:form modelAttribute="user" method="post" id="carsForm" role="form" >
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th width="35%">Marka</th>
                        <th width="35%">Model</th>
                        <th width="30%">Kolor</th>
                    </tr>
                </thead>
                <tbody id="carsTable">
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