function LoadCalendar(formName,container,showID,defaultBool,calOptions) {
	YAHOO.namespace("widget.calendar");

	function handleSelect(type,args,obj) {
		var dates = args[0]; 
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];

		var txtDate1 = document.getElementById(formName);
		txtDate1.value = month + "/" + day + "/" + year;
	}

	function handleSelectTwoDigits(type,args,obj) {
		var dates = args[0]; 
		var date = dates[0];
		var year = date[0], month = date[1], day = date[2];
		if (month < 10)	{ month = "0" + month; }
		if (day < 10) { day = "0" + day; }

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
			
			if (this.oP.show_two_digits != null) {
				myCalendar.selectEvent.subscribe(mySelectHandler, YAHOO.widget.calendar.cal, true);   
				myCalendar.selectEvent.subscribe(handleSelectTwoDigits, YAHOO.widget.calendar.cal, true);
			}
			else {
				myCalendar.selectEvent.subscribe(mySelectHandler, YAHOO.widget.calendar.cal, true);   
				myCalendar.selectEvent.subscribe(handleSelect, YAHOO.widget.calendar.cal, true);
			}
			myCalendar.render();
			// Listener to show the 1-up Calendar when the button is clicked
			YAHOO.util.Event.addListener(showID, "click", myCalendar.show, myCalendar, true);

			YAHOO.util.Event.addListener("update", "click", updateCal);
			YAHOO.util.Event.addListener("dates", "submit", handleSubmit);
		}

	}
	YAHOO.util.Event.addListener(window, "load", init);
}
