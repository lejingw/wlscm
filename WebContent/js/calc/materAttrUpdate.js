/**
 * 初始化数据
 */
function setValues(){
	
}
function setPageContent(){
	var status = jQuery("#status").val();
	if(status!="1"){
		jQuery("#btnSave").attr("disabled", "disabled");
		jQuery("#btnJ").attr("disabled", "disabled");
	}

	loadUpdateMaterPeam("newrebate",jQuery("#rebate").val());	
	loadUpdateMaterPeam("newrebatetype",jQuery("#rebatetype").val());	
	loadUpdateMaterPeam("newrebateamount",jQuery("#rebateamount").val());	
	loadUpdateMaterPeam("newtagtype",jQuery("#tagtype").val());
	loadUpdateMaterPeam("newstringworkprice",jQuery("#newstringworkprice").val());	
	loadUpdateMaterPeam("newspecialworkprice",jQuery("#specialworkprice").val());	
	loadUpdateMaterPeam("newcutwidescale",jQuery("#cutwidescale").val());	
	loadUpdateMaterPeam("newcutdeepscale",jQuery("#cutdeepscale").val());	
	loadUpdateMaterPeam("newpartcontent",jQuery("#partcontent").val());	
	loadUpdateMaterPeam("newornastate",jQuery("#ornastate").val());
}
/**
 * 查找饰品
 * @param type
 */
function loadCode(type){
	var code="";
	if(type=="1")
		code = jQuery("#ornacode").val();
	else
		code = jQuery("#ornabarcode").val();

	showLayer(true);
	MaterAttrUpdateDwr.getMaterCode(code,type,function(mater){
		showLayer(false);
		if(mater==null){
			jQuery("#ornabarcode").val("");
			jQuery("#ornacode").val("");
			alert("不存在此饰品");
		}else
			loadMater(mater);
	});
}
/**
 * 修改时加载属性各个段
 * @param peam
 * @param value
 */
function loadUpdateMaterPeam(peam,value){
//	if(null!=value&&""!=value)
//		jQuery("#"+peam+"").removeAttr("disabled");
//	else
//		jQuery("#"+peam+"").attr("disabled", "disabled");
}
/**
 * 加载饰品属性各个段
 * @param peam
 */
function loadMaterPeam(peam1,peam2,value){
	jQuery("#"+peam1+"").val("");
	jQuery("#"+peam2+"").val("");
	if(null!=value){
		jQuery("#"+peam1+"").val(value);
		//jQuery("#"+peam2+"").removeAttr("disabled");
	}else{
		//jQuery("#"+peam2+"").attr("disabled", "disabled");
	}
}
/**
 * 加载属性验证
 * @param peam
 * @param value
 */
function loadDataTypePeam(peam,value){
	if(null!=value&&""!=value){
		jQuery("#"+peam+"").attr("dataType", "Double");
	}else{
		jQuery("#"+peam+"").attr("dataType", "");
	}
	
}
/**
 * 加载饰品属性
 * @param mater
 */
