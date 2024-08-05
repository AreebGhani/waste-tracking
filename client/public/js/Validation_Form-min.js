function isEmpty(str){if (str == null || str.length == 0) return true;if (isWhitespace(str)) return true;return false;}
function isEmptyIgnoreWS(str){if (str == null || str.length == 0) return true;return false;}
function isNotEmpty(str){if (isWhitespace(str)) return false;if (str != null && str.length > 0) return true;return false;}
function isNotEmptyIgnoreWS(str){if (str != null && str.length > 0) return true;return false;}
function isNumeric(str){var re = /[\D]/g;if (re.test(str)) return false;return true;}
function isFloat(str){var re = /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/g;return String(str).search (re) != -1;}
function isEmail(str){if(isEmpty(str)) return true;var re = /^[^\s()<>@,;:\/]+@\w[\w\.-]+\.[a-zA-Z]{2,}$/i;return re.test(str);}
function isAlpha(str){var re = /[^a-zA-Z]/g;if (re.test(str)) return false;return true;}
function isAlphaNumeric(str){var re = /[^a-zA-Z0-9]/g;if (re.test(str)) return false;return true;}
function isLength(str, len){return str.length == len;}
function isLengthBetween(str, min, max){return (str.length >= min)&&(str.length <= max);}
function isMatch(str1, str2){return str1 == str2;}
function isWhitespace(str){var ws = new RegExp(/^\s+$/);if (ws.test(str)) return true;return false;}
function isDateAnyFormat(str){var re = /^(\d{1,2})[\.\/-](\d{1,2})[\.\/-](\d{4})$/;if (!re.test(str)) return false;result = str.match(re);return checkDate(result);}
function isDate(str){var re = /^(\d{1,2})[\/](\d{1,2})[\/](\d{4})$/;if (!re.test(str)) return false;result = str.match(re);return checkDate(result);}
function isDateHyphon(str){var re = /^(\d{1,2})[-](\d{1,2})[-](\d{4})$/;if (!re.test(str)) return false;result = str.match(re);return checkDate(result);}
function isDatePeriod(str){var re = /^(\d{1,2})[\.](\d{1,2})[\.](\d{4})$/;if (!re.test(str)) return false;result = str.match(re);return checkDate(result);}
function checkDate(result){var y = result[3];var m = result[1];var d = result[2];if(m < 1 || m > 12 || y < 1900 || y > 2100) return false;if (m == 2){var days = ((y % 4) == 0) ? 29 : 28;}else if (m == 4 || m == 6 || m == 9 || m == 11){var days = 30;}else{var days = 31;}return (d >= 1 && d <= days);}
function isPhone(str){var re = /^(\d{3})[-](\d{3})[-](\d{4})$/;if (!re.test(str)) return false;return true;}
function isPhonePeriod(str){var re = /^(\d{3})[\.](\d{3})[\.](\d{4})$/;if (!re.test(str)) return false;return true;}
function isPhoneNoMask(str){var re = /^(\d{10})$/;if (!re.test(str)) return false;return true;}
function isPhoneParens(str){var re = /^(\(\d{3}\))(\d{3})[-](\d{4})$/;if (!re.test(str)) return false;return true;}
function isPhoneParensSpace(str){var re = /^(\(\d{3}\))[\s](\d{3})[-](\d{4})$/;if (!re.test(str)) return false;return true;}
