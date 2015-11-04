
<#macro html pageTitle="">
<!doctype html>
<html lang="ja">

	<#include "head.ftl"/>
	<body>
		<div class="container">

			<#include "./validateMessagePanel.ftl" />

			<#include "./nav/nav.ftl"/>

			<#nested />

		</div>
		<#include "footer.ftl"/>
	</body>
</html>
</#macro>