function loadMater(mater){
	jQuery("#ornabarcode").val(mater.ornaBarcode);
	jQuery("#ornacode").val(mater.ornaCode);
	
	loadMaterPeam("rebate","newrebate",mater.rebate);

	loadMaterPeam("rebatetype","newrebatetype",mater.rebateType);
	
	loadMaterPeam("rebateamount","newrebateamount",mater.rebateAmount);

	loadMaterPeam("tagtype","newtagtype",mater.tagType);		

	loadMaterPeam("stringworkprice","newstringworkprice",mater.stringWorkPrice);
	
	loadMaterPeam("specialworkprice","newspecialworkprice",mater.specialWorkPrice);

	loadMaterPeam("mainshapeid","newmainshapeid",mater.mainShapeId);

	loadMaterPeam("colorid","newcolorid",mater.colorId);
	
	loadMaterPeam("cleanid","newcleanid",mater.cleanId);

	loadMaterPeam("maincolorgradeid","newmaincolorgradeid",mater.mainColorGradeId);
	
	loadMaterPeam("cutid","newcutid",mater.cutId);

	loadMaterPeam("cutwidescale","newcutwidescale",mater.cutWideScale);
	
	loadMaterPeam("cutdeepscale","newcutdeepscale",mater.cutDeepScale);
	
	loadMaterPeam("symmetryid","newsymmetryid",mater.symmetryId);
	
	loadMaterPeam("polishineid","newpolishineid",mater.polishineId);
	
	loadMaterPeam("fluorescenceid","newfluorescenceid",mater.fluorescenceId);	
	
	loadMaterPeam("waistlineid","newwaistlineid",mater.waistlineId);

	loadMaterPeam("vertexid","newvertexid",mater.vertexId);	

	loadMaterPeam("bracketcolorid","newbracketcolorid",mater.bracketcolorId);

	loadMaterPeam("wearid","newwearid",mater.wearId);

	loadMaterPeam("styletypeid","newstyletypeid",mater.styleTypeId);

	loadMaterPeam("partcontent","newpartcontent",mater.partContent);
	
	loadMaterPeam("zodiac","newzodiac",mater.zodiac);
	
	loadMaterPeam("ornastate","newornastate",mater.state);
	
	
	jQuery("#isdbllabel")[0].checked= mater.isDblLabel=="1"?true:false;
	jQuery("#newisdbllabel")[0].checked= mater.isDblLabel=="1"?true:false;
	jQuery("#ismutipart")[0].checked= mater.isMutiPart=="1"?true:false;
	jQuery("#newismutipart")[0].checked= mater.isMutiPart=="1"?true:false;
}

