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