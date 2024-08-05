/*
<br><hr>
<b>Param</b>: form field - phone number that is 10 digits
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the phone
mask of 555-555-5555.  The input must be in the form of 10 digits.
An example would be 4102221133.  When associated with an onblur 
javascript method the input will be changed and set to the phone
mask.
<p>
The function will exit if the length is not 10 and the field is not numeric.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction">
	<input type="text" name="phone" id="phone" maxlength="12" size="12" onblur="formatPhone(this);">
</form>
</textarea>
<br>
<form name="formFormatPhone" action="">
	Try it: <input type="text" name="phone1" id="phone1" maxlength="12" size="12" onblur="formatPhone(this);"> // Enter 10 digits and press the tab key
</form>
<hr><br>
*/
function formatPhone(form) {
	var phone = document.getElementById(form.name).value;

	if (phone.length == 10 && isNumeric(phone)) {
		document.getElementById(form.name).value = phone.substring(0,3) + "-" + phone.substring(3,6) + "-" + phone.substring(6,10);
	}
}

/*
<br><hr>
<b>Param</b>: form field - phone number that is 10 digits
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the phone
mask of 555.555.5555.  The input must be in the form of 10 digits.
An example would be 4102221133.  When associated with an onblur 
javascript method the input will be changed and set to the phone
mask.
<p>
The function will exit if the length is not 10 and the field is not numeric.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="41">
<form name="yourForm" action="yourAction">
	<input type="text" name="phone" id="phone" maxlength="12" size="12" onblur="formatPhonePeriod(this);">
</form>
</textarea>
<br>
<form name="formFormatPhonePeriod" action="">
	Try it: <input type="text" name="phone2" id="phone2" maxlength="12" size="12" onblur="formatPhonePeriod(this);"> // Enter 10 digits and press the tab key
</form>
<hr><br>
*/
function formatPhonePeriod(form) {
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)) {
		document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(3,6) + "." + phone.substring(6,10);
	}
}

/*
<br><hr>
<b>Param</b>: form field - phone number that is 10 digits or a phone number
in the mask of 555-555-5555.
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the phone
mask of 555.555.5555.  The input must be in the form of 10 digits or in
the mask of 555-555-5555.  The output will result in the format 555.555.5555.
An example would be 4102221133.  When associated with an onblur 
javascript method the input will be changed and set to the phone
mask.
<p>
The function will exit if the length is not 10 and the field is not numeric or
the field is not in the mask of 555-555-5555.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction">
	<input type="text" name="phone" id="phone" maxlength="12" size="12" onblur="formatPhonePeriodWithHyphens(this);">
</form>
</textarea>
<br>
<form name="formFormatPhonePeriodWithHyphens" action="">
	Try it: <input type="text" name="phone3" id="phone3" maxlength="12" size="12" onblur="formatPhonePeriodWithHyphens(this);"> // Enter 10 digits and press the tab key
</form>
<hr><br>
*/
function formatPhonePeriodWithHyphens(form) {
	var phone = document.getElementById(form.name).value;

	if (phone.length == 10 && isNumeric(phone)) {
		document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(3,6) + "." + phone.substring(6,10);
	}
	else if (phone.length == 12) {
		if (phone.charAt(3) == '-' && phone.charAt(7) == '-') {
			document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(4,7) + "." + phone.substring(8,12);
		}
	}
}

/*
<br><hr>
<b>Param</b>: form field - phone number that is 10 digits or a phone number
in the mask of 555-555-5555.
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the phone
mask of (555)555-5555.  The input must be in the form of 10 digits or in
the mask of 555-555-5555.  The output will result in the format (555)555-5555.
When associated with an onblur javascript method the input will be changed 
and set to the phone
mask.
<p>
The function will exit if the length is not 10 and the field is not numeric or
the field is not in the mask of 555-555-5555.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction">
	<input type="text" name="phone" id="phone" maxlength="13" size="13" onblur="formatPhoneParens(this);">
</form>
</textarea>
<br>
<form name="formFormatPhoneParens" action="">
	Try it: <input type="text" name="phone4" id="phone4" maxlength="13" size="13" onblur="formatPhoneParens(this);"> // Enter 10 digits and press the tab key
</form>
<hr><br>
*/
function formatPhoneParens(form) {
	var phone = document.getElementById(form.name).value;

	if (phone.length == 10 && isNumeric(phone)) {
		document.getElementById(form.name).value = "(" + phone.substring(0,3) + ")" + phone.substring(3,6) + "-" + phone.substring(6,10);
	}
	else if (phone.length == 12) {
		if (phone.charAt(3) == '-' && phone.charAt(7) == '-') {
			document.getElementById(form.name).value = "(" + phone.substring(0,3) + ")" + phone.substring(4,7) + "-" + phone.substring(8,12);
		}
	}
}

