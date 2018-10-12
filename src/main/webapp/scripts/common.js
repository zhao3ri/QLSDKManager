//日期控件背景处理
$('.date input').css("background-color","white");

//帮助提示控件
$('.helpToolTip').popover({
		placement:'top',
		trigger:'manual',
		trigger:'hover'
 });

$('.helpToolTip').css("color","#39B3D7");
$('.helpToolTip').css("cursor","pointer");
$('.helpToolTip').addClass("icon-question-sign");