// dateValidation.js

function dateValidation(form) {
	start = "<b><font color=\"red\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	end = "<b></font></b><br>";
	error.innerHTML = "";
	
	if (form.cancelVal.value == "true") {
		form.cancelVal.value = "";
		form.holidayDt_.value = "";
		return true;
	}

	if (isEmpty(form.holidayDt_.value)) {
		error.innerHTML += start + "Please select a holiday date" + end;
		return false;
	}	
	else if (isDate(form.holidayDt_.value) == false) {
		error.innerHTML += start + "Date entered is invalid" + end;
		return false;
	}
	else{ 
		return true;
	}
}

