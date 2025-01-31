package com.iamalokit.anotherblog.controller.admin;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class KaptchController {
	
	@Autowired
    private DefaultKaptcha captchaProducer;
	
	@GetMapping("/common/kaptcha")
	public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
		byte[] captchaOutputStream = null;
		ByteArrayOutputStream imgOutputStream = new ByteArrayOutputStream();
		try {
			String verifyCode = captchaProducer.createText();
			httpServletRequest.getSession().setAttribute("verifyCode", verifyCode);
			BufferedImage challenge = captchaProducer.createImage(verifyCode);
			ImageIO.write(challenge, "jpg", imgOutputStream);
		} catch (Exception e) {
			httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		captchaOutputStream = imgOutputStream.toByteArray();
		httpServletResponse.setHeader("Cache-Control", "no-store");
		httpServletResponse.setHeader("Pragma", "no-cache");
		httpServletResponse.setDateHeader("Expires", 0);
		httpServletResponse.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
		responseOutputStream.write(captchaOutputStream);
		responseOutputStream.flush();
		responseOutputStream.close();
		
	}
}
