
<#assign pageTitle="" />
<#include "../../utils/common.ftl">
<#include "../common.ftl">
<@layout.html pageTitle=pageTitle>

<#assign path = springMacroRequestContext.requestUri?html />

<div class="row">
	<div class="col-md-12">

		<div>
			<a class="btn btn-primary" role="button" data-toggle="collapse"
				href="#collapseRegisterForm" aria-expanded="false" aria-controls="collapseRegisterForm">
				CREATE
			</a>
			<div class="collapse" id="collapseRegisterForm">
				<form action="${path}" method="POST">
					<@form.generator
						ignoreFieldNames = ["id"] 
						typesMap = { 
						}
					/>
					<button type="submit" class="btn btn-primary">CREATE</button>
				</form>
			</div>
		</div>

		<div class="row">
			<div class="text-center">
				<@pager.pagination items = entities />
			</div>
			<div class="col-md-6">
				<@pageable.optionSelector 
					sortOrSize = 'sort' 
					selectorMap = {
						'id': 'ID ASC', 
						'id,DESC': 'ID DESC'
					}
				/>
			</div>
			<div class="col-md-6">
				<@pageable.optionSelector 
					sortOrSize = 'size' 
					selectorMap = {
						'10': '10', 
						'100': '100' 
					}
				/>
			</div>
		</div>

		<div class="row">

			<div class="col-md-12">
				<@table.generator 
					entities = entities.content 
					fields = fields 
					ignoreFieldNames = [] 
					typesMap = { 
					}
				/>
			</div>

		</div>
	</div>
</div>

</@layout.html>