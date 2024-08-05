/**
 * 
 */

function getHostName() {
	var x = window.location.hostname;

	x2 = x.substring(0,2).toUpperCase();
	
	if (x2 == "IT") {
		return "//" + x + ":8080";
	}
	else {
		return "//" + x;
	}
}

function HtmlEncode(s)
	{
	  var el = document.createElement("div");
	  el.innerText = el.textContent = s;
	  s = el.innerHTML;
	  return s;
	}

// Ajax cleanup
function decode(val){
	return decodeURI(val)
	}

function encode(val){
    var eVal;
    if(!encodeURIComponent){
        eVal=escape(val);
        eVal=eVal.replace(/@/g,"%40");
        eVal=eVal.replace(/\//g,"%2F");
        eVal=eVal.replace(/\+/g,"%2B");
        eVal=eVal.replace(/'/g,"%60");
        eVal=eVal.replace(/"/g,"%22");
        eVal=eVal.replace(/`/g,"%27");
        eVal=eVal.replace(/&/g,"%26");
    }
    else{
        eVal=encodeURIComponent(val);
        eVal=eVal.replace(/~/g,"%7E");
        eVal=eVal.replace(/!/g,"%21");
        eVal=eVal.replace(/\(/g,"%28");
        eVal=eVal.replace(/\)/g,"%29");
        eVal=eVal.replace(/'/g,"%27");
        eVal=eVal.replace(/"/g,"%22");
        eVal=eVal.replace(/`/g,"%27");
        eVal=eVal.replace(/&/g,"%26");
    }
    return eVal.replace(/\%20/g,"+");
	}

function cleanTable(newTable){		
     newTable = newTable.replace(/<[a|A][^>]*>|<\/[a|A]>/g,"");
	 newTable = newTable.replace("<label", "<div");
	 newTable = newTable.replace("<LABEL", "<div");
	 newTable = newTable.replace("<input", "<div");
	 newTable = newTable.replace("<INPUT", "<div");
	 newTable = newTable.replace("</label>", "</div>");
	 newTable = newTable.replace("</LABEL>", "</div>");
	 newTable = newTable.replace("Search:","");
	 newTable = newTable.replace("SEARCH:","");
	 return newTable;                
	}