package com.iamalokit.anotherblog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamalokit.anotherblog.service.ConfigService;
import com.iamalokit.anotherblog.util.BlogStringUtil;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class ConfigController {

	@Resource
	private ConfigService configService;

	@GetMapping("/configurations")
	public String list(HttpServletRequest request) {
		request.setAttribute("path", "configurations");
		request.setAttribute("configurations", configService.getAllConfigs());
		return "admin/configuration";
	}

	@PostMapping("/configurations/website")
	@ResponseBody
	public Result website(@RequestParam(value = "websiteName", required = false) String websiteName,
			@RequestParam(value = "websiteDescription", required = false) String websiteDescription,
			@RequestParam(value = "websiteLogo", required = false) String websiteLogo,
			@RequestParam(value = "websiteIcon", required = false) String websiteIcon) {
		int updateResult = 0;
		if (!BlogStringUtil.isNullOrEmpty(websiteName)) {
			updateResult += configService.updateConfig("websiteName", websiteName);
		}
		if (!BlogStringUtil.isNullOrEmpty(websiteDescription)) {
			updateResult += configService.updateConfig("websiteDescription", websiteDescription);
		}
		if (!BlogStringUtil.isNullOrEmpty(websiteLogo)) {
			updateResult += configService.updateConfig("websiteLogo", websiteLogo);
		}
		if (!BlogStringUtil.isNullOrEmpty(websiteIcon)) {
			updateResult += configService.updateConfig("websiteIcon", websiteIcon);
		}
		return ResultGenerator.genSuccessResult(updateResult > 0);
	}

	@PostMapping("/configurations/userInfo")
	@ResponseBody
	public Result userInfo(@RequestParam(value = "yourAvatar", required = false) String yourAvatar,
			@RequestParam(value = "yourName", required = false) String yourName,
			@RequestParam(value = "yourEmail", required = false) String yourEmail) {
		int updateResult = 0;
		if (!BlogStringUtil.isNullOrEmpty(yourAvatar)) {
			updateResult += configService.updateConfig("yourAvatar", yourAvatar);
		}
		if (!BlogStringUtil.isNullOrEmpty(yourName)) {
			updateResult += configService.updateConfig("yourName", yourName);
		}
		if (!BlogStringUtil.isNullOrEmpty(yourEmail)) {
			updateResult += configService.updateConfig("yourEmail", yourEmail);
		}
		return ResultGenerator.genSuccessResult(updateResult > 0);
	}

	@PostMapping("/configurations/footer")
	@ResponseBody
	public Result footer(@RequestParam(value = "footerAbout", required = false) String footerAbout,
			@RequestParam(value = "footerICP", required = false) String footerICP,
			@RequestParam(value = "footerCopyRight", required = false) String footerCopyRight,
			@RequestParam(value = "footerPoweredBy", required = false) String footerPoweredBy,
			@RequestParam(value = "footerPoweredByURL", required = false) String footerPoweredByURL) {
		int updateResult = 0;
		if (!BlogStringUtil.isNullOrEmpty(footerAbout)) {
			updateResult += configService.updateConfig("footerAbout", footerAbout);
		}
		if (!BlogStringUtil.isNullOrEmpty(footerICP)) {
			updateResult += configService.updateConfig("footerICP", footerICP);
		}
		if (!BlogStringUtil.isNullOrEmpty(footerCopyRight)) {
			updateResult += configService.updateConfig("footerCopyRight", footerCopyRight);
		}
		if (!BlogStringUtil.isNullOrEmpty(footerPoweredBy)) {
			updateResult += configService.updateConfig("footerPoweredBy", footerPoweredBy);
		}
		if (!BlogStringUtil.isNullOrEmpty(footerPoweredByURL)) {
			updateResult += configService.updateConfig("footerPoweredByURL", footerPoweredByURL);
		}
		return ResultGenerator.genSuccessResult(updateResult > 0);
	}
}
