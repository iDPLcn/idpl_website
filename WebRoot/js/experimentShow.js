Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};
function loadtime(){
	var tables=document.getElementsByTagName("table");
	var tables_number=tables.length;
	var table;
	var rows;	
	var startRunning;
	var completedTime;
	var date;
	for(var i=0;i<tables_number-3;i++)
	{
		rows=tables[i].rows;
		startRunning=rows[1].cells[1].innerHTML;
		completedTime=rows[1].cells[2].innerHTML;
		date		=rows[1].cells[3].innerHTML;
		if(startRunning!="")
		rows[1].cells[1].innerHTML = new Date(startRunning*1000).Format("yyyy-MM-dd hh:mm:ss");
		if(completedTime!="")
		rows[1].cells[2].innerHTML = new Date(completedTime*1000).Format("yyyy-MM-dd hh:mm:ss");
		if(date!="")
		rows[1].cells[3].innerHTML = new Date(date*1000).Format("yyyy-MM-dd hh:mm:ss");	
	}
}
