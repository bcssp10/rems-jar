package com.rems.util;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ParamFactory {

	final class LogHolder { // not public
	    static final Logger LOGGER = LoggerFactory.getLogger(ParamFactory.class);
	}
	
	/*public static <T extends Number> T getNumber( Class<T> clazz, HttpServletRequest request, String field) {

		String text = request.getParameter(field);

		PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
	    editor.setAsText(text);
	    
	    return (T) editor.getValue();
	}*/
	
	public static int getInt(HttpServletRequest request, String field) {
		
		field = request.getParameter(field);
		
		if (field.trim().isEmpty())
			return 0;
		
		return Integer.parseInt(field);
	}
	
	public static Date getDate( HttpServletRequest request, String field) {

		String text = request.getParameter(field);
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
	    if(text.isEmpty())
	    	return null;
	    
	    Date date = null;
	    
	    try {
			date = simpleDateFormat.parse(text);
		} catch (ParseException e) {
			LogHolder.LOGGER.error(e.getMessage());
		}
	    
	    return date;
	}
	
}
