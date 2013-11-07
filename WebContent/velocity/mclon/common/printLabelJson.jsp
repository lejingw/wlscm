<%@page import="com.jatools.web.util.BdCommon"%>
<%@page import="com.jatools.common.Global"%>
<%@ page  contentType="text/html;charset=UTF-8"%>
<html>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<head>
	<title>打印条码</title>
<OBJECT id=PrintCtrl align="CENTER" WIDTH=300 HEIGHT=20 classid="CLSID:0201B4C8-2EA4-4973-BB2C-1D6672AAD90D"></OBJECT> 

<link href="<%=Global.CONTEXT%>/script/mclon/tablecloth/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="<%=Global.CONTEXT%>/script/mclon/tablecloth/tablecloth.js"></script>
<script type="text/javascript" src="<%=BdCommon.getParameterValue("print_btw_path")%>printLabelJson.js" charset="UTF-8"></script>

<script language="javascript" for= 'PrintCtrl' event='OnPrintFinished'> 

 //alert("BarPrint() 执行完成!");

</script>

<script language="javascript">
	var aajj;
	var is_init=0;
	
	function loadJS(_url){
	    var script=document.createElement("script");
	    script.type='text/javascript';
	    script.language='javascript';
	    script.src=_url;
	    document.getElementsByTagName("head")[0].appendChild(script);
    }
	
	function jsonpCallback(result) {
      	aajj=result;
		is_init=1;
		initShow();
 	}
	

	var aajj=<%=request.getAttribute("LIST_FOR_BARCODE_JSON")%>;
	var scm_data=<%=request.getParameter("LIST_FOR_BARCODE_JSON")%>;
	var index_print=window.location.href.indexOf('printUrl');
	
	var ic=5;
	if(aajj!=null){
		is_init=1;
	}else if(scm_data!=null){
		aajj=scm_data;
		is_init=1;
	}else if(index_print!=-1){
		var printUrl=window.location.href.substring(window.location.href.indexOf('printUrl')+9);
		loadJS(printUrl+'&fn=jsonpCallback');
	}
	
	function doPrint() 
	{ 
	    var printType=<%=request.getParameter("pt") %>;
	    var params = "<%=request.getParameter("params") %>";
		var os = params.split("-");
		
		var pathstr=window.location.protocol+'//'+window.location.host+"<%=BdCommon.getParameterValue("print_btw_path")%>";
		if(os[os.length-1]=='0'&&printType==1){
			printType=4;
		}
		var data=getDateUrlArray(aajj,printType);
		var cmd = '';// '"C:\\Program Files\\Seagull\\BarTender\\7.75\\Bartend.exe" /F=%1. /D=%2. /P /X';
		var params = '';
		
		PrintCtrl.ClearDataCache();
		for(var i=0;i<data.length;i++){
			var temp=data[i];
			var btwName=getFormatBtw(temp[0],printType);
			if(btwName==null||btwName==""||temp[1]==""){
				continue;
			}
			PrintCtrl.LoadFormatFile(pathstr+btwName); 
			PrintCtrl.LoadData(temp[1]);
			PrintCtrl.ExecuteCommand(cmd, params); 
		}
	}
	
	/*function clock() {
		ic = ic - 1
		document.title = "本窗口将在" + ic + "秒后自动关闭!";
		if (ic > 0)
			setTimeout("clock();", 1000);
		else {
			window.opener=null
			window.open('','_self');
			window.close();
		}
		setTimeout(function(){window.opener=null;window.close();}, 3000);
	}*/
	function doClrCache() {
		var sum = PrintCtrl.ClearFormatCache();
		PrintCtrl.ClearDataCache();
		alert("缓存已清除");
	}

	function closeself() {
		window.close();
	}

	function initShow() {
		if (is_init != 1)
			return;
		var tbt = document.getElementById("tbt");
		if (tbt == null || tbt.rows.length > 0)
			return;

		if (aajj == null)
			aajj = [];
		else
			aajj = aajj.lines;

		for ( var i = 0; i < aajj.length; i++) {
			var o = aajj[i];
			var r = tbt.insertRow(0);
			with (o) {
				r.insertCell(0).innerHTML = getCellHtml(labelType);
				r.insertCell(1).innerHTML = getCellHtml(ornaCode);
				r.insertCell(2).innerHTML = getCellHtml(ornaName);
				r.insertCell(3).innerHTML = getCellHtml(posAmount);
				r.insertCell(4).innerHTML = getCellHtml(mainWeight);
				r.insertCell(5).innerHTML = getCellHtml(partStoneWeight);
				r.insertCell(6).innerHTML = getCellHtml(allQty);
				r.insertCell(7).innerHTML = getCellHtml(specialWeight);
				r.insertCell(8).innerHTML = getCellHtml(sizeName);
				r.insertCell(9).innerHTML = getCellHtml(quality);
				r.insertCell(10).innerHTML = getCellHtml(specialWorkPrice);
				r.insertCell(11).innerHTML = getCellHtml(identNo);
				r.insertCell(12).innerHTML = getCellHtml(mainShape);
				r.insertCell(13).innerHTML = getCellHtml(partStoneContent);
				r.insertCell(14).innerHTML = getCellHtml(cleanName);
				r.insertCell(15).innerHTML = getCellHtml(color);
				r.insertCell(16).innerHTML = getCellHtml(factoryStyle);
				r.insertCell(17).innerHTML = getCellHtml(venderCode);
				r.insertCell(18).innerHTML = getCellHtml(grian);
				r.insertCell(19).innerHTML = getCellHtml('否');
				r.insertCell(20).innerHTML = getCellHtml(colorGrade);
				r.insertCell(21).innerHTML = getCellHtml(analysisArange);
				r.insertCell(22).innerHTML = getCellHtml(stringWorkPrice);//11.07.25 红绳工费
			}
		}
	}
	function getCellHtml(value) {
		return "<div style='white-space:nowrap;'>" + value + "</div>"
	}
	window.onload = function() {
		initShow();
		tablecloth();
	}
