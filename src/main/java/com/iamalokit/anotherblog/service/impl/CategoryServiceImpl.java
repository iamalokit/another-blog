package com.iamalokit.anotherblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.dao.BlogCategoryDao;
import com.iamalokit.anotherblog.dao.BlogDao;
import com.iamalokit.anotherblog.entity.BlogCategory;
import com.iamalokit.anotherblog.mapper.BlogCategoryMapper;
import com.iamalokit.anotherblog.mapper.BlogMapper;
import com.iamalokit.anotherblog.service.CategoryService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private BlogCategoryMapper blogCategoryMapper;

	@Autowired
	private BlogCategoryDao blogCategoryDao;

	@Autowired
	private BlogDao blogDao;

	@Override
	public PageResult getBlogCategoryPage(PageQueryUtil pageUtil) {
		List<BlogCategory> categoryList = blogCategoryDao.findCategoryList(pageUtil);
		int total = blogCategoryDao.getTotalCategories(pageUtil);
		PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	@Override
	public int getTotalCategories() {
		return blogCategoryDao.getTotalCategories(null);
	}

	@Override
	public Boolean saveCategory(String categoryName, String categoryIcon) {
		BlogCategory temp = blogCategoryDao.selectByCategoryName(categoryName);
		if (temp == null) {
			BlogCategory blogCategory = new BlogCategory();
			blogCategory.setCategoryName(categoryName);
			blogCategory.setCategoryIcon(categoryIcon);
			return blogCategoryMapper.insertSelective(blogCategory) > 0;
		}

		return false;
	}

	@Override
	public Boolean updateCategory(Long id, String categoryName, String categoryIcon) {
		BlogCategory blogCategory = blogCategoryMapper.selectByPrimaryKey(id);
		if (blogCategory != null) {
			blogCategory.setCategoryIcon(categoryIcon);
			blogCategory.setCategoryName(categoryName);
			blogDao.updateBlogCategories(categoryName, blogCategory.getId(), new Long[] { id });
			return blogCategoryMapper.updateByPrimaryKeySelective(blogCategory) > 0;
		}
		return false;
	}

	@Override
	public Boolean deleteBatch(Long[] ids) {
		Long categoryId = new Long(0);
		if (ids.length < 1) {
			return false;
		}
		blogDao.updateBlogCategories("Default", categoryId, ids);
		return blogCategoryDao.deleteBatch(ids) > 0;
	}

	@Override
	public List<BlogCategory> getAllCategories() {
		return blogCategoryDao.findCategoryList(null);
	}

}
