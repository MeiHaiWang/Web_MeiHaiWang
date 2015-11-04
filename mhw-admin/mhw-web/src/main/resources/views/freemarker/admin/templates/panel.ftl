
<#macro generator ignoreFieldNames typesMap>

	<#list fields as field>
	
		<#if !(ignoreFieldNames?seq_contains(field.name)) && field.name != "serialVersionUID">
		
			<#switch field.type>

				<#case "class java.lang.String">
					<div class="form-group">
						<label for="${field.name?html}">${field.name?html}</label>
						<#assign str = (fieldValue(field, entity)!'')?html />
						<#if str?contains('jpg')>
						<div><a href="${str}" target="_blank"><img src="${str}" style="height:100px;"/></a></div>
						<#else>
						<div>${str}</div>
						</#if>
					</div>
					<#break>

				<#case "class java.lang.Integer">
				<#case "int">
					<div class="form-group">
						<label for="${field.name}">${field.name}</label>
						<div>${(fieldValue(field, entity)?string['0'])!''}</div>
					</div>
					<#break>

				<#case "class java.lang.Long">
				<#case "long">
					<div class="form-group">
						<label for="${field.name}">${field.name}</label>
						<#assign selectedId = fieldValue(field, entity)!1 />
						<#list typesMap[field.name] as type>
							<#if selectedId == type.id>
								<div>${type?html}</div>
							</#if>
						</#list>
					</div>

				<#case "class java.lang.Boolean">
				<#case "boolean">
					${field.name}
					<div class="checkbox disabled">
						<label for="${field.name}">
							<input id="${field.name}" type="checkbox" name="entity.${field.name}" 
								value="1" <#if fieldValue(field, entity)!false>checked</#if> data-toggle="toggle" disabled/>
						</label>
					</div>
					<#break>

			</#switch>

		</#if>
	</#list>

</#macro>