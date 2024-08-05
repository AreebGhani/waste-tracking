/*
<br><hr>
<b>Param</b>: form field<br>
<b>Param</b>: div container<br>
<b>Param</b>: image id
<p>
Dependent JavaScript files as of yui_2.6.0:<br>
yui_2.6.0/yui/build/yahoo/yahoo.js<br>
yui_2.6.0/yui/build/event/event.js<br>
yui_2.6.0/yui/build/dom/dom.js<br>
yui_2.6.0/yui/build/calendar/calendar.js
<p>
Dependent style sheets file as of yui_2.6.0:<br>
js/yui_2.6.0/yui/build/calendar/assets/skins/sam/calendar.css
<p>
<b>Description</b>:	
Allows a simple calendar interface between the user and the application.
Three parameters are sent into this method.  The form field is the field
that will have the date populated.  The div container parameter is where 
the calendar popup will be displayed.  The image id is the id of the image
that will be clicked to display the calendar.
<p>
An example would be to add the following three components code to your page.
<p>
<textarea width="100%" height="240px" style="width:100%;height:240px" name="code" wrap="logical" rows="21" cols="42">
Inside your <head> tag:
<script>
LoadCalendar('date','dateCont','dateID');
</script>
<style>
dateCont { display:none; position: absolute;}
</style>

Inside your form tag:
<form name="yourForm" action="yourAction">
	<input type="text" name="date">
	<img src="calendar.gif" id="dateID">	
	<div id="dateCont"></div>	
</form>
</textarea>
<p>
You must use the method LoadCalendar for each calendar added.  Each form
field, div container and image id must be unique to the page.  The style
property must be set as well or the calendar will dispaly without the user
clicking the calendar image.  Inside your form you must have a field, an image
with an id and a div container.
<br>
<style>
	#loadCalendarDateCont { display:none; position: absolute;}
	#loadCalendarDateCont2 { display:none; position: absolute;}
	#loadCalendarDateCont3 { display:none; position: absolute;}
	#loadCalendarDateCont4 { display:none; position: absolute;}
	#loadCalendarDateCont5 { display:none; position: absolute;}
	#loadCalendarDateCont6 { display:none; position: absolute;}
</style>

<script>
var options_ = {
	mindate:"12/05/2008",
	maxdate:"1/27/2010",
	pagedate:"1/2009",
	selected:"1/24/2009",
	title:"What is your date?",
	navigator:true,
	locale_months:"short",
	locale_weekdays:"long"
};

LoadCalendar('loadCalendarDate','loadCalendarDateCont','loadCalendarDateID');
LoadCalendar('loadCalendarDate2','loadCalendarDateCont2','loadCalendarDateID2',true,options_);
LoadCalendar('minDate','loadCalendarDateCont4','loadCalendarDateID4');
LoadCalendar('maxDate','loadCalendarDateCont5','loadCalendarDateID5');
LoadCalendar('selectDate','loadCalendarDateCont6','loadCalendarDateID6');
</script>
<p>
Here is an example of a basic calendar that will be used in most applications.
<form name="calendarForm" action="">
	<input type="text" name="loadCalendarDate" id="loadCalendarDate" size="10" maxlength="10">
	<img src="calendar.gif" id="loadCalendarDateID">	
	<div id="loadCalendarDateCont"></div>	
</form>

<p>
Here is an example of more complex calendar that uses the following attributes:<br>
- Minimum date: 12/1/2008<br>
- Maximum date: 1/27/2010<br>
- Page date: 1/2009<br>
- Selected: 1/24/2009<br>
- Title: What is your date?<br>
- Navigator: True<br>
- Locale Months: Short<br>
- Locale Weekdays: Long<br>

<form name="calendarForm2" action="">
	<input type="text" name="loadCalendarDate2" id="loadCalendarDate2" size="10" maxlength="10">
	<img src="calendar.gif" id="loadCalendarDateID2">	
	<div id="loadCalendarDateCont2"></div>	
</form>
<p>
You can change many of the calendar options by setting the form options below.  Once you are done click "Try Me" and then click on the calendar button that will 
appear to the right of the right of the "Try Me" button. 
<form name="customCalendarForm" action="yourAction">
<table>
<tr>
	<td>Minimum Date:</td>
	<td>
		<input type="text" name="minDate" id="minDate" size="10" maxlength="10">
		<img src="calendar.gif" id="loadCalendarDateID4">	
		<div id="loadCalendarDateCont4"></div>	
	</td>
	<td>Maximum Date:</td>
	<td>
		<input type="text" name="maxDate" id="maxDate" size="10" maxlength="10">
		<img src="calendar.gif" id="loadCalendarDateID5">	
		<div id="loadCalendarDateCont5"></div>	
	</td>
</tr>
<tr>
	<td>Selected Date:</td>
	<td>
		<input type="text" name="selectDate" id="selectDate" size="10" maxlength="10">
		<img src="calendar.gif" id="loadCalendarDateID6">	
		<div id="loadCalendarDateCont6"></div>	
	</td>
	<td>Month/Year to Display:</td>
	<td>
		<select name="pageMonth">
			<option value=""></option>
			<option value="1">January</option><option value="2">February</option><option value="3">March</option>
			<option value="4">April</option><option value="5">May</option><option value="6">June</option>
			<option value="7">July</option><option value="8">August</option><option value="9">September</option>
			<option value="10">October</option><option value="11">November</option><option value="12">December</option>
		</select>
		<select name="pageYear">
			<option value=""></option>
			<option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option>
			<option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option>
		</select>
	</td>
</tr>
<tr>
	<td>Title:</td>
	<td>
		<input type="text" name="title" size="15" maxlength="20">
	</td>
	<td>Navigator:</td>
	<td>
		<select name="nav">
			<option value="false">N</option><option value="true">Y</option>
		</select>
	</td>
</tr>
<tr>
	<td>Locale Months:</td>
	<td>
		<select name="l_months">
			<option value="short">Short</option><option value="medium">Medium</option><option value="long">Long</option>
		</select>
	</td>
	<td>Locale Weekdays:</td>
	<td>
		<select name="l_weekdays">
			<option value="short">Short</option><option value="medium">Medium</option><option value="long">Long</option><option value="1char">One Char</option>
		</select>
	</td>
</tr>
<tr>
	<td>Multi-Select:</td>
	<td>
		<select name="multiSelect">
			<option value="false">N</option><option value="true">Y</option>
		</select>
	</td>
	<td>Close Option:</td>
	<td>
		<select name="closeOpt">
			<option value="true">Y</option><option value="false">N</option>
		</select>
	</td>
</tr>
<tr>
	<td>Show Weekday Headers:</td>
	<td>
		<select name="showWeekdays">
			<option value="true">Y</option><option value="false">N</option>
		</select>
	</td>
	<td>Start Weekday:</td>
	<td>
		<select name="startWeekday">
			<option value="0">Sunday</option><option value="1">Monday</option><option value="2">Tuesday</option><option value="3">Wednesday</option>
			<option value="4">Thursday</option><option value="5">Friday</option><option value="6">Saturday</option>
		</select>
	</td>
</tr>
<tr>
	<td>Hide Blank Weeks:</td>
	<td>
		<select name="hideBlankWeeks">
			<option value="false">N</option><option value="true">Y</option>
		</select>
	</td>
	<td></td>
	<td>
	</td>
</tr>

<tr>
	<td><input type="button" name="submit" value="Try Me" onclick="checkCustomCalendar();"></td>
	<td colspan="3">
		<div id="customCalendarResult" style="display:none;">
			<b>Click Here ---></b>
			<input type="hidden" name="loadCalendarDate3" id="loadCalendarDate3" size="10" maxlength="10">
			<img src="calendar.gif" id="loadCalendarDateID3">	
			<div id="loadCalendarDateCont3"></div>	
		</div>
	</td>
</tr>
</table>
</form>
<br><hr>
*/

