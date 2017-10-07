<div id="confirmDialog" title="title">
    <form action="/user/account/delete/confirm/ok" method="post" id="confirmDialogForm" name="confirmDialogForm" >
        <p>content</p>
        <div class="form-group pull-right">
            <button type="button" id="dialogSubmit" class="btn btn-primary" form="confirmDialogForm">Potwierdź</button>
            <button type="button" id="dialogClose" class="btn btn-primary">Anuluj</button>
            <input type="hidden" name="myObjectId" value="${user}" />
        </div>
    </form>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<%-- jQuery UI javascript --%>
<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>

<script type="text/javascript">

    function modalSubmit(){
        $("#myForm").submit();
    }

    document.getElementById("dialogSubmit").addEventListener("click", modalSubmit, false);
</script>