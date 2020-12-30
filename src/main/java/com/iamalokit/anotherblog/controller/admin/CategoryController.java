package com.iamalokit.anotherblog.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamalokit.anotherblog.service.CategoryService;
import com.iamalokit.anotherblog.util.BlogStringUtil;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public String categoryPage(HttpServletRequest request) {
		request.setAttribute("path", "categories");
		return "admin/category";
	}

	@RequestMapping(value = "/categories/list", method = RequestMethod.GET)
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (params.get("page") == null || params.get("limit") == null) {
			return ResultGenerator.genFailResult("No Categories found");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
	}

	@RequestMapping(value = "/categories/save", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestParam("categoryName") String categoryName,
			@RequestParam("categoryIcon") String categoryIcon) {
		if (BlogStringUtil.isNullOrEmpty(categoryName)) {
			return ResultGenerator.genFailResult("Category Name is null or empty");
		}
		if (BlogStringUtil.isNullOrEmpty(categoryIcon)) {
			return ResultGenerator.genFailResult("Category Icon is null or empty");
		}
		if (categoryService.saveCategory(categoryName, categoryIcon)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("Unable to save category");
		}
	}

	@RequestMapping(value = "/categories/update", method = RequestMethod.POST)
	@ResponseBody
	public Result update(@RequestParam("categoryId") Long categoryId, @RequestParam("categoryName") String categoryName,
			@RequestParam("categoryIcon") String categoryIcon) {
		if (BlogStringUtil.isNullOrEmpty(categoryName)) {
			return ResultGenerator.genFailResult("categoryName is empty or null");
		}
		if (BlogStringUtil.isNullOrEmpty(categoryIcon)) {
			return ResultGenerator.genFailResult("categoryIcon is empty or null");
		}
		if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("Unable to update category");
		}
	}

	@RequestMapping(value = "/categories/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("No record deleted");
		}
		if (categoryService.deleteBatch(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("Unable to delete category");
		}
	}

}
