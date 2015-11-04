
<#macro optionSelector sortOrSize selectorMap>

<#assign query = "?"/>
<#list RequestParameters?keys as key>
<#if key != 'page' && key != sortOrSize>
<#assign query = query + key +
"=" + RequestParameters[key]?html + "&">
</#if>
</#list>

<#assign value = (RequestParameters[sortOrSize])!''?html />

<select class="form-control" onchange="location.href = this.options[selectedIndex].value">

	<option value="${query}">
		-- 選択してください --
	</option>
	<#list selectorMap?keys as key>
	<option value="${query + sortOrSize + '=' + key}" <#if value == key>selected</#if>>
		${selectorMap[key]}
	</option>
	</#list>

</select>

</#macro>