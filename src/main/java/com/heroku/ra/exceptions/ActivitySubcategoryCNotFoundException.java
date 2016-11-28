/*
 * Created on 27 Nov 2016 ( Time 22:49:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.heroku.ra.exceptions;

public class ActivitySubcategoryCNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1L;

	public ActivitySubcategoryCNotFoundException(Integer id) {
		super("ActivitySubcategoryC not found with id: " + id.toString());
	}

	public ActivitySubcategoryCNotFoundException(String activitysubcategoryc) {
		super("ActivitySubcategoryC " + activitysubcategoryc + " not found!");
	}
}
