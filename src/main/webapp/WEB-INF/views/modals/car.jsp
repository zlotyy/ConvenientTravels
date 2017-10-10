<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="carDialog" title="${dialogTitle}">
    <%--<form:form action="/car/add" modelAttribute="user" method="post" id="addCarForm" name="addCarForm" />--%>
    <%--<jsp:include page="/car/add" />--%>
    <form:form action="/car" modelAttribute="user" method="post" id="carDialogForm" name="carDialogForm" >
        <div class="col-md-12">
            <table class="table" id="carTable">
                <thead>
                    <tr>
                        <th width="35%">Marka</th>
                        <th width="35%">Model</th>
                        <th width="30%">Kolor</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="car" items="${user.cars}" varStatus="status">
                    <tr>
                        <td><form:input path="${car.carBrand}" id="brand" name="brand" class="form-control" autofocus="autofocus" required="required" /></td>
                        <td><form:input path="${car.carModel}" id="model" name="model" class="form-control" required="required" /></td>
                        <td><form:input path="${car.color}" id="carColor" name="carColor" class="form-control" required="required" /></td>
                    </tr>
                </c:forEach>
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
                <button type="submit" id="dialogSubmit" class="btn btn-primary">Potwierdź</button>
                <button type="button" id="dialogClose" class="btn btn-primary">Odrzuć</button>
            </div>
        </div>
    </form:form>
</div>