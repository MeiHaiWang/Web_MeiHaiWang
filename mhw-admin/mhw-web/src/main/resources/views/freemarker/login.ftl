
<#assign pageTitle="ログイン" />
<#include "utils/common.ftl">
<@layout.html pageTitle=pageTitle>

<div class="row">
	<div class="col-md-12">
		<@pankuzu.show 
			pageTitle = pageTitle 
			map = {
			}
		/>
	</div>
</div>

<#include
"./templates/loginForms.ftl" />

</@layout.html>
