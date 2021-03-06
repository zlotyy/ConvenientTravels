<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="carDialog" name="carDialog" title="${dialogTitle}">
    <form:form method="post" modelAttribute="user" id="carsForm" name="carsForm" role="form" >
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th width="30%">Marka</th>
                        <th width="30%">Model</th>
                        <th width="30%">Kolor</th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody id="carsTable" name="carsTable">
                    <tr>
                        <td><input name="carBrand" class="form-control" autofocus="autofocus" required /></td>
                        <td><input name="carModel" class="form-control" required /></td>
                        <td><input name="color" class="form-control" required /></td>
                        <td><button type="button" name="removeCar" title="Usuń" class="btn btn-default delete" >
                            <i class="glyphicon glyphicon-remove" style="color: red"></i>
                        </button></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-6">
            <div class="form-group pull-left">
                <button type="button" id="addNextCar" name="addNextCar" class="btn btn-primary">Dodaj kolejny</button>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group pull-right">
                <button type="button" id="carDialogSubmit" name="carDialogSubmit" class="btn btn-primary">Potwierdź</button>
                <button type="reset" id="carDialogClose" name="carDialogClose" class="btn btn-primary">Odrzuć</button>
            </div>
        </div>
    </form:form>
</div>