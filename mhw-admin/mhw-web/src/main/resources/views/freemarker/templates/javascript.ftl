<script>
(function($){
	$(function(){
		var $forms = $('form[method=POST]');
		if($forms) {
			$.each($forms, function(index, form) {
				$(form).prepend(
					'<input type="hidden" name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" />'
				);
			});
		}
	});
})(jQuery);
</script>

<script src="//cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.0/js/bootstrap-toggle.min.js"></script>
<script>
(function($){
	$(function(){
		$('select').select2();
	});
})(jQuery);
</script>