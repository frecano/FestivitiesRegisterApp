package com.festivities_register.beanvalidation;

import java.lang.reflect.Method;
import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRangeValidation,Object>{

	private String from;
	private String to;

	public void initialize(DateRangeValidation dataRange) {
		from = dataRange.from();
		to =  dataRange.to();

	}

	public boolean isValid(Object obj, ConstraintValidatorContext arg1) {

		try {
			Calendar fromDate = getDateValue(from, obj);
			Calendar toDate = getDateValue(to, obj);
			if(toDate == null)
				return true;
			if(fromDate != null)
				return  fromDate.before(toDate) || !fromDate.after(toDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	private Calendar getDateValue(String varName, Object obj) throws Exception{
		Class classObj = obj.getClass();
		Method getter = classObj.getMethod(getAccessorMethodName(varName), new Class[0]);
		Object getterResult = getter.invoke(obj, null);
		if (getterResult != null && getterResult instanceof Calendar){
			return (Calendar) getterResult;
		}else{
			return null;
		}
	}


	private String getAccessorMethodName(String property){
		StringBuilder builder = new StringBuilder("get");
		builder.append(Character.toUpperCase(property.charAt(0))); 
		builder.append(property.substring(1));
		return builder.toString();
	}

	
}
