package com.iamalokit.anotherblog.service;

import java.util.List;

import com.iamalokit.anotherblog.entity.BlogCategory;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

public interface CategoryService {
	
	PageResult getBlogCategoryPage(PageQueryUtil pageUtil);

	int getTotalCategories();
	
	Boolean saveCategory(String categoryName,String categoryIcon);

    Boolean updateCategory(Long categoryId, String categoryName, String categoryIcon);

    Boolean deleteBatch(Long[] ids);

    List<BlogCategory> getAllCategories();

}
