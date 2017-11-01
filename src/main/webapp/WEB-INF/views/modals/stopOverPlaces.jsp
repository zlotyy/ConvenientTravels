<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="stopOverPlacesDialog" name="stopOverPlacesDialog" title="${dialogTitle}">
    <form:form method="post" modelAttribute="driveDTO" id="stopOverPlacesForm" name="stopOverPlacesForm" role="form">
        <div class="col-md-12">
            <table class="table">
                <thead>
                    <tr>
                        <th width="45%">Miasto</th>
                        <th width="45%">Ulica</th>
                        <th width="10%"></th>
                    </tr>
                </thead>
                <tbody id="stopOverPlacesTable" name="stopOverPlacesTable">
                    <tr>
                        <td><input name="stopOverCities" class="form-control" autofocus="autofocus" required/></td>
                        <td><input name="stopOverStreets" class="form-control" required/></td>
                        <td>
                            <button type="button" name="removePlace" title="Usuń" class="btn btn-default delete">
                                <i class="glyphicon glyphicon-remove" style="color: red"></i>
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div class="col-md-6">
            <div class="form-group pull-left">
                <button type="button" id="addNextPlace" name="addNextPlace" class="btn btn-primary">Dodaj kolejne
                </button>
            </div>
        </div>
        <div class="col-md-6">
            <div class="form-group pull-right">
                <button type="button" id="stopOverPlacesSubmit" name="stopOverPlacesSubmit" class="btn btn-primary">
                    Potwierdź
                </button>
                <button type="reset" id="stopOverPlacesClose" name="stopOverPlacesClose" class="btn btn-primary">
                    Odrzuć
                </button>
            </div>
        </div>
    </form:form>
</div>