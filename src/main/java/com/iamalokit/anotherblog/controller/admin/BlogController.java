package com.iamalokit.anotherblog.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iamalokit.anotherblog.entity.Blog;
import com.iamalokit.anotherblog.service.BlogService;
import com.iamalokit.anotherblog.service.CategoryService;
import com.iamalokit.anotherblog.util.BlogStringUtil;
import com.iamalokit.anotherblog.util.Constants;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;
import com.iamalokit.anotherblog.util.URLUtil;

@Controller
@RequestMapping("/admin")
public class BlogController {

	@Resource
	private BlogService blogService;

	@Resource
	private CategoryService categoryService;

	@GetMapping("/blogs")
	public String list(HttpServletRequest request) {
		request.setAttribute("path", "blogs");
		return "admin/blog";
	}

	@GetMapping("/blogs/list")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (params.get("page") == null || params.get("limit") == null) {
			return ResultGenerator.genFailResult("Unable to fetch the blogs list");
		}

		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(blogService.getBlogsPage(pageUtil));
	}

	@GetMapping("/blogs/edit")
	public String edit(HttpServletRequest request) {
		request.setAttribute("path", "edit");
		request.setAttribute("categories", categoryService.getAllCategories());
		return "admin/edit";
	}

	@GetMapping("/blogs/edit/{blogId}")
	public String edit(HttpServletRequest request, @PathVariable("blogId") Long blogId) {
		request.setAttribute("path", "edit");
		Blog blog = blogService.getBlogById(blogId);
		if (blog == null) {
			return "error/error_400";
		}
		request.setAttribute("blog", blog);
		request.setAttribute("categories", categoryService.getAllCategories());
		return "admin/edit";
	}

	@PostMapping("/blogs/save")
	@ResponseBody
	public Result save(@RequestParam("blogTitle") String blogTitle,
			@RequestParam(name = "blogSubUrl", required = false) String blogSubUrl,
			@RequestParam("blogCategoryId") Integer blogCategoryId, @RequestParam("blogTags") String blogTags,
			@RequestParam("blogContent") String blogContent, @RequestParam("blogCoverImage") String blogCoverImage,
			@RequestParam("blogStatus") Byte blogStatus, @RequestParam("enableComment") Byte enableComment) {
		if (BlogStringUtil.isNullOrEmpty(blogTitle)) {
			return ResultGenerator.genFailResult("blogTitle is null or empty");
		}
		if (blogTitle.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogTitle greater than 150 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogTags)) {
			return ResultGenerator.genFailResult("blogTags is null or empty");
		}
		if (blogTags.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogTags greater than 150 characters");
		}
		if (blogSubUrl.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogSubUrl greater than 150 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogContent)) {
			return ResultGenerator.genFailResult("blogContent is null or empty");
		}
		if (blogTags.trim().length() > 100000) {
			return ResultGenerator.genFailResult("blogTags greater than 100000 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogCoverImage)) {
			return ResultGenerator.genFailResult("blogCoverImage is null or empty");
		}
		Blog blog = new Blog();
		blog.setBlogTitle(blogTitle);
		blog.setBlogSubUrl(blogSubUrl);
		blog.setBlogCategoryId(blogCategoryId);
		blog.setBlogTags(blogTags);
		blog.setBlogContent(blogContent);
		blog.setBlogCoverImage(blogCoverImage);
		blog.setBlogStatus(blogStatus);
		blog.setEnableComment(enableComment);
		String saveBlogResult = blogService.saveBlog(blog);
		if ("success".equals(saveBlogResult)) {
			return ResultGenerator.genSuccessResult("Blog saved successfully");
		} else {
			return ResultGenerator.genFailResult(saveBlogResult);
		}
	}

	@PostMapping("/blogs/update")
	@ResponseBody
	public Result update(@RequestParam("blogId") Long blogId, @RequestParam("blogTitle") String blogTitle,
			@RequestParam(name = "blogSubUrl", required = false) String blogSubUrl,
			@RequestParam("blogCategoryId") Integer blogCategoryId, @RequestParam("blogTags") String blogTags,
			@RequestParam("blogContent") String blogContent, @RequestParam("blogCoverImage") String blogCoverImage,
			@RequestParam("blogStatus") Byte blogStatus, @RequestParam("enableComment") Byte enableComment) {
		if (BlogStringUtil.isNullOrEmpty(blogTitle)) {
			return ResultGenerator.genFailResult("blogTitle is null or empty");
		}
		if (blogTitle.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogTitle greater than 150 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogTags)) {
			return ResultGenerator.genFailResult("blogTags is null or empty");
		}
		if (blogTags.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogTags greater than 150 characters");
		}
		if (blogSubUrl.trim().length() > 150) {
			return ResultGenerator.genFailResult("blogSubUrl greater than 150 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogContent)) {
			return ResultGenerator.genFailResult("blogContent is null or empty");
		}
		if (blogTags.trim().length() > 100000) {
			return ResultGenerator.genFailResult("blogTags greater than 100000 characters");
		}
		if (BlogStringUtil.isNullOrEmpty(blogCoverImage)) {
			return ResultGenerator.genFailResult("blogCoverImage is null or empty");
		}
		Blog blog = new Blog();
		blog.setId(blogId);
		blog.setBlogTitle(blogTitle);
		blog.setBlogSubUrl(blogSubUrl);
		blog.setBlogCategoryId(blogCategoryId);
		blog.setBlogTags(blogTags);
		blog.setBlogContent(blogContent);
		blog.setBlogCoverImage(blogCoverImage);
		blog.setBlogStatus(blogStatus);
		blog.setEnableComment(enableComment);
		String updateBlogResult = blogService.updateBlog(blog);
		if ("success".equals(updateBlogResult)) {
			return ResultGenerator.genSuccessResult("Blog updated successfully");
		} else {
			return ResultGenerator.genFailResult(updateBlogResult);
		}
	}

	@PostMapping("/blogs/md/uploadfile")
	public void uploadFileByEditormd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "editormd-image-file", required = true) MultipartFile file)
			throws IOException, URISyntaxException {
		String fileName = file.getOriginalFilename();
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
		Random r = new Random();
		StringBuilder tempName = new StringBuilder();
		tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
		String newFileName = tempName.toString();
		File destFile = new File(Constants.FILE_UPLOAD_DIC + newFileName);
		String fileUrl = URLUtil.getHost(new URI(request.getRequestURL() + "")) + "/upload/" + newFileName;
		File fileDirectory = new File(Constants.FILE_UPLOAD_DIC);
		try {
			if (!fileDirectory.exists()) {
				if (!fileDirectory.mkdir()) {
					throw new IOException("Unable to create the file directory :" + fileDirectory);
				}
			}
			file.transferTo(destFile);
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			response.getWriter().write("{\"success\": 1, \"message\":\"success\",\"url\":\"" + fileUrl + "\"}");
		} catch (UnsupportedEncodingException e) {
			response.getWriter().write("{\"success\":0}");
		} catch (IOException e) {
			response.getWriter().write("{\"success\":0}");
		}
	}
	
	
	@PostMapping("/blogs/delete")
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("No blogs to delete");
        }
        if (blogService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Unable to delete blogs");
        }
    }
}
