
<#if errors??>
<#list errors as error>
<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;
		</span>
	</button>
	<strong>Warning!</strong>
	${error.field} ${error.defaultMessage?html}.
</div>
</#list>
</#if>
<#if error??>
<div class="alert alert-danger alert-dismissible" role="alert">
	<button type="button" class="close" data-dismiss="alert"
		aria-label="Close">
		<span aria-hidden="true">&times;
		</span>
	</button>
	<strong>Warning!</strong>
	${error?html}
</div>
</#if>