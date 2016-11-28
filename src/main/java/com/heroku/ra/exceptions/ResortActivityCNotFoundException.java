/*
 * Created on 27 Nov 2016 ( Time 22:49:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.heroku.ra.exceptions;

public class ResortActivityCNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1L;

	public ResortActivityCNotFoundException(Integer id) {
		super("ResortActivityC not found with id: " + id.toString());
	}

	public ResortActivityCNotFoundException(String resortactivityc) {
		super("ResortActivityC " + resortactivityc + " not found!");
	}
}
