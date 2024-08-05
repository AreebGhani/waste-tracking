function initADLookup(divName){
	//create markup
		document.getElementById(divName).innerHTML = "<div class='hd'>County Employee Search:</div>" +
				"<div class='bd'><div id='searchForm'></div><div id='searchResults'></div></div>";
				
	//instantiate user lookup dialog
		
		var adLookupContinue = function() {
			YAHOO.ad.container.lookup.hide();
		}
	
		YAHOO.namespace("ad.container");	
		document.getElementById(divName).style.display="block";
		YAHOO.ad.container.lookup = new YAHOO.widget.Dialog(divName, 
									{ width : "600px",
									  fixedcenter : true,
									  visible : false, 
									  close: true,
									  zindex:99,
									  modal:true,
									  draggable:true,
									  effect:{effect:YAHOO.widget.ContainerEffect.FADE,duration:0.15},
									  constraintoviewport : true,
									  buttons : [ { text:"Close", handler:adLookupContinue }]
									});
	
		// Render the Dialog
		YAHOO.ad.container.lookup.render();
		YAHOO.ad.container.lookup.hide();	
		
}

function ShowADLookup(userIdTarget,nameTarget,emailTarget,phoneTarget){
	document.getElementById('searchForm').innerHTML = "Search for a County Employee by entering all or part of their name in the input box below and clicking on the magnifying glass.  Choose from the results by clicking on the name." + 
	"<br><br><input type='text' size='85' id='searchString'/>&nbsp;<img title='Search' style='border:0;cursor:pointer;' src='../images/magnifier.png' onClick='javascript:ADLookup(document.getElementById(\"searchString\").value,\"" + userIdTarget + "\",\"" + nameTarget + "\",\"" + emailTarget + "\",\"" + phoneTarget + "\");'/><br>" +
	"<center><div id='loadingDiv' style='display:none;'><img src='http://aacoprod.aacounty.org/AACOUtils/images/loading.gif'></div></center>";
	document.getElementById('searchResults').style.display = 'none';
	YAHOO.ad.container.lookup.show();
}


function sortNames(a,b) {
	if(a.Name > b.Name){
		return 1;
	}
	else if(a.Name < b.Name){
		return -1;
	}
	else{
		return 0;
	}
}

