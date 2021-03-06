/*
 * Created on 5 Dec 2016 ( Time 11:51:40 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */

package com.heroku.ra.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.heroku.ra.entities.GuestStayC;
import com.heroku.ra.model.ResponseMessage;
import com.heroku.ra.services.GuestStayCService;

@Controller
@RequestMapping(value="/api/v1/gueststay")
public class GuestStayCRestController {
	
	private static Logger logger = LoggerFactory.getLogger(GuestStayCRestController.class);
	
	@Autowired
	private GuestStayCService gueststaycService;
		
	/*
	 * READ METHODS
	 */

	@RequestMapping(value="", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getAll() {

		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> getPage");
		
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(gueststaycService.findAll());
		} catch (Exception e) {
			logger.error("GuestStayCController -> getAll", e);
			responseMessage.setError(-1, "Unable to get page for GuestStayC: " + e.getMessage());
		}
		return responseMessage;
	}

	@RequestMapping(value="/page", method=RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage getPage(@RequestParam int page, @RequestParam int records) {

		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> getPage");
		
		ResponseMessage responseMessage = new ResponseMessage();
		try {
			responseMessage.setData(gueststaycService.findAll(page, records));
		} catch (Exception e) {
			logger.error("GuestStayCController -> getPage", e);
			responseMessage.setError(-1, "Unable to get page for GuestStayC: " + e.getMessage());
		}
		return responseMessage;
		
	}


//	@RequestMapping(value="/count", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getCount() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("GuestStayCService -> getAll");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(gueststaycService.count());
//		} catch (Exception e) {
//			logger.error("GuestStayCController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all GuestStayC: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

//	@RequestMapping(value="/page", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getPage(@RequestParam int page,@RequestParam int size) {
//
//		if (logger.isDebugEnabled())
//			logger.debug("GuestStayCService -> getPage(" + page + "," + size + ")");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(gueststaycService.getPage(page, size));
//		} catch (Exception e) {
//			logger.error("GuestStayCController -> getAll", e);
//			responseMessage.setError(-1, "Unable to get all GuestStayC: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}
	
//	@RequestMapping(value="/elements", method=RequestMethod.GET,
//			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseMessage getDropDownElements() {
//
//		if (logger.isDebugEnabled())
//			logger.debug("GuestStayCService -> getDropDownElements");
//		
//		ResponseMessage responseMessage = new ResponseMessage();
//		try {
//			responseMessage.setData(gueststaycService.getDropDownValues());
//		} catch (Exception e) {
//			logger.error("GuestStayCController -> getDropDownElements", e);
//			responseMessage.setError(-1, "Unable to getDropDownElements for GuestStayC: " + e.getMessage());
//		}
//		return responseMessage;
//		
//	}

	@RequestMapping(value="/{id}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public ResponseMessage getOne(@PathVariable Integer id) {
		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> getOne(" + id + ")");
		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(gueststaycService.get(id));
		} catch (Exception e) {
			logger.error("GuestStayCController -> create", e);
			responseMessage.setError(-1,
					"Unable to create GuestStayC: " + id + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

	
	/*
	 * DML Methods
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage create(@RequestBody GuestStayC element) {

		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> create:" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			responseMessage.setData(gueststaycService.create(element));
		} catch (Exception e) {
			logger.error("GuestStayCController -> create", e);
			responseMessage.setError(-1,
					"Unable to create GuestStayC: " + element + ",Error:"  + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage edit(@PathVariable Integer id, @RequestBody GuestStayC element) {
		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> edit(" + id + "):" + element);

		ResponseMessage responseMessage = new ResponseMessage();
		
		try {
			element.setId(id);
			responseMessage.setData(gueststaycService.create(element));
		} catch (Exception e) {
			logger.error("GuestStayCController -> edit", e);
			responseMessage.setError(-1,
					"Unable to edit GuestStayC: " + element + ",Error:" + e.getMessage());
		}
		
		return responseMessage;
	}

	@RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE, 
			produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseMessage delete(@PathVariable Integer id) {

		if (logger.isDebugEnabled())
			logger.debug("GuestStayCService -> delete(" + id + ")");

		ResponseMessage responseMessage = new ResponseMessage();
		try {
			gueststaycService.delete(id);
		} catch (Exception e) {
			logger.error("GuestStayCController -> delete", e);
			responseMessage.setError(-1, "Unable to delete GuestStayC: " + id + ",Error:"  + e.getMessage());
		}
		return responseMessage;
	}
	
	@ExceptionHandler(Exception.class)
	public @ResponseBody
	String handleException(Exception e, HttpServletResponse response) {
	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    return e.getMessage();
	}

}

