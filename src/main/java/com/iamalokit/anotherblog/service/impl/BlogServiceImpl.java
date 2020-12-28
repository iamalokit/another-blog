package com.iamalokit.anotherblog.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iamalokit.anotherblog.dao.BlogCategoryDao;
import com.iamalokit.anotherblog.dao.BlogDao;
import com.iamalokit.anotherblog.entity.Blog;
import com.iamalokit.anotherblog.entity.BlogCategory;
import com.iamalokit.anotherblog.mapper.BlogCategoryMapper;
import com.iamalokit.anotherblog.mapper.BlogMapper;
import com.iamalokit.anotherblog.service.BlogService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogDetailVO;
import com.iamalokit.anotherblog.vo.BlogListVO;
import com.iamalokit.anotherblog.vo.SimpleBlogListVO;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;

	@Autowired
	private BlogMapper blogMapper;

	@Autowired
	private BlogCategoryMapper blogCategoryMapper;

	@Autowired
	private BlogCategoryDao blogCategoryDao;

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
		return blogDao.getTotalBlogs(null);
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
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("limit", 8);
		params.put("blogStatus", 1);
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		List<Blog> blogList = blogDao.findBlogList(pageUtil);
		List<BlogListVO> blogListVOS = getBlogListVOsByBlogs(blogList);
		int total = blogDao.getTotalBlogs(pageUtil);
		PageResult pageResult = new PageResult(blogListVOS, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	@Override
	public List<SimpleBlogListVO> getBlogListForIndexPage(int type) {
        List<SimpleBlogListVO> simpleBlogListVOS = new ArrayList<>();
        List<Blog> blogs = blogDao.findBlogListByType(type, 9);
        if (!CollectionUtils.isEmpty(blogs)) {
            for (Blog blog : blogs) {
                SimpleBlogListVO simpleBlogListVO = new SimpleBlogListVO();
                BeanUtils.copyProperties(blog, simpleBlogListVO);
                simpleBlogListVOS.add(simpleBlogListVO);
            }
        }
        return simpleBlogListVOS;
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

	private List<BlogListVO> getBlogListVOsByBlogs(List<Blog> blogList) {
		List<BlogListVO> blogListVOS = new ArrayList<>();
		if (!CollectionUtils.isEmpty(blogList)) {
			List<Long> categoryIds = blogList.stream().map(Blog::getBlogCategoryId).collect(Collectors.toList());
			Map<Long, String> blogCategoryMap = new HashMap<>();
			if (!CollectionUtils.isEmpty(categoryIds)) {
				List<BlogCategory> blogCategories = blogCategoryDao.selectByCategoryIds(categoryIds);
				if (!CollectionUtils.isEmpty(blogCategories)) {
					blogCategoryMap = blogCategories.stream().collect(
							Collectors.toMap(BlogCategory::getId, BlogCategory::getCategoryIcon, (key1, key2) -> key2));
				}
			}
			for (Blog blog : blogList) {
				BlogListVO blogListVO = new BlogListVO();
				BeanUtils.copyProperties(blog, blogListVO);
				if (blogCategoryMap.containsKey(blog.getBlogCategoryId())) {
					blogListVO.setBlogCategoryIcon(blogCategoryMap.get(blog.getBlogCategoryId()));
				} else {
					blogListVO.setBlogCategoryId(0);
					blogListVO.setBlogCategoryName("Default Category");
					blogListVO.setBlogCategoryIcon("/admin/dist/img/category/00.png");
				}
				blogListVOS.add(blogListVO);
			}
		}
		return blogListVOS;
	}

}
