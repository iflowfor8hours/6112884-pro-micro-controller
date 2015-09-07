function navtabfn(current)
{
	document.write("<div id=\"header\"><h1>XT/AT/PS2/Terminal to USB Converter with NKRO</h1></div>");
	document.write("<div id=menu class=\"navtabs\"><ul class=\"navtabs\">");
	
	var titles = new Array("Intro", "Hardware", "Firmware", "Config", "QuickRef", "Tools", "Codes", "Trouble", "GH", "DT", "History");
	var pages = new Array("index.html", "hardware.html", "firmware.html", "config.html", "configquickref.html", "tools.html", "codes.html", "trouble.html", "GH.html", "DT.html", "history.html");
	
	for ( i = 0; i < titles.length; i++ ) {
		document.write("<li class=\"navtabs\">");
		if ( current == titles[i] ) { document.write("<p class=\"navtabs\">" + titles[i] + "</p>"); }
		else { document.write("<a class=\"navtabs\" href=" + pages[i] + ">" + titles[i] + "</a>"); }
		document.write("</li>");
	}

	document.write("</ul></div>");
}