</script>
</head>
<body >
	<form name="frm" action="" method="POST">
	<table id="buttonline"><tr>
		 <td><input type="button" value="关闭" name="sub" onClick="closeself()">
		  <input  type="button" name="print" onClick="doPrint()" value="标签打印" >
		  <input  type="button" name="clearCache" onClick="doClrCache()" value="清除缓存" ></td></tr>
		 <tr>
	</table>
		<table width="100%">
			<tr>
				<td>
					<table id="tabobj" class="list">
						<thead>
							<tr>
							    <th nowrap="nowrap">标签类型</th>
							    <th nowrap="nowrap">饰品编码</th>
								<th nowrap="nowrap">饰品名称</th>
								<th nowrap="nowrap">位置金额</th>
								<th nowrap="nowrap">主石重量</th>
								<th nowrap="nowrap">配石重量</th>
								<th nowrap="nowrap">饰品总重</th>
								<th nowrap="nowrap">特殊重量</th>
								<th nowrap="nowrap">饰品尺寸</th>
								<th nowrap="nowrap">成色</th>
								<th nowrap="nowrap">特殊工费</th>
								<th nowrap="nowrap">鉴定证书</th> 
								<th nowrap="nowrap">主石形状</th> 
								<th nowrap="nowrap">配石内容</th> 
								<th nowrap="nowrap">净度</th> 
								<th nowrap="nowrap">颜色</th> 
								<th nowrap="nowrap">款号</th> 
								<th nowrap="nowrap">供应商</th> 
								<th nowrap="nowrap">总粒数</th> 
								<th nowrap="nowrap">是否打印重量</th>
								<th nowrap="nowrap">色级</th>
								<th nowrap="nowrap">分析范围</th>
								<th nowrap="nowrap">红绳工费</th>
							</tr>
						</thead>
						<tbody id='tbt'>
 
						</tbody>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