function ADLookup(searchString, userIdTarget, nameTarget, emailTarget, phoneTarget){
   	document.getElementById('loadingDiv').style.display = 'inline';
	
	var url = "http://aacoprod-intra/AACOUtils/searchUsersByName.do?searchString=" + searchString;
	var meth = "GET";
	
	var onSuccessFunc = function (req) {
		document.getElementById('searchResults').style.display = 'inline';
		var response = req.responseText;
		if (window.DOMParser){
			parser=new DOMParser();
			var doc=parser.parseFromString(response,"text/xml");
		}
		else{ // Internet Explorer
			var doc=new ActiveXObject("Microsoft.XMLDOM");
		  	doc.async="false";
		  	doc.loadXML(response); 
		} 
		
		var z=doc.getElementsByTagName('item');	
		var adArray = [];
		
		if (z.length == 0){
			document.getElementById('searchResults').innerHTML = "No results found";
		}
		else{	
			for (i=0;i<z.length;i++) {
				var adData= {Name: z[i].childNodes[1].childNodes[0].nodeValue, UserId:z[i].childNodes[0].childNodes[0].nodeValue, Email:z[i].childNodes[2].childNodes[0].nodeValue, Phone:z[i].childNodes[3].childNodes[0].nodeValue};
				adArray.push(adData);
			}
			
			var formatName = function(elCell, oRecord, oColumn, sData) {
				if(!emailTarget && !phoneTarget){
					elCell.innerHTML = "<a href='javascript:" + userIdTarget + ".value = \"" + oRecord.getData('UserId') + "\";" + nameTarget + ".value = \"" + sData + "\";YAHOO.ad.container.lookup.hide();'>" + sData + "</a>";
				}
				else if(!emailTarget){
					elCell.innerHTML = "<a href='javascript:" + userIdTarget + ".value = \"" + oRecord.getData('UserId') + "\";" + nameTarget + ".value = \"" + sData + "\";" + phoneTarget + ".value = \"" + oRecord.getData('Phone') + "\";YAHOO.ad.container.lookup.hide();'>" + sData + "</a>";
				}
				else if(!phoneTarget){
					elCell.innerHTML = "<a href='javascript:" + userIdTarget + ".value = \"" + oRecord.getData('UserId') + "\";" + nameTarget + ".value = \"" + sData + "\";" + emailTarget + ".value = \"" + oRecord.getData('Email') + "\";YAHOO.ad.container.lookup.hide();'>" + sData + "</a>";
				}
				else{
					elCell.innerHTML = "<a href='javascript:" + userIdTarget + ".value = \"" + oRecord.getData('UserId') + "\";" + nameTarget + ".value = \"" + sData + "\";" + emailTarget + ".value = \"" + oRecord.getData('Email') + "\";" + phoneTarget + ".value = \"" + oRecord.getData('Phone') + "\";YAHOO.ad.container.lookup.hide();'>" + sData + "</a>";
				}
			};
			
			var adColumnDefs = [   
			          			 {key:"Name", label:"Name",sortable:true, formatter:formatName, resizeable:false,width:150}, 
			          			 {key:"UserId", label:"User ID",sortable:true,resizeable:false,width:100},
			          			 {key:"Email", label:"Email",sortable:true, resizeable:false,width:200},
			          		     {key:"Phone", label:"Phone #",sortable:true, resizeable:false,width:100}
			          		     
			          		 ];   
			             
	  		 var adDataSource = new YAHOO.util.DataSource(adArray);   
	  		 adDataSource.responseType = YAHOO.util.DataSource.TYPE_JSARRAY;   
	  		 adDataSource.responseSchema = {    fields: ["Name","UserId","Email","Phone"]   };    
		     adDataSource.doBeforeCallback = function (oRequest, oFullResponse, oParsedResponse, oCallback) {
	              oParsedResponse.results.sort(sortNames);
	              document.getElementById('loadingDiv').style.display = 'none';
	              return oParsedResponse;
	         };
	  		 
	  		 var adDataTable = new YAHOO.widget.ScrollingDataTable(searchResults,   adColumnDefs, adDataSource, {height:"10em"});   
	  		 
	  		 return {  oDS: adDataSource,   oDT: adDataTable  };
		}
	}
	
	var onErrorFunc = function (status) { 
		alert("AJAX error: " + status + ":" + url); 
	};
			
	var myAjax = new AJAXRequest(url, meth);
	myAjax.makeRequest(onSuccessFunc, onErrorFunc);
}


function ADGetName(searchString, target){
	var url = "http://aacoprod-intra/AACOUtils/searchUsersById.do?searchString=" + searchString;
	var meth = "GET";
	
	var onSuccessFunc = function (req) {
		var response = req.responseText;
		if (window.DOMParser){
			parser=new DOMParser();
			var doc=parser.parseFromString(response,"text/xml");
		}
		else{ // Internet Explorer
			var doc=new ActiveXObject("Microsoft.XMLDOM");
		  	doc.async="false";
		  	doc.loadXML(response); 
		} 
		
		var z=doc.getElementsByTagName('item');	
		
		if (z.length == 0){
			target.innerHTML = "unknown";
		}
		else{	
			target.innerHTML = z[0].childNodes[1].childNodes[0].nodeValue;
		}	
	}
	
	var onErrorFunc = function (status) { 
		alert("AJAX error: " + status + ":" + url); 
	};
			
	var myAjax = new AJAXRequest(url, meth);
	myAjax.makeRequest(onSuccessFunc, onErrorFunc);
}
