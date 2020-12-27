package com.iamalokit.anotherblog.service;

import java.util.List;

import com.iamalokit.anotherblog.entity.Blog;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogDetailVO;
import com.iamalokit.anotherblog.vo.SimpleBlogListVO;

public interface BlogService {
	
	String saveBlog(Blog blog);
	
	PageResult getBlogsPage(PageQueryUtil pageUtil);

	Boolean deleteBatch(Long[] ids);
	
	int getTotalBlogs();
	
	Blog getBlogById(Long blogId);
	
	String updateBlog(Blog blog);
	
	PageResult getBlogsForIndexPage(int page);
	
	List<SimpleBlogListVO> getBlogListForIndexPage(int type);
	
	BlogDetailVO getBlogDetail(Long blogId);
	
	PageResult getBlogsPageByTag(String tagName, int page);
	
	PageResult getBlogsPageByCategory(String categoryId, int page);
	
	PageResult getBlogsPageBySearch(String keyword, int page);
	
	BlogDetailVO getBlogDetailBySubUrl(String subUrl);
}
