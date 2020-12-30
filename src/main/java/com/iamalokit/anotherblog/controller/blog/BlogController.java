package com.iamalokit.anotherblog.controller.blog;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.iamalokit.anotherblog.service.BlogService;
import com.iamalokit.anotherblog.service.CategoryService;
import com.iamalokit.anotherblog.service.CommentService;
import com.iamalokit.anotherblog.service.ConfigService;
import com.iamalokit.anotherblog.service.TagService;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogDetailVO;

@Controller
public class BlogController {
	public static String theme = "amaze";

	@Resource
	private BlogService blogService;
	@Resource
	private TagService tagService;
//	@Resource
//	private LinkService linkService;
	@Resource
	private CommentService commentService;
	@Resource
	private ConfigService configService;
	@Resource
	private CategoryService categoryService;

	@GetMapping({ "/", "/index", "index.html" })
	public String index(HttpServletRequest request) {
		return this.page(request, 1);
	}

	@GetMapping({ "/page/{pageNum}" })
	public String page(HttpServletRequest request, @PathVariable("pageNum") int pageNum) {
		PageResult blogPageResult = blogService.getBlogsForIndexPage(pageNum);
		if (blogPageResult == null) {
			return "error/error_404";
		}
		request.setAttribute("blogPageResult", blogPageResult);
		request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
		request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
		request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
//		request.setAttribute("pageName", "首页");
		request.setAttribute("configurations", configService.getAllConfigs());
		return "blog/" + theme + "/index";
	}

	@GetMapping({ "/blog/{blogId}", "/article/{blogId}" })
	public String detail(HttpServletRequest request, @PathVariable("blogId") Long blogId,
			@RequestParam(value = "commentPage", required = false, defaultValue = "1") Integer commentPage) {
		BlogDetailVO blogDetailVO = blogService.getBlogDetail(blogId);
		if (blogDetailVO != null) {
			request.setAttribute("blogDetailVO", blogDetailVO);
			request.setAttribute("commentPageResult",
					commentService.getCommentPageByBlogIdAndPageNum(blogId, commentPage));
		}
		request.setAttribute("pageName", "Details");
		request.setAttribute("configurations", configService.getAllConfigs());
		return "blog/" + theme + "/detail";
	}

	@GetMapping({ "/search/{keyword}/{page}" })
	public String search(HttpServletRequest request, @PathVariable("keyword") String keyword,
			@PathVariable("page") Integer page) {
		PageResult blogPageResult = blogService.getBlogsPageBySearch(keyword, page);
		request.setAttribute("blogPageResult", blogPageResult);
		request.setAttribute("pageName", "Search Results");
		request.setAttribute("pageUrl", "search");
		request.setAttribute("keyword", keyword);
		request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
		request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
		request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
		request.setAttribute("configurations", configService.getAllConfigs());
		return "blog/" + theme + "/list";
	}

	@GetMapping({ "/search/{keyword}" })
	public String search(HttpServletRequest request, @PathVariable("keyword") String keyword) {
		return search(request, keyword, 1);
	}

	@GetMapping({ "/tag/{tagName}" })
	public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName) {
		return tag(request, tagName, 1);
	}

	@GetMapping({ "/tag/{tagName}/{page}" })
	public String tag(HttpServletRequest request, @PathVariable("tagName") String tagName,
			@PathVariable("page") Integer page) {
		PageResult blogPageResult = blogService.getBlogsPageByTag(tagName, page);
		request.setAttribute("blogPageResult", blogPageResult);
		request.setAttribute("pageName", "Tags");
		request.setAttribute("pageUrl", "tag");
		request.setAttribute("keyword", tagName);
		request.setAttribute("newBlogs", blogService.getBlogListForIndexPage(1));
		request.setAttribute("hotBlogs", blogService.getBlogListForIndexPage(0));
		request.setAttribute("hotTags", tagService.getBlogTagCountForIndex());
		request.setAttribute("configurations", configService.getAllConfigs());
		return "blog/" + theme + "/list";
	}
}
