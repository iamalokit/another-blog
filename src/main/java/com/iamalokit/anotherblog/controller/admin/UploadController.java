package com.iamalokit.anotherblog.controller.admin;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iamalokit.anotherblog.util.Constants;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;
import com.iamalokit.anotherblog.util.URLUtil;

@Controller
@RequestMapping("/admin")
public class UploadController {
	
	@PostMapping({"/upload/file"})
    @ResponseBody
	public Result upload(HttpServletRequest httpServletRequest, @RequestParam("file") MultipartFile file)
			throws URISyntaxException {
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Random r = new Random();
		StringBuilder tempName = new StringBuilder();
		tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
		String newFileName = tempName.toString();
		File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
		File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
		try {
			if (!fileDirectory.exists()) {
				if (!fileDirectory.mkdir()) {
					throw new IOException("Directory does not exist " + fileDirectory);
				}
			}
			file.transferTo(destFile);
			Result resultSuccess = ResultGenerator.genSuccessResult();
			resultSuccess.setData(
					URLUtil.getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/upload/" + newFileName);
			return resultSuccess;
		} catch (IOException e) {
			e.printStackTrace();
			return ResultGenerator.genFailResult("Unable to upload the file");
		}
	}
}
