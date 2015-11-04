
<#macro generator parentId relationSubIds entities fields ignoreFieldNames typesMap>

<table class="table table-hover">
	<thead>
		<tr>
			<#list fields as field>
			<#if !(ignoreFieldNames?seq_contains(field.name)) && field.name != "serialVersionUID">
			<th>${field.name?html}</th>
			</#if>
			</#list>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#list entities as entity>
		<#assign registered = false>
		<#list relationSubIds as id>
			<#if entity.id == id>
				<#assign registered = true>
			</#if>
		</#list>
		<tr <#if registered>class="text-muted"</#if>>
			<#list fields as field>

			<#if !(ignoreFieldNames?seq_contains(field.name)) && field.name != "serialVersionUID">

				<#switch field.type>

				<#case "class java.lang.String">
					<#assign str = (fieldValue(field, entity)!'')?html />
					<#if  str?contains('png') || str?contains('jpg') || str?contains('jpeg') || str?contains('gif')>
					<td>
						<a href="${str}" target="_blank"><img src="${str}" height="100px" /></a>
					</td>
					<#else>
					<td>
					${str}
					</td>
					</#if>
					<#break>

				<#case "class java.lang.Integer">
				<#case "int">
					<td>
					${(fieldValue(field, entity)?string['0'])!''}
					</td>
					<#break>

				<#case "class java.lang.Long">
				<#case "long">
					<td>

					<#assign selectedId = fieldValue(field, entity) />
					<#if typesMap[field.name]??>
					<#list typesMap[field.name] as type>
					<#if selectedId == type.id>${type?html}</#if>
					</#list>
					<#else>
					${((fieldValue(field, entity)?string['0'])!'')?html}
					</#if>

					</td>
					<#break>

				<#case "class java.lang.Boolean">
				<#case "boolean">
					<td>
					${(fieldValue(field, entity)!false)?string('On', 'Off')}
					</td>
					<#break>
				
				<#default>

				</#switch>

			</#if>
			</#list>

			<td>
				<#if registered>
					<form action="${springMacroRequestContext.requestUri?html}/${entity.id}" method="POST">
						<input type="hidden" name="_method" value="DELETE" />
						<button type="submit" class="btn btn-default">削除</button>
					</form>
				<#else>
					<form action="${springMacroRequestContext.requestUri?html}/${entity.id}" method="POST">
						<button type="submit" class="btn btn-primary">登録</button>
					</form>
				</#if>
			</td>

		</tr>
		</#list>
	</tbody>
</table>

</#macro>