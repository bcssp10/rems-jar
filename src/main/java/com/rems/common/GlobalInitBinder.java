package com.rems.common;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalInitBinder {

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
		
		binder.registerCustomEditor(double.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {

				double amount = text.isEmpty() ? 0d : Double.valueOf(text).doubleValue();
				setValue(amount);
			}
		});
	}

}
