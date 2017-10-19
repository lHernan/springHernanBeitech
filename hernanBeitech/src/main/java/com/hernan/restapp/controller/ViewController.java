package com.hernan.restapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * The Class ViewController.
 *
 * @author Hernan
 */
@Controller
public class ViewController {

	/**
	 * Homepage.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {

		return "index";
	}

}
