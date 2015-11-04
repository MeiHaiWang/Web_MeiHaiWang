<#assign isNavbar = true />
<div class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>

				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Cosplay管理つ〜る</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<#assign
				tablesMap = {
					"tClaim":"tClaim",
					"tComment":"tComment",
					"tCoupon":"tCoupon",
					"tEvaluation":"tEvaluation",
					"tHairsalonmaster":"tHairsalonmaster",
					"tHairstyle":"tHairstyle",
					"tImage":"tImage",
					"tMasterarea":"tMasterarea",
					"tMastercountry":"tMastercountry",
					"tMastercouponkind":"tMastercouponkind",
					"tMasterhairtype":"tMasterhairtype",
					"tMastermenucategory":"tMastermenucategory",
					"tMasternews":"tMasternews",
					"tMasterrecommend":"tMasterrecommend",
					"tMastersearchcondition":"tMastersearchcondition",
					"tMastersearchconditiontitle":"tMastersearchconditiontitle",
					"tMastersearchconditiontype":"tMastersearchconditiontype",
					"tMenu":"tMenu",
					"tReservation":"tReservation",
					"tReview":"tReview",
					"tSeat":"tSeat",
					"tStylist":"tStylist",
					"tUser":"tUser"
				}
			/>
			<ul class="nav navbar-nav">

				<#assign path = springMacroRequestContext.requestUri />
				<#list tablesMap?keys as key>
				<li <#if (path?split('/')[2])! == key>class="active"</#if>>
					<a href="/admin/${key}">${tablesMap[key]}</a>
				</li>
				</#list>

				<li>
					<a href="#" onclick="$('form[name=logout_head]').submit(); return false;">logout</a>
					<form name="logout_head" action="/auth/logout" method="POST"></form>
				</li>

			</ul>
		</div><!--/.nav-collapse -->
	</div><!--/.container-fluid -->
</div>
