<#if (actionMessages?exists && actionMessages?size > 0)>
<script type="text/javascript">
	Messenger.options = {
	    extraClasses: 'messenger-fixed messenger-on-top',
	    theme: 'flat'
	}
	Messenger().post({
	  message: '<#list actionMessages as message>${message}<#if message_has_next><br/><#rt/></#if></#list>',
	  type: 'info',
	  hideAfter: 20,
	  showCloseButton: true
	});
</script>
</#if>