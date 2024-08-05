function formatPhone(form) {
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)){document.getElementById(form.name).value = phone.substring(0,3) + "-" + phone.substring(3,6) + "-" + phone.substring(6,10);}
}
function formatPhonePeriod(form) {
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)){document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(3,6) + "." + phone.substring(6,10);}
}
function formatPhonePeriodWithHyphens(form) {
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)){document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(3,6) + "." + phone.substring(6,10);}
	else if (phone.length == 12){if (phone.charAt(3) == '-' && phone.charAt(7) == '-'){document.getElementById(form.name).value = phone.substring(0,3) + "." + phone.substring(4,7) + "." + phone.substring(8,12);}}
}
function formatPhoneParens(form){
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)){document.getElementById(form.name).value = "(" + phone.substring(0,3) + ")" + phone.substring(3,6) + "-" + phone.substring(6,10);}
	else if (phone.length == 12){if (phone.charAt(3) == '-' && phone.charAt(7) == '-'){document.getElementById(form.name).value = "(" + phone.substring(0,3) + ")" + phone.substring(4,7) + "-" + phone.substring(8,12);}}
}
function formatPhoneParensSpace(form) {
	var phone = document.getElementById(form.name).value;
	if (phone.length == 10 && isNumeric(phone)){document.getElementById(form.name).value = "(" + phone.substring(0,3) + ") " + phone.substring(3,6) + "-" + phone.substring(6,10);}
	else if (phone.length == 12) {if (phone.charAt(3) == '-' && phone.charAt(7) == '-'){document.getElementById(form.name).value = "(" + phone.substring(0,3) + ") " + phone.substring(4,7) + "-" + phone.substring(8,12);}}
}
function formatDate(form) {
	var date = document.getElementById(form.name).value;
	if (date.length == 6 && isNumeric(date)){var newDate = new Date();var a = date.substring(0,2);var b = date.substring(2,4);var c = date.substring(4,6);var d = newDate.getFullYear();var e = (d+"").substring(0,2);document.getElementById(form.name).value = a + "/" + b + "/" + e + c;;}
	else if (date.length == 8 && isNumeric(date)){var a = date.substring(0,2);var b = date.substring(2,4);var c = date.substring(4,8);document.getElementById(form.name).value = a + "/" + b + "/" + c;;}
}
function formatFloat(num, prec){var retval = num.toFixed(prec);document.write(retval);}
function formatFloat2(num){var retval = num.toFixed(2);document.write(retval);}
function formatOracleDateTime(val){if (val != "") {var year = val.substring(0,4);var month = val.substring(5,7);var day = val.substring(8,10);var time = val.substring(11,19);document.write(month + "/" + day + "/" + year);}}
