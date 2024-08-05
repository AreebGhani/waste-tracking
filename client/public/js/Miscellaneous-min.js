function textCounter(field,cntfield,maxlimit){if (field.value.length > maxlimit) field.value = field.value.substring(0, maxlimit);else cntfield.value = maxlimit - field.value.length;}
function confirmation(msg){var answer = confirm(msg);if (answer){return true;}else{return false;}}
function viewLayer(obj){document.getElementById("setVisibleDiv").style.display="inline";}
function hideLayer(obj){document.getElementById("hideLayerDiv").style.display="none";}