//LoadCalendar = function (formName,container,showID,calOptions) {
function LoadCalendar(formName,container,showID,defaultBool,calOptions) {
	YAHOO.namespace("widget.calendar");

	function handleSelect(type,args,obj) {
		var dates = args[0]; 
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		
		var txtDate1 = document.getElementById(formName);
		txtDate1.value = month + "/" + day + "/" + year;
	}

	function updateCal() {
		var txtDate1 = document.getElementById(formName);

		if (txtDate1.value != "") {
			YAHOO.widget.calendar.cal.select(txtDate1.value);

			var firstDate = YAHOO.widget.calendar.cal.getSelectedDates()[0];
			YAHOO.widget.calendar.cal.cfg.setProperty("pagedate", (firstDate.getMonth()+1) + "/" + firstDate.getFullYear());

			YAHOO.widget.calendar.cal.render();
		}
	}

	// For this widget page, stop the Form from being submitted, and update the cal instead
	function handleSubmit(e) {
		updateCal();
		YAHOO.util.Event.preventDefault(e);
	}

	function init() {
		if (defaultBool != true) {
			YAHOO.widget.calendar.cal = new YAHOO.widget.Calendar("cal",container, { 
				title:"Choose a date:", close:true , iframe:true } );
			var mySelectHandler = function(type,args,obj) {   
			var selected = args[0];   
			document.getElementById(container).style.display="none";
			};   

			YAHOO.widget.calendar.cal.selectEvent.subscribe(mySelectHandler, YAHOO.widget.calendar.cal, true);   
			YAHOO.widget.calendar.cal.selectEvent.subscribe(handleSelect, YAHOO.widget.calendar.cal, true);
			YAHOO.widget.calendar.cal.render();
			// Listener to show the 1-up Calendar when the button is clicked
			YAHOO.util.Event.addListener(showID, "click", YAHOO.widget.calendar.cal.show, YAHOO.widget.calendar.cal, true);

			YAHOO.util.Event.addListener("update", "click", updateCal);
			YAHOO.util.Event.addListener("dates", "submit", handleSubmit);
		}
		else {
			this.oP = (calOptions) ? calOptions : {};
			var myCalendar = new YAHOO.widget.Calendar("myCalendar",container);   

			if (this.oP.title != null)
				myCalendar.cfg.setProperty("title",this.oP.title,false);   
			if (this.oP.mindate != null)
				myCalendar.cfg.setProperty("mindate",this.oP.mindate,false);   
			if (this.oP.maxdate != null)
				myCalendar.cfg.setProperty("maxdate",this.oP.maxdate,false);   
			if (this.oP.pagedate != null)
				myCalendar.cfg.setProperty("pagedate",this.oP.pagedate,false);   
			if (this.oP.selected != null)
				myCalendar.cfg.setProperty("selected",this.oP.selected,false);   
			if (this.oP.close != null)
				myCalendar.cfg.setProperty("close",this.oP.close,false);
			else 
				myCalendar.cfg.setProperty("close",true,false);
			if (this.oP.iframe != null)
				myCalendar.cfg.setProperty("iframe",this.oP.iframe,false);
			else 
				myCalendar.cfg.setProperty("iframe",true,false);
			if (this.oP.navigator != null)
				myCalendar.cfg.setProperty("navigator",this.oP.navigator,false);
			else 
				myCalendar.cfg.setProperty("navigator",false,false);
			if (this.oP.show_weekdays != null)
				myCalendar.cfg.setProperty("show_weekdays",this.oP.show_weekdays,false);
			else 
				myCalendar.cfg.setProperty("show_weekdays",true,false);
			if (this.oP.locale_months != null)
				myCalendar.cfg.setProperty("locale_months",this.oP.locale_months,false);
			else 
				myCalendar.cfg.setProperty("locale_months","long",false);
			if (this.oP.locale_weekdays != null)
				myCalendar.cfg.setProperty("locale_weekdays",this.oP.locale_weekdays,false);
			else 
				myCalendar.cfg.setProperty("locale_weekdays","short",false);
			if (this.oP.start_weekday != null)
				myCalendar.cfg.setProperty("start_weekday",this.oP.start_weekday,false);
			if (this.oP.show_week_header != null)
				myCalendar.cfg.setProperty("show_week_header",this.oP.show_week_header,false);
			if (this.oP.show_week_footer != null)
				myCalendar.cfg.setProperty("show_week_footer",this.oP.show_week_footer,false);
			if (this.oP.hide_blank_weeks != null)
				myCalendar.cfg.setProperty("hide_blank_weeks",this.oP.hide_blank_weeks,false);

			var mySelectHandler = function(type,args,obj) {   
	    	var selected = args[0];   
			document.getElementById(container).style.display="none";
			};   
			
			myCalendar.selectEvent.subscribe(mySelectHandler, YAHOO.widget.calendar.cal, true);   
			myCalendar.selectEvent.subscribe(handleSelect, YAHOO.widget.calendar.cal, true);
			myCalendar.render();
			// Listener to show the 1-up Calendar when the button is clicked
			YAHOO.util.Event.addListener(showID, "click", myCalendar.show, myCalendar, true);

			YAHOO.util.Event.addListener("update", "click", updateCal);
			YAHOO.util.Event.addListener("dates", "submit", handleSubmit);
		}

	}
	YAHOO.util.Event.addListener(window, "load", init);
}
