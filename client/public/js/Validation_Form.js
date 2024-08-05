/*
<br><hr>
<b>Param</b>: form field - checks to see if field is empty excluding whitespace.
<p>
<b>Description</b>:	
Returns true if the form field has no value or only whitespace.  Returns false if 
the form field has a value.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isEmpty(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsEmpty" action="">
	Try it: <input type="text" name="vIsEmpty" id="vIsEmpty" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isEmpty(document.getElementById('vIsEmpty').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isEmpty(str){
	if (str == null || str.length == 0) return true;
	if (isWhitespace(str)) return true;
	return false;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if field is empty ignoring whitespace.
<p>
<b>Description</b>:	
Returns true if the form field has no value.  Returns false if the form field has 
a value including any whitespace.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
-function checkForm(form) {
	if (isEmptyIgnoreWS(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
<form name="formIsEmptyIgnoreWS" action="">
	Try it: <input type="text" name="vIsEmptyIgnoreWS" id="vIsEmptyIgnoreWS" maxlength="10" size="10"> 
	<input type="button" value="Click me" onclick="isEmptyIgnoreWS(document.getElementById('vIsEmptyIgnoreWS').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isEmptyIgnoreWS(str){
	if (str == null || str.length == 0) return true;
	return false;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if field is not empty excluding whitespace.
<p>
<b>Description</b>:	
Returns true if the form field has a value excluding whitespace.  Returns false if 
the form field has no value.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isNotEmpty(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsNotEmpty" action="">
	Try it: <input type="text" name="vIsNotEmpty" id="vIsNotEmpty" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isNotEmpty(document.getElementById('vIsNotEmpty').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isNotEmpty(str){
	if (isWhitespace(str)) return false;
	if (str != null && str.length > 0) return true;
	return false;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if field is not empty ignoring whitespace.
<p>
<b>Description</b>:	
Returns true if the form field has a value ignoring whitespace.  Returns false if 
the form field has no value.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isNotEmptyIgnoreWS(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsNotEmptyIgnoreWS" action="">
	Try it: <input type="text" name="vIsNotEmptyIgnoreWS" id="vIsNotEmptyIgnoreWS" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isNotEmptyIgnoreWS(document.getElementById('vIsNotEmptyIgnoreWS').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isNotEmptyIgnoreWS(str){
	if (str != null && str.length > 0) return true;
	return false;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if the field is numeric
<p>
<b>Description</b>:	
Returns true if the form field is numeric.  Returns false if the form field has any
character that is not numeric.  An empty string will return true because there are no
numeric characters.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isNumeric(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsNumeric" action="">
	Try it: <input type="text" name="vIsNumeric" id="vIsNumeric" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isNumeric(document.getElementById('vIsNumeric').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isNumeric(str){
	var re = /[\D]/g
	if (re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if the field is a float value
<p>
<b>Description</b>:	
Returns true if the form field is a float value.  Returns false if the form field has any
character that is not a digit or a period.  An empty string will return true because there are no
non-float characters.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isFloat(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsFloat" action="">
	Try it: <input type="text" name="vIsFloat" id="vIsFloat" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isFloat(document.getElementById('vIsFloat').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isFloat(str){
	var re = /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/g
	return String(str).search (re) != -1 
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if the field is in the format of a valid email address
<p>
<b>Description</b>:	
Returns true if the form field is a valid email address and false if it is not valid.  An empty
string will return true.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isEmail(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsEmail" action="">
	Try it: <input type="text" name="vIsEmail" id="vIsEmail" maxlength="50" size="50">
	<input type="button" value="Click me" onclick="isEmail(document.getElementById('vIsEmail').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isEmail(str){
	if(isEmpty(str)) return true;
	var re = /^[^\s()<>@,;:\/]+@\w[\w\.-]+\.[a-zA-Z]{2,}$/i
	return re.test(str);
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if the field is of the character a-z.
<p>
<b>Description</b>:	
Returns true if the form field is a-z ignoring case. An empty string will return true.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isAlpha(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsAlpha" action="">
	Try it: <input type="text" name="vIsAlpha" id="vIsAlpha" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isAlpha(document.getElementById('vIsAlpha').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isAlpha(str){
	var re = /[^a-zA-Z]/g
	if (re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field - checks to see if the field is of the character a-z or 1-9.
<p>
<b>Description</b>:	
Returns true if the form field is a-z or 1-9 ignoring case. An empty string will return true.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isAlphaNumeric(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsAlphaNumeric" action="">
	Try it: <input type="text" name="vIsAlphaNumeric" id="vIsAlphaNumeric" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isAlphaNumeric(document.getElementById('vIsAlphaNumeric').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isAlphaNumeric(str){
	var re = /[^a-zA-Z0-9]/g
	if (re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field <br>
<b>Param</b>: length to check against form field
<p>
<b>Description</b>:	
Returns true if the length of the form field passed in is equal to the length passed in.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isLength(form.field,7)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
A text box is used here to enter the length.  This will be predetermined by your application
and this is only for testing purposes.
<p>
<form name="formIsLength" action="">
	Try it: <input type="text" name="vIsLength" id="vIsLength" maxlength="10" size="10"> Length: <input type="text" name="vIsLength2" id="vIsLength2" maxlength="3" size="3">
	<input type="button" value="Click me" onclick="isLength(document.getElementById('vIsLength').value, document.getElementById('vIsLength2').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isLength(str, len){
	return str.length == len;
}

/*
<br><hr>
<b>Param</b>: form field <br>
<b>Param</b>: minimum length to check against form field<br>
<b>Param</b>: maximum length to check against form field
<p>
<b>Description</b>:	
Returns true if the length of the form field passed is between the minimum and maximum
lengths passed in.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isLengthBetween(form.field,5,8)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
Two text boxes are used here to enter the minimum and maximum lengths of the string.  
This will be predetermined by your application and this is only for testing purposes.
<p>
<form name="formIsBetween" action="">
	Try it: <input type="text" name="vIsLengthBetween" id="vIsLengthBetween" maxlength="10" size="10">
	Min: <input type="text" name="vIsLengthBetween3" id="vIsLengthBetween2" maxlength="3" size="3">
	Min: <input type="text" name="vIsLengthBetween3" id="vIsLengthBetween3" maxlength="3" size="3">
	<input type="button" value="Click me" 
	onclick="isLengthBetween(document.getElementById('vIsLengthBetween').value, document.getElementById('vIsLengthBetween2').value, document.getElementById('vIsLengthBetween3').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isLengthBetween(str, min, max){
	return (str.length >= min)&&(str.length <= max);
}

/*
<br><hr>
<b>Param</b>: form field <br>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the two form fields passed in are equal.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="text" name="field2" id="field2" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isMatch(form.field, form.field2)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsMatch" action="">
	Try it: <input type="text" name="vIsMatch" id="vIsMatch" maxlength="10" size="10"> <input type="text" name="vIsMatch2" id="vIsMatch2" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isMatch(document.getElementById('vIsMatch').value, document.getElementById('vIsMatch2').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isMatch(str1, str2){
	return str1 == str2;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is only whitespace (all spaces).
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isWhitespace(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsWhitespace" action="">
	Try it: <input type="text" name="vIsWhitespace" id="vIsWhitespace" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isWhitespace(document.getElementById('vIsWhitespace').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isWhitespace(str){
	var ws = new RegExp(/^\s+$/);

	if (ws.test(str)) return true;
	return false;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the date mask of MM/DD/YYYY, MM.DD.YYYY or MM-DD-YYYY.
This method also validates the date to ensure it is a valid date.  The month and day 
fields do not have to be two characters.  02/02/2008 and 2/2/2008 will both work.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isDateAnyFormat(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsDateAnyFormat" action="">
	Try it: <input type="text" name="vIsDateAnyFormat" id="vIsDateAnyFormat" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isDateAnyFormat(document.getElementById('vIsDateAnyFormat').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isDateAnyFormat(str){
	var re = /^(\d{1,2})[\.\/-](\d{1,2})[\.\/-](\d{4})$/

	if (!re.test(str)) return false;
	result = str.match(re);

	return checkDate(result);
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the date mask of MM/DD/YYYY.
This method also validates the date to ensure it is a valid date.  The month and day 
fields do not have to be two characters.  02/02/2008 and 2/2/2008 will both work.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isDate(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsDate" action="">
	Try it: <input type="text" name="vIsDate" id="vIsDate" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isDate(document.getElementById('vIsDate').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isDate(str){
	var re = /^(\d{1,2})[\/](\d{1,2})[\/](\d{4})$/

	if (!re.test(str)) return false;
	result = str.match(re);

	return checkDate(result);
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the date mask of MM-DD-YYYY.
This method also validates the date to ensure it is a valid date.  The month and day 
fields do not have to be two characters.  02-02-2008 and 2-2-2008 will both work.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isDateHyphon(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsDateHyphon" action="">
	Try it: <input type="text" name="vIsDateHyphon" id="vIsDateHyphon" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isDateHyphon(document.getElementById('vIsDateHyphon').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isDateHyphon(str){
	var re = /^(\d{1,2})[-](\d{1,2})[-](\d{4})$/

	if (!re.test(str)) return false;
	result = str.match(re);

	return checkDate(result);
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the date mask of MM.DD.YYYY.
This method also validates the date to ensure it is a valid date.  The month and day 
fields do not have to be two characters.  02.02.2008 and 2.2.2008 will both work.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isDatePeriod(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsDatePeriod" action="">
	Try it: <input type="text" name="vIsDatePeriod" id="vIsDatePeriod" maxlength="10" size="10">
	<input type="button" value="Click me" onclick="isDatePeriod(document.getElementById('vIsDatePeriod').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isDatePeriod(str){
	var re = /^(\d{1,2})[\.](\d{1,2})[\.](\d{4})$/

	if (!re.test(str)) return false;
	result = str.match(re);

	return checkDate(result);
}

/*
<br><hr>
<b>Param</b>: array set passed in from inDate functions
<p>
<b>Description</b>:	
Returns true if the date is a valid date.  This should not be used in form validation.
It is used by the isDate method to validate forms.
<hr><br>
*/
function checkDate(result) {
	var y = parseInt(result[3]);
	var m = parseInt(result[1]);
	var d = parseInt(result[2]);

	if(m < 1 || m > 12 || y < 1900 || y > 2100) return false;
	if (m == 2) {
		var days = ((y % 4) == 0) ? 29 : 28;
	}
	else if (m == 4 || m == 6 || m == 9 || m == 11) {
		var days = 30;
	}
	else {
		var days = 31;
	}
	
	return (d >= 1 && d <= days);
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the phone mask of 410-222-1155.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isPhone(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsPhone" action="">
	Try it: <input type="text" name="vIsPhone" id="vIsPhone" maxlength="12" size="12">
	<input type="button" value="Click me" onclick="isPhone(document.getElementById('vIsPhone').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isPhone(str) {
	var re = /^(\d{3})[-](\d{3})[-](\d{4})$/
	if (!re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the phone mask of 410.222.1155.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isPhonePeriod(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsPhonePeriod" action="">
	Try it: <input type="text" name="vIsPhonePeriod" id="vIsPhonePeriod" maxlength="12" size="12">
	<input type="button" value="Click me" onclick="isPhonePeriod(document.getElementById('vIsPhonePeriod').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isPhonePeriod(str) {
	var re = /^(\d{3})[\.](\d{3})[\.](\d{4})$/
	if (!re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the phone mask of 4102221155.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isPhoneNoMask(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsPhoneNoMask" action="">
	Try it: <input type="text" name="vIsPhoneNoMask" id="vIsPhoneNoMask" maxlength="12" size="12">
	<input type="button" value="Click me" onclick="isPhoneNoMask(document.getElementById('vIsPhoneNoMask').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isPhoneNoMask(str) {
	var re = /^(\d{10})$/
	if (!re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the phone mask of (410)222-1155.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isPhoneParens(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsPhoneParens" action="">
	Try it: <input type="text" name="vIsPhoneParens" id="vIsPhoneParens" maxlength="15" size="15">
	<input type="button" value="Click me" onclick="isPhoneParens(document.getElementById('vIsPhoneParens').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isPhoneParens(str) {
	var re = /^(\(\d{3}\))(\d{3})[-](\d{4})$/
	if (!re.test(str)) return false;
	return true;
}

/*
<br><hr>
<b>Param</b>: form field 
<p>
<b>Description</b>:	
Returns true if the string is in the phone mask of (410) 222-1155.
<p>
An example is listed below.  Please note that you will have to create an onsubmit 
routine when the form is submitted.  In this example it is called checkForm.
<p>
<textarea width="100%" height="180px" style="width:100%;height:180px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction" onsubmit="return checkForm(this)">
	<input type="text" name="field" id="field" maxlength="10" size="10">
	<input type="submit" value="Submit">
</form>
 function checkForm(form) {
	if (isPhoneParensSpace(form.field)) {
		error.innerHTML += start + "Your message would go here." + end;
		retval = false;
	}
 }
</textarea>
<br>
In this example an alert box is used.  In the above example a div tag called 'error' 
is updated.  The div tag would need to be created on your form as well.  This is the 
preferred method.  The alert box is for demonstrations of this method.
<p>
<form name="formIsPhoneParensSpace" action="">
	Try it: <input type="text" name="vIsPhoneParensSpace" id="vIsPhoneParensSpace" maxlength="15" size="15">
	<input type="button" value="Click me" onclick="isPhoneParensSpace(document.getElementById('vIsPhoneParensSpace').value) ? alert('true') : alert('false');">
</form>
<hr><br>
*/
function isPhoneParensSpace(str) {
	var re = /^(\(\d{3}\))[\s](\d{3})[-](\d{4})$/
	if (!re.test(str)) return false;
	return true;
}