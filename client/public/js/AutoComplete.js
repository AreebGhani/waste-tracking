LoadAutoComplete = function (options, schema) {
	var schemaArray = new Array();
	
	this.oP = (options) ? options : {};
	
	for (var i=0; i < schema.length; i++) { 
		schemaArray.push(schema[i]);
	}

	var myServer = this.oP.server;   
	var myDataSource = new YAHOO.widget.DS_XHR(myServer, schemaArray);
	myDataSource.responseType = YAHOO.widget.DS_XHR.TYPE_XML;
	myDataSource.scriptQueryParam = this.oP.param;

	var myAutoComp = new YAHOO.widget.AutoComplete(this.oP.input, this.oP.container, myDataSource);
	myAutoComp.animVert = this.oP.animVert;	// Container will expand and collapse vertically
	myAutoComp.animHoriz = this.oP.animHoriz; // Container will expand and collapse horizontally
	myAutoComp.animSpeed = this.oP.animSpeed; // Container animation will take 2 seconds to complete
	myAutoComp.autoHighlight = this.oP.autoHighlight;  
	myAutoComp.forceSelection = this.oP.forceSelection; 
	myAutoComp.minQueryLength = this.oP.minQueryLength;
	myAutoComp.useIFrame = this.oP.useIFrame;
	
	return myAutoComp;
}
