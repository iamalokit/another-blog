package com.iamalokit.anotherblog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamalokit.anotherblog.entity.AdminUser;
import com.iamalokit.anotherblog.service.AdminUserService;
import com.iamalokit.anotherblog.service.CategoryService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminUserService adminUserService;
	
	@Resource
	private CategoryService categoryService;

	@GetMapping({ "/login" })
	public String login() {
		return "admin/login";
	}

	@PostMapping(value = "/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			@RequestParam("verifyCode") String verifyCode, HttpSession session) {
		if (verifyCode == null || "".equalsIgnoreCase(verifyCode)) {
			session.setAttribute("errorMsg", "Invalid verification code");
			return "admin/login";
		}
		if (!StringUtils.hasLength(userName) || !StringUtils.hasLength(password)) {
			session.setAttribute("errorMsg", "Invalid Password");
			return "admin/login";
		}
		String kaptchaCode = session.getAttribute("verifyCode") + "";
		if (!StringUtils.hasLength(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
			session.setAttribute("errorMsg", "Kaptca code doesnt match the verification code");
			return "admin/login";
		}
		AdminUser adminUser = adminUserService.login(userName, password);
		if (adminUser != null) {
			session.setAttribute("loginUser", adminUser.getNickName());
			session.setAttribute("loginUserId", adminUser.getId());
			return "redirect:/admin/index";
		} else {
			session.setAttribute("errorMsg", "Unable to login");
			return "admin/login";
		}
	}

	@GetMapping({ "", "/", "/index", "/index.html" })
	public String index(HttpServletRequest request) {
		request.setAttribute("path", "index");
		request.setAttribute("categoryCount", categoryService.getTotalCategories());
//		request.setAttribute("blogCount", blogService.getTotalBlogs());
//		request.setAttribute("linkCount", linkService.getTotalLinks());
//		request.setAttribute("tagCount", tagService.getTotalTags());
//		request.setAttribute("commentCount", commentService.getTotalComments());
		return "admin/index";
	}

	@GetMapping("/profile")
	public String profile(HttpServletRequest request) {
		Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
		AdminUser adminUser = adminUserService.getUserDetailById(loginUserId);
		if (adminUser == null) {
			return "admin/login";
		}
		request.setAttribute("path", "profile");
		request.setAttribute("loginUserName", adminUser.getLoginUserName());
		request.setAttribute("nickName", adminUser.getNickName());
		return "admin/profile";
	}

	@PostMapping("/profile/name")
	@ResponseBody
	public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
			@RequestParam("nickName") String nickName) {
		if (!StringUtils.hasLength(loginUserName) || !StringUtils.hasLength(nickName)) {
			return "Nick name or Login Username is empty or null";
		}
		Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
		if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
			return "success";
		} else {
			return "Unable to update name";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("loginUserId");
		request.getSession().removeAttribute("loginUser");
		request.getSession().removeAttribute("errorMsg");
		return "admin/login";
	}
}
