package com.zepal.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/{pageName}")
	public String goPage(@PathVariable("pageName")String pageName) {
		return pageName;
	}
}