function getBill(status){
	var headObj = {
			billid               :returnNull(jQuery("#billid").val()),//				                  
			billno               :returnNull(jQuery("#billno").val()),//	//		              
			orgid                :returnNull(jQuery("#orgid").val()),//	//		              
			status               :status,//	//		              
			rebate               :returnNull(jQuery("#rebate").val()),//	//		              
			newrebate            :returnNull(jQuery("#newrebate").val()),//	//		          
			rebatetype           :returnNull(jQuery("#rebatetype").val()),//	//		          
			newrebatetype        :returnNull(jQuery("#newrebatetype").val()),//	//		      
			tagtype              :returnNull(jQuery("#tagtype").val()),//	//		            
			newtagtype           :returnNull(jQuery("#newtagtype").val()),//	//		          
			stringworkprice      :returnNull(jQuery("#stringworkprice").val()),//	//		    
			newstringworkprice   :returnNull(jQuery("#newstringworkprice").val()),//	//		  
			specialworkprice     :returnNull(jQuery("#specialworkprice").val()),//	//		    
			newspecialworkprice  :returnNull(jQuery("#newspecialworkprice").val()),//	//		
			ornastate            :returnNull(jQuery("#ornastate").val()),//	//		          
			newornastate         :returnNull(jQuery("#newornastate").val()),//	//		        
			isdbllabel           :returnNull(jQuery("#isdbllabel")[0]==null?null:jQuery("#isdbllabel")[0].checked==false?'0':'1'),	//		 0  1		            
			newisdbllabel        :returnNull(jQuery("#newisdbllabel")[0]==null?null:jQuery("#newisdbllabel")[0].checked==false?'0':'1'),	//		 0  1		        
			mainshapeid          :returnNull(jQuery("#mainshapeid").val()),//	//	          
			newmainshapeid       :returnNull(jQuery("#newmainshapeid").val()),//	//	        
			colorid              :returnNull(jQuery("#colorid").val()),//	//	              
			newcolorid           :returnNull(jQuery("#newcolorid").val()),//	//	            
			cleanid              :returnNull(jQuery("#cleanid").val()),//	//	              
			newcleanid           :returnNull(jQuery("#newcleanid").val()),//	//	            
			maincolorgradeid     :returnNull(jQuery("#maincolorgradeid").val()),//	//	      
			newmaincolorgradeid  :returnNull(jQuery("#newmaincolorgradeid").val()),//	//	  
			cutid                :returnNull(jQuery("#cutid").val()),//	//	                
			newcutid             :returnNull(jQuery("#newcutid").val()),//	//	              
			cutwidescale         :returnNull(jQuery("#cutwidescale").val()),//	//		        
			newcutwidescale      :returnNull(jQuery("#newcutwidescale").val()),//	//		    
			cutdeepscale         :returnNull(jQuery("#cutdeepscale").val()),//	//		        
			newcutdeepscale      :returnNull(jQuery("#newcutdeepscale").val()),//	//		    
			symmetryid           :returnNull(jQuery("#symmetryid").val()),//	//	            
			newsymmetryid        :returnNull(jQuery("#newsymmetryid").val()),//	//	        
			polishineid          :returnNull(jQuery("#polishineid").val()),//	//	          
			newpolishineid       :returnNull(jQuery("#newpolishineid").val()),//	//	        
			fluorescenceid       :returnNull(jQuery("#fluorescenceid").val()),//	//	        
			newfluorescenceid    :returnNull(jQuery("#newfluorescenceid").val()),//	//	    
			waistlineid          :returnNull(jQuery("#waistlineid").val()),//	//	          
			newwaistlineid       :returnNull(jQuery("#newwaistlineid").val()),//	//	        
			vertexid             :returnNull(jQuery("#vertexid").val()),//	//	              
			newvertexid          :returnNull(jQuery("#newvertexid").val()),//	//	          
			bracketcolorid       :returnNull(jQuery("#bracketcolorid").val()),//	//	        
			newbracketcolorid    :returnNull(jQuery("#newbracketcolorid").val()),//	//	    
			wearid               :returnNull(jQuery("#wearid").val()),//	//		              
			newwearid            :returnNull(jQuery("#newwearid").val()),//	//		          
			styletypeid          :returnNull(jQuery("#styletypeid").val()),//	//		        
			newstyletypeid       :returnNull(jQuery("#newstyletypeid").val()),//	//		      
			ismutipart           :returnNull(jQuery("#ismutipart")[0]==null?null:jQuery("#ismutipart")[0].checked==false?'0':'1'),	//     
			newismutipart        :returnNull(jQuery("#newismutipart")[0]==null?null:jQuery("#newismutipart")[0].checked==false?'0':'1'),	//		 0  1   
			partcontent          :returnNull(jQuery("#partcontent").val()),//	//		        
			newpartcontent       :returnNull(jQuery("#newpartcontent").val()),//	//		      
			zodiac               :returnNull(jQuery("#zodiac").val()),//	//		              
			newzodiac            :returnNull(jQuery("#newzodiac").val()),//	//		          
			createdate           :returnNull(jQuery("#createdate").val()),//	//		          
			createid             :returnNull(jQuery("#createid").val()),//	//		            
			updatedate           :returnNull(jQuery("#updatedate").val()),//	//		          
			updateid             :returnNull(jQuery("#updateid").val()),//	//		            
			memo                 :returnNull(jQuery("#memo").val()),//	//		                
			updatereason         :returnNull(jQuery("#updatereason").val()),//	//		        
			rebateamount         :returnNull(jQuery("#rebateamount").val()),//	//		        
			newrebateamount      :returnNull(jQuery("#newrebateamount").val()),//	//	      
			ornacode             :returnNull(jQuery("#ornacode").val()),     
			ornabarcode          :returnNull(jQuery("#ornabarcode").val())                                        
	};
	return headObj;
}
function returnNull(v){
	if(v==""||v==null)
		return null;
	else
		return v;
}
function saveMaterAttrUpdate(status){
	if(Validator.Validate($("frm"),3)){
		confirm("确定保存?", function(){	
			showLayer(true);
    		MaterAttrUpdateDwr.saveOrUpdateMaterAttrUpdate(getBill(status), function(data){
    			showLayer(false);
    			var m = data.split("_");
    			if(m[0]=="2"){
    				alert(m[1]);
    			}else{
    				alert(m[1], function(){
    					window.location = "materAttrUpdate.vm";
    				});
    			}
    		});
    	});
	}
}

function checkNum(str){
	var value = jQuery("#"+str+"").val();
	if(value=="")
		return false;
	else if(Number(value)==0||value=="0"){
		jQuery("#"+str+"").val("");
		return false;
	}else if(Number(value)<0){
		jQuery("#"+str+"").val("");
		return false;
	}
	if(!isDecimal(value)){
		alert("请输入正确的数据");
		jQuery("#"+str+"").val("");
		return true;
	}return false;
}