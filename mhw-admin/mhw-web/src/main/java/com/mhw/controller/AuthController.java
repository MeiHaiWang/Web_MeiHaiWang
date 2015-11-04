package com.mhw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class AuthController extends BaseWebController {
	
	/**
	 * トップページ
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewIndex(ModelMap model) {
		return "index";
	}
	/**
	 * ログインページを表示します。
	 */
	@RequestMapping(value = "/auth/login", method = RequestMethod.GET)
	public String viewLogin(ModelMap model) {
		return "login";
	}
	
}
