
<#macro generator ignoreFieldNames=[] typesMap={} imageFieldsMap={}>

	<#list fields as field>
	
		<#if !(ignoreFieldNames?seq_contains(field.name)) && field.name != "serialVersionUID">
		
			<#switch field.type>

				<#case "class java.lang.String">
					<#if imageFieldsMap[field.name]??>

					<div class="fileinput fileinput-new" data-provides="fileinput">
						<div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
							<img src="${(fieldValue(field, entity)!'')?html}" alt="">
						</div>
						<div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;"></div>
						<div>
							<span class="btn btn-default btn-file">
								<span class="fileinput-new">Select image</span>
								<span class="fileinput-exists">Change</span>
								<input type="file" name="${imageFieldsMap[field.name]}" <#if !fieldValue(field, entity)??> required</#if> />
								<input type="hidden" name="entity.${field.name?html}" value="${(fieldValue(field, entity)!'')?html}" <#if !fieldValue(field, entity)??> required</#if> />
							</span>
							<a href="#" class="btn btn-default fileinput-exists" data-dismiss="fileinput">Remove
							</a>
						</div>
					</div>

					<#else>
					<div class="form-group">
						<label for="${field.name?html}">${field.name?html}</label>
						<input id="${field.name?html}" type="text" name="entity.${field.name?html}" class="form-control" value="${(fieldValue(field, entity)!'')?html}" required/>
					</div>
					</#if>
					<#break>

				<#case "class java.lang.Integer">
				<#case "int">
					<div class="form-group">
						<label for="${field.name}">${field.name}</label>
						<input id="${field.name}" type="number" name="entity.${field.name}" class="form-control" value="${(fieldValue(field, entity)?string['0'])!''}" required/>
					</div>
					<#break>

				<#case "class java.lang.Long">
				<#case "long">
					<div class="form-group">
						<label for="${field.name}">${field.name}</label>
						<input id="${field.name}" type="number" name="entity.${field.name}" class="form-control" value="${(fieldValue(field, entity)?string['0'])!''}" required/>
					</div>
					<#break>

				<#case "class java.lang.Boolean">
				<#case "boolean">
					${field.name}
					<div class="checkbox">
						<label for="${field.name}">
							<input id="${field.name}" type="checkbox" name="entity.${field.name}" 
								value="1" <#if fieldValue(field, entity)!false>checked</#if> data-toggle="toggle"/>
						</label>
					</div>
					<#break>

				<#case "class org.joda.time.DateTime">
				<#case "class java.util.Date">
					<div class="form-group">
						<label for="${field.name}">${field.name}</label>
						<input id="${field.name}" type="text" name="entity.${field.name}" class="form-control" value="${(fieldValue(field, entity).toString('yyyy/MM/dd hh:mm'))!}" placeholder="format: yyyy/MM/dd hh:mm" required/>
					</div>
					<#break>

			</#switch>

		</#if>
	</#list>

</#macro>