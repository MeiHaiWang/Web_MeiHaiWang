<#macro pagination items>
<nav>
	<ul class="pagination">

		<#assign paginationOffset = 6 />

		<#assign query = "?" />

		<#list RequestParameters?keys as key>
			<#if key != 'page'>
				<#assign query = query + key + "=" + RequestParameters[key]?html + "&">
			</#if>
		</#list>

		<#assign path = springMacroRequestContext.requestUri?html + query />

		<#if !items.first>
		<li>
			<a href="${path}" aria-label="Previous">
				<span aria-hidden="true">&laquo;
				</span>
			</a>
		</li>
		</#if>

		<#list (items.number - paginationOffset)..(items.number + paginationOffset) as number>
			<#if number gte 0 && number lt items.totalPages>
				<li <#if number == items.number>class="active"</#if>>
					<a href="${path + 'page=' + number}">${number + 1}</a>
				</li>
			</#if>
		</#list>

		<#if !items.last>
		<li>
			<a href="${path + 'page=' + (items.totalPages - 1)}" aria-label="Next">
				<span aria-hidden="true">&raquo;
				</span>
			</a>
		</li>
		</#if>

	</ul>
</nav>
</#macro>
