<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div id="carDialog" title="${dialogTitle}">
    <form:form action="${dialogFormAction}" method="post" id="carDialogForm" name="carDialogForm" >
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
                    <tr>
                        <td><input id="brand" class="form-control" autofocus="autofocus" required="required" /></td>
                        <td><input id="model" class="form-control" required="required" /></td>
                        <td><input id="carColor" class="form-control" required="required" /></td>
                    </tr>
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
                <button type="button" id="dialogClose" class="btn btn-primary">Anuluj</button>
            </div>
        </div>
    </form:form>
</div>