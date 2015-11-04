
<#assign pageTitle="" />
<#include "../../utils/common.ftl">
<#include "../common.ftl">
<@layout.html pageTitle=pageTitle>

<#assign path = springMacroRequestContext.requestUri?html />

<div class="row">
	<div class="col-md-12">

		<form action=${path} method="POST">
			<input type="hidden" name="_method" value="PUT" />
			<@form.generator
				ignoreFieldNames = ["id"] 
				typesMap = { 
				}
			/>
			<button type="submit" class="btn btn-primary">UPDATE</button>
		</form>

		<form action=${path} method="POST">
			<input type="hidden" name="_method" value="DELETE" />
			<button type="submit" class="btn btn-primary" onclick='return confirm("delete confirm");'>DELETE</button>
		</form>

	</div>
</div>

</@layout.html>