package com.iamalokit.anotherblog.controller.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamalokit.anotherblog.service.TagService;
import com.iamalokit.anotherblog.util.BlogStringUtil;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class TagController {
	
	@Resource
	private TagService tagService;
	
	@GetMapping("/tags")
    public String tagPage(HttpServletRequest request) {
        request.setAttribute("path", "tags");
        return "admin/tag";
    }
	
	@PostMapping("/tags/save")
    @ResponseBody
    public Result save(@RequestParam("tagName") String tagName) {
        if (BlogStringUtil.isNullOrEmpty(tagName)) {
            return ResultGenerator.genFailResult("Tag name is null or empty");
        }
        if (tagService.saveTag(tagName)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Unable to save tag");
        }
    }
	
	@GetMapping("/tags/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        if (params.get("page") == null || params.get("limit") == null) {
            return ResultGenerator.genFailResult("Invalid parameters");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(tagService.getBlogTagPage(pageUtil));
    }
	
	@PostMapping("/tags/delete")
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("Invalid Parameters");
        }
        if (tagService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Unable to delete tags");
        }
    }
}
