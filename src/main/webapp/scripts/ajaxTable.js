/**
 * 异步搜索类
 * @param url 列表action地址
 * @param params 参数,json格式。如{"name":"test","age":25}
 * @param contentDiv 返回的结果填充至哪个div
 * @author guojt
 * @since 2012-02-09
 *                                                                           
 */
function AjaxTable(url,contentDiv){
	this.url = url;
	this.params = {}
	this.contentDiv = contentDiv;
	this.pageNo = 1;	//当前页
	this.orderBy = "";	//排序字段
	this.order = "";	//排序方式
	this.pageObjName = "page";	//分页对象的名称，默认为page
	this.isFirstSearch = false;
}

//得到参数
AjaxTable.prototype.getParams = function(){
	var divId = this.contentDiv;
	this.params ={
			"history.rid":$("#"+divId+" #rid").val(),
			"history.omkey":$("#"+divId+" #omkey").val(),
			"history.uniteRid":$("#"+divId+" #uniteRid").val(),
			"history.uniteOmkey":$("#"+divId+" #uniteOmkey").val()
		}
}

//执行搜索
AjaxTable.prototype.search = function(){
	var divId = this.contentDiv;
	
	//设置loading图片
	//$("#"+divId).html("<img src='" + basePath + "/images/loading_blue.gif'/>数据处理中，请稍后...");
	//ZENG.msgbox.show("数据处理中，请稍后...", 6,60000);
	//添加分页参数
	this.getParams();
	this.params = this.addPagingParams(this.params);
	$.ajax({
		/*不使用缓存*/
		url: this.url+"?d="+new Date(),
		type: "GET",
		data: this.params,
		dataType: "html",
		success: function(data, textStatus) {
			//无法在此使用this关键字，如this.contentDiv的值为undefined
			
			//填充返回数据
			$("#"+divId).html(data);
			//$("#"+divId).show();
			//ZENG.msgbox.hide();
		},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			//ZENG.msgbox.show("查询失败。状态：" + textStatus, 5, 3000);
		}
	});
}

//上一页/下一页
AjaxTable.prototype.jumpPage = function(pageNo, totalPages) {
	if(pageNo!=''){
		if(!isNaN(parseInt(pageNo,10))){
			if(pageNo > totalPages)
				pageNo = totalPages;
			this.pageNo = pageNo;
			this.isFirstSearch = false;
			this.search();
		}else{
			$("#jumpPageNo").val("");
		}
	}
}

//列排序
AjaxTable.prototype.sort = function(orderBy, order) {
	this.orderBy = orderBy;
	
	if (this.order == "") {
		this.order = order.toLowerCase();
	
	}else if (this.order == "desc") {
		this.order = "asc";
	
	}else{
		this.order = "desc";
	}
	this.isFirstSearch = false;
	this.search();
}

//添加分页参数
AjaxTable.prototype.addPagingParams = function(params){
	params[this.pageObjName+".pageNo"] = this.pageNo;
	if(this.orderBy != ''){
		params[this.pageObjName+".orderBy"] = this.orderBy;
	}
	if(this.order != ''){
		params[this.pageObjName+".order"] = this.order;
	}
	params["isFirstSearch"] = this.isFirstSearch;
	return params;
}

//只能输入数字
function onlyInt(obj){
	if(obj.value==0){
		obj.value = "";
	}
	obj.value=obj.value.replace(/[^\d]/g,'');
}