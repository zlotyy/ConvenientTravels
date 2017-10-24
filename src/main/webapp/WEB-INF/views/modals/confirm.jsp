<div id="confirmDialog" name="confirmDialog" title="${dialogTitle}">
    <form action="${dialogFormAction}" method="post" name="${dialogFormName}" >
        <p>${dialogContent}</p>
        <div class="form-group pull-right">
            <button type="button" name="dialogSubmit" form="${dialogFormName}" class="btn btn-primary">Potwierdź</button>
            <button type="button" name="dialogClose" class="btn btn-primary">Anuluj</button>
        </div>
    </form>
</div>

<script type="text/javascript">
    $("[name=dialogSubmit]").on("click", function () {
       $("[name=${dialogFormName}]").submit();
    });
</script>