package com.iamalokit.anotherblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iamalokit.anotherblog.entity.BlogCategory;
import com.iamalokit.anotherblog.util.PageQueryUtil;

public interface BlogCategoryDao {
	
	BlogCategory selectByCategoryName(String categoryName);

	List<BlogCategory> findCategoryList(PageQueryUtil pageUtil);

	List<BlogCategory> selectByCategoryIds(@Param("categoryIds") List<Long> categoryIds);

	int getTotalCategories(PageQueryUtil pageUtil);

	int deleteBatch(Long[] ids);

}
