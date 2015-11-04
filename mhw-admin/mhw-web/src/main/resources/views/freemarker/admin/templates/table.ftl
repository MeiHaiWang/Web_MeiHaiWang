
<#macro generator entities fields ignoreFieldNames typesMap>

<table class="table table-striped table-hover">
	<thead>
		<tr>
			<#list fields as field>
			<#if !(ignoreFieldNames?seq_contains(field.name)) && field.name != "serialVersionUID">
			<th>${field.name?html}</th>
			</#if>
			</#list>
			<th>EDIT</th>
		</tr>
	</thead>
	<tbody>
		<#list entities as entity>
		<tr>
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
					${((fieldValue(field, entity)?string['0'])!'')?html}
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
					${(fieldValue(field, entity)?string['0'])!''}
					</#if>

					</td>
					<#break>

				<#case "class java.lang.Boolean">
				<#case "boolean">
					<td>
					${(fieldValue(field, entity)!false)?string('On', 'Off')}
					</td>
					<#break>

				<#case "class org.joda.time.DateTime">
				<#case "class java.util.Date">
					<td>
					${fieldValue(field, entity).toString('yyyy/MM/dd hh:mm')?html}
					</td>
					<#break>
	
				<#default>

				</#switch>

			</#if>
			</#list>

			<td>
				<a href="${springMacroRequestContext.requestUri?html}/${entity.id}" class="btn btn-primary">UPDATE / DELETE</a>
			</td>

		</tr>
		</#list>
	</tbody>
</table>

</#macro>