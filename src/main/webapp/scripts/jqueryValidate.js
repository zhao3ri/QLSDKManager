(function($) {
	  var validator_defaults_dup = $.extend({}, $.validator.defaults);
	  $.extend($.validator.defaults, {
		  errorPlacement: function(error, element) {  
		    element.attr("data-content",error.text());
		    element
		    .popover({
		   		placement:'top',
		   		trigger:'manual'
		    })
		    .popover('show');
		},
		highlight: function (element) { // hightlight error inputs
		    $(element)
		    .closest('.form-group').removeClass('has-success').addClass('has-error'); // set error class to the control group
		},
		
		unhighlight: function (element) { // revert the change dony by hightlight
		    $(element)
		    .popover('destroy')
		    .closest('.form-group').removeClass('has-error').addClass('has-success'); // set error class to the control group
		}
	  });
})(jQuery);

function removeMessage(element){
	element.popover('destroy')
    .closest('.form-group').removeClass('has-error').addClass('has-success');
}

var t=true;
function valMessage(element,status){
	element.bind("change",function(){
		t=true;
	});
    t=status;
    element.blur();
}

function appendValidate(){
	
	// 中文的验证 
	jQuery.validator.addMethod("chinese", function(value, element) { 
		var chinese = /^[\u4e00-\u9fa5]+$/; 
		return this.optional(element) || (chinese.test(value)); 
	}, "只能输入中文");
	
	// 非中文的验证 
	jQuery.validator.addMethod("nochinese", function(value, element) { 
		var chinese = /^[a-zA-Z0-9\.]+$/; 
		return this.optional(element) || (chinese.test(value)); 
	}, "不能输入中文");
	
	// 非中文的验证 
	jQuery.validator.addMethod("qqmsn", function(value, element) { 
		var chinese = /^[a-zA-Z0-9\.@_]+$/; 
		return this.optional(element) || (chinese.test(value)); 
	}, "不能输入特殊字符");
	
	// 渠道
	jQuery.validator.addMethod("channelType", function(value, element) { 
		var chinese = /^[a-zA-Z0-9]+$/; 
		return this.optional(element) || (chinese.test(value)); 
	}, "只能填写a-zA-Z0-9");
	
	//验证13系列和150-159(154除外)、180、185、186、187、188、189几种号码，长度11位
	$.validator.addMethod("mobile" ,function(value, element)  {       
	     var length = value.length;   
	     var mobile = /^((13\d{9})|(15[0-35-9]\d{8})|(18[05-9]\d{8}))$/;   
	     return   this .optional(element)||(length == 11 && mobile.test(value));       
	},'手机号码格式不正确'); 
  
	// 固话号码验证  分机号-(\d{4}|\d{3}|\d{2}|\d{1})
	jQuery.validator.addMethod("phone", function(value, element) { 
		if(value.indexOf(' ')!=-1){
			return false;
		}	
		var tel = /^(\d{3}-\d{7}|\d{4}-\d{8}|\d{3}-\d{8}|\d{4}-\d{7}|\d{7}|\d{8})$/; 
		var tel2 = /^(\d{3}-\d{7}-(\d{4}|\d{3}|\d{2}|\d{1})|\d{4}-\d{8}-(\d{4}|\d{3}|\d{2}|\d{1})|\d{3}-\d{8}-(\d{4}|\d{3}|\d{2}|\d{1})|\d{4}-\d{7}-(\d{4}|\d{3}|\d{2}|\d{1})|\d{7}-(\d{4}|\d{3}|\d{2}|\d{1})|\d{8}-(\d{4}|\d{3}|\d{2}|\d{1}))$/; 
		return (this.optional(element)||(tel.test(value))) || (this.optional(element)||(tel2.test(value))); 
	}, "电话号码格式错误"); 
	
	// 邮政编码验证 
	jQuery.validator.addMethod("zipCode", function(value, element) { 
		var tel = /^[0-9]{6}$/; 
		return this.optional(element) || (tel.test(value)); 
	}, "邮政编码格式错误"); 
	
	// QQ号码验证 
	jQuery.validator.addMethod("qq", function(value, element) { 
		var tel = /^[1-9]\d{4,9}$/; 
		return this.optional(element) || (tel.test(value)); 
	}, "qq号码格式错误"); 
	
	//excel file验证
	jQuery.validator.addMethod("isExcel", function(value, element) { 
	    var excelFile = /^.*?\.xls$/;
	    return excelFile.test(value);	
	}, "请选择Excel(.xls)文件"); 
	
	jQuery.validator.addMethod("isIdCardNo", function(value, element) {     
        return this.optional(element) || checkidcard(value);     
    }, " 请正确输入您的身份证号码");
    
	//图片后缀验证
	jQuery.validator.addMethod("isPic", function(value, element) {
	    var jpg = /^.*?\.((JPG)|(jpg))$/;
	    var png = /^.*?\.((PNG)|(png))$/;
	    var bmp = /^.*?\.((BMP)|(bmp))$/;
	    var gif = /^.*?\.((GIF)|(gif))$/;
		return this.optional(element) || (jpg.test(value)||png.test(value)||bmp.test(value)||gif.test(value));  
	}, "图片格式不正确.(允许格式：jpg,png,bmp,gif)"); 	
	
	//选择日期不大于当前日期校验
	jQuery.validator.addMethod(
			"beforeToday",
			function(value, element, params){
				var valueDate = new Date(value);
				if(valueDate.getTime() > params.getTime()){
					return false;
				}
				return true;
			},
			"选中日期不能大于当前时间"
		);
		
	//字符串中不可只有空格
	jQuery.validator.addMethod(
		"notOnlyBlank",
		function(value, element, params){
			if(value.indexOf(" ") != -1 && $.trim(value) == ""){
				return false;
			}
			else{
				return true;
			}
		},
		"不能输入空字符"
	);
	
	//正负整数验证
	jQuery.validator.addMethod(
		"integer",
		function(value, element){
			var tel = /^-?\d+$/; 
			return this.optional(element) || (tel.test(value)); 
		},
		"只能输入整数"
	);
}

function checkidcard(num){  
    var len = num.length, re;  
    if (len == 15)  
        re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{3})$/);  
    else if (len == 18)  
        re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\d)$/);  
    else{  
        //alert(" 请输入15或18位身份证号,您输入的是 "+len+ "位");   
        return false;  
    }  
    var a = num.match(re);  
    if (a != null){  
        if (len==15){  
            var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]);  
            var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];  
        }else{  
            var D = new Date(a[3]+"/"+a[4]+"/"+a[5]);  
            var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5];  
        }  
        if (!B){  
            //alert(" 输入的身份证号 "+ a[0] +" 里出生日期不对！");   
            return false;  
        }  
    }  else{
    	return false;
    }
    return true;  
}  