
<#macro show pageTitle="" map={}>
<#assign pankuzu = "" />
	<ol class="breadcrumb">
		<li><a href="/">ホーム</a></li>
		<#list map?keys as key>
		<li>
			<a href="${map[key]?html}">${key?html}</a>
		</li>
		</#list>
		<#if pageTitle??>
		<li class="active">
		<b>${pageTitle?html}</b>
		</li>
		</#if>
</#macro>