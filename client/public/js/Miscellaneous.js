/*
<br><hr>
<b>Param</b>: form field textarea<br>
<b>Param</b>: form field length<br>
<b>Param</b>: maximum length
<p>
<b>Description</b>:	
Allows a textarea field to be displayed that has a maximum count text box that is 
updated each time the user enters or removes a character.  Two fields are required.
The first is a textarea holding the value and the second is a readonly text box that
will contain the textarea count.
<p>
An example is listed below.  Please note the ending textarea is shown as -textarea-.
This is done so the code can be displayed in a textarea box but will need to be 
removed when added to your form.
<p>
<textarea width="100%" height="120px" style="width:100%;height:120px" name="code" wrap="logical" rows="21" cols="42">
	<textarea name="field" id="field" cols="60" rows="3" onkeydown="textCounter(document.getElementById('field'),document.getElementById('fieldLen'),250)" 
		onkeyup="textCounter(document.getElementById('field'),document.getElementById('fieldLen'),250)">
	</-textarea-> // The hypens need to be removed
	&nbsp;&nbsp;<input readonly type="text" name="fieldLen" id="fieldLen" size="3" maxlength="3" value="250" tabindex="-1">characters left
</textarea>
<br>
<p>
	<textarea name="vTextCounter" id="vTextCounter" cols="60" rows="3" onkeydown="textCounter(document.getElementById('vTextCounter'),document.getElementById('vTextCounterLen'),250)" 
		onkeyup="textCounter(document.getElementById('vTextCounter'),document.getElementById('vTextCounterLen'),250)">
	</textarea>&nbsp;&nbsp;<input readonly type="text" name="vTextCounterLen" id="vTextCounterLen" size="3" maxlength="3" value="250" tabindex="-1">characters left
<hr><br>
*/
function textCounter(field,cntfield,maxlimit) {
	if (field.value.length > maxlimit) 
		field.value = field.value.substring(0, maxlimit);
	else
		cntfield.value = maxlimit - field.value.length;
}

/*
<br><hr>
<b>Param</b>: message to user
<p>
<b>Description</b>:	
Creates a popup confirmation javascript box with an OK and cancel button.  The page
control is dependent upon the answer given by the user.
<p>
An example is listed below. 
<p>
<textarea width="100%" height="120px" style="width:100%;height:120px" name="code" wrap="logical" rows="21" cols="42">
	if(confirmation('Your message here')) {
		return true;
	}
	else {
		return false;
	}
</textarea>
<br>
In this example an alert box is used.  Your page control would be dependent on the 
boolean result.
<p>
Press to delete <input type="button" value="Continue" onclick="confirmation('Are you sure you want to delete this record?') ? alert('true') : alert('false');">
<hr><br>
*/
function confirmation(msg) {
	var answer = confirm(msg);
	
	if (answer){
		return true;
	}
	else {
		return false;
	}
}

/*
<br><hr>
<b>Param</b>: div tag
<p>
<b>Description</b>:	
Will set the display of the div property to inline.  
<p>
An example is listed below. 
<p>
<textarea width="100%" height="120px" style="width:100%;height:120px" name="code" wrap="logical" rows="21" cols="42">
<div id="tempDiv" style="display:none;">
	<font size="20px">Hello There!</font>
</div>
<a href="javascript:viewLayer('tempDiv');">Click here to show message</a>
</textarea>
<br>
In this example you will see the message Hello There! on your screen.
<p>
<a href="javascript:viewLayer('setVisibleDiv');">Click here to show message</a>
<div id="setVisibleDiv" style="display:none;">
	<font size="20px">Hello There!</font>
</div>
<hr><br>
*/
function viewLayer(obj) {
	document.getElementById("setVisibleDiv").style.display="inline";
//	obj = document.getElementById(obj);
//	obj.style.visibility = (obj.style.visibility == 'visible') ? 'hidden' : 'visible';
}

/*
<br><hr>
<b>Param</b>: div tag
<p>
<b>Description</b>:	
Will set the display of the div property to none.  
<p>
An example is listed below. 
<p>
<textarea width="100%" height="120px" style="width:100%;height:120px" name="code" wrap="logical" rows="21" cols="42">
<div id="tempDiv">
	<font size="20px">Hello There!</font>
</div>
<a href="javascript:hideVisible('tempDiv');">Click here to show message</a>
</textarea>
<br>
In this example you will see the message Goodbye become hidden.
<p>
<a href="javascript:hideLayer('hideLayerDiv');">Click here to hide message</a>
<div id="hideLayerDiv">
	<font size="20px">Goodbye</font>
</div>
<hr><br>
*/
function hideLayer(obj) {
	document.getElementById("hideLayerDiv").style.display="none";
//	obj = document.getElementById(obj);
//	obj.style.visibility = (obj.style.visibility = 'hidden');
}
