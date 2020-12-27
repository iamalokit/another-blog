package com.iamalokit.anotherblog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.Blog;
import com.iamalokit.anotherblog.service.BlogService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogDetailVO;
import com.iamalokit.anotherblog.vo.SimpleBlogListVO;

@Service
public class BlogServiceImpl implements BlogService {

	@Override
	public String saveBlog(Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getBlogsPage(PageQueryUtil pageUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBatch(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalBlogs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Blog getBlogById(Long blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getBlogsForIndexPage(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogDetailVO getBlogDetail(Long blogId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getBlogsPageByTag(String tagName, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getBlogsPageByCategory(String categoryId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getBlogsPageBySearch(String keyword, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogDetailVO getBlogDetailBySubUrl(String subUrl) {
		// TODO Auto-generated method stub
		return null;
	}

}