/*
<br><hr>
<b>Param</b>: form field - phone number that is 10 digits or a phone number
in the mask of 555-555-5555.
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the phone
mask of (555) 555-5555.  The input must be in the form of 10 digits or in
the mask of 555-555-5555.  The output will result in the format (555)555-5555.
When associated with an onblur javascript method the input will be changed 
and set to the phone
mask.
<p>
The function will exit if the length is not 10 and the field is not numeric or
the field is not in the mask of 555-555-5555.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction">
	<input type="text" name="phone" id="phone" maxlength="14" size="14" onblur="formatPhoneParensSpace(this);">
</form>
</textarea>
<br>
<form name="formFormatPhoneParensSpace" action="">
	Try it: <input type="text" name="phone5" id="phone5" maxlength="14" size="14" onblur="formatPhoneParensSpace(this);"> // Enter 10 digits and press the tab key
</form>
<hr><br>
*/
function formatPhoneParensSpace(form) {
	var phone = document.getElementById(form.name).value;

	if (phone.length == 10 && isNumeric(phone)) {
		document.getElementById(form.name).value = "(" + phone.substring(0,3) + ") " + phone.substring(3,6) + "-" + phone.substring(6,10);
	}
	else if (phone.length == 12) {
		if (phone.charAt(3) == '-' && phone.charAt(7) == '-') {
			document.getElementById(form.name).value = "(" + phone.substring(0,3) + ") " + phone.substring(4,7) + "-" + phone.substring(8,12);
		}
	}
}

/*
<br><hr>
<b>Param</b>: form field - date that is 6 or 8 digits
<p>
<b>Description</b>:	
This function will take a form field and alter its value to the date
mask of 01/01/2000.  The input must be in the form of 6 or 8 digits.  The output 
will result in the format 01/01/2000.  When associated with an onblur javascript 
method the input will be changed and set to the date mask.
<p>
The function will exit if the length is not 6 or 8 digits.
<p>
An example would be to add the following code inside of your form
text input field:
<p>
<textarea width="100%" height="80px" style="width:100%;height:80px" name="code" wrap="logical" rows="21" cols="42">
<form name="yourForm" action="yourAction">
	<input type="text" name="date" id="date" maxlength="10" size="10" onblur="formatDate(this);">
</form>
</textarea>
<br>
<form name="formFormatDate" action="">
	Try it: <input type="text" name="date1" id="date1" maxlength="10" size="10" onblur="formatDate(this);"> // Enter 6 or 8 digits and press the tab key
</form>
<hr><br>
*/
function formatDate(form) {
	var date = document.getElementById(form.name).value;
	
	if (date.length == 6 && isNumeric(date)) {
		var newDate = new Date();
		var a = date.substring(0,2);
		var b = date.substring(2,4);
		var c = date.substring(4,6);
		var d = newDate.getFullYear();
		var e = (d+"").substring(0,2);
		document.getElementById(form.name).value = a + "/" + b + "/" + e + c;;
	}
	else if (date.length == 8 && isNumeric(date)) {
		var a = date.substring(0,2);
		var b = date.substring(2,4);
		var c = date.substring(4,8);
		document.getElementById(form.name).value = a + "/" + b + "/" + c;;
	}
}

/*
<br><hr>
<b>Param</b>: number that will be formatted to a float<br>
<b>Param</b>: float precision - numeric value
<p>
<b>Description</b>:	
This function will take a numeric value and add precision.  It is not used for 
form fields but simply to display data.  
<p>
An example would be to add the following code inside of your page.
<p>
<textarea width="100%" height="40px" style="width:100%;height:40px" name="code" wrap="logical" rows="21" cols="42">
	$<script>formatFloat(100,2);</script></textarea>
<p>
The following formats the value 100 to two precision places:<br>
$<script>formatFloat(100,2);</script>
<hr><br>
*/
function formatFloat(num, prec) {
	var retval = num.toFixed(prec);
	document.write(retval);
}

/*
<br><hr>
<b>Param</b>: number that will be formatted to a float using a precision of 2
<p>
<b>Description</b>:	
This function will take a numeric value and add precision of 2.  It is not used for 
form fields but simply to display data.  
<p>
An example would be to add the following code inside of your page.
<p>
<textarea width="100%" height="40px" style="width:100%;height:40px" name="code" wrap="logical" rows="21" cols="42">
	$<script>formatFloat2(100);</script></textarea>
<p>
The following formats the value 100 to two precision places:<br>
$<script>formatFloat2(100);</script>
<hr><br>
*/
function formatFloat2(num) {
	var retval = num.toFixed(2);
	document.write(retval);
}

/*
<br><hr>
<b>Param</b>: Oracle date/time that will be formatted into MM/DD/YYYY-hh:mm:ss
<p>
<b>Description</b>:	
This function will take an Oracle date/time value and format it into MM/DD/YYYY-hh:mm:ss. 
It is not used for form fields but simply to display data.  
<p>
An example would be to add the following code inside of your page.
<p>
<textarea width="100%" height="40px" style="width:100%;height:40px" name="code" wrap="logical" rows="21" cols="42">
	<script>formatOracleDateTime('2008-01-01 10:57:45');</script></textarea>
<p>
The following formats the Oracle date/time:<br>
<script>formatOracleDateTime('2008-01-01 10:57:45');</script>
<hr><br>
*/
function formatOracleDateTime(val) {
	if (val != "") {
		var year = val.substring(0,4);
		var month = val.substring(5,7);
		var day = val.substring(8,10);
		var time = val.substring(11,19);
		document.write(month + "/" + day + "/" + year);
	}
}
