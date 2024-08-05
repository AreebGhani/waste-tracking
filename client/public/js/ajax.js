AJAXRequest = function (url, method)
{
	this.url = url;
	if(method != "POST") this.method = "GET";
	else this.method = "POST";
}


AJAXRequest.prototype.makeRequest = function(onSuccess, onError)
{
	this.onSuccess = onSuccess;
	this.onError = onError;
	var pointer = this;

	if (window.XMLHttpRequest)
	{
		// FOR MOST BROWSERS (OTHER THAN IE)
		this.req = new XMLHttpRequest();
		this.req.onreadystatechange = function () { pointer.processReqChange() };
		this.req.open(this.method, this.url, true); //
		this.req.send(null);
	}
	else if (window.ActiveXObject)
	{
		// FOR IE
		this.req = new ActiveXObject("Microsoft.XMLHTTP");
		if (this.req)
		{
			this.req.onreadystatechange = function () { pointer.processReqChange() };
			this.req.open(this.method, this.url, true);
			this.req.send();
		}
	}
}

AJAXRequest.prototype.processReqChange = function()
{
	if (this.req.readyState == 4)
	{
		if (this.req.status == 200) this.onSuccess(this.req);
		else this.onError(this.req.status);
	}
}

// Changes XML to JSON
function xmlToJson(xml) {
	alert("got to xmltoJson");
	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
		obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	if (xml.hasChildNodes()) {
		for(var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof(obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof(obj[nodeName].length) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
};
