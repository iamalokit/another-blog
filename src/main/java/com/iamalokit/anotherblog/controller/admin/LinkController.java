package com.iamalokit.anotherblog.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LinkController {
	@GetMapping("/links")
	public String linkPage(HttpServletRequest request) {
		request.setAttribute("path", "links");
		return "admin/link";
	}
}
