package com.iamalokit.anotherblog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.iamalokit.anotherblog.entity.Blog;
import com.iamalokit.anotherblog.util.PageQueryUtil;

public interface BlogDao {

	int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Long categoryId,
			@Param("ids") Long[] ids);
	
	List<Blog> findBlogList(PageQueryUtil pageUtil);
	
	int getTotalBlogs(PageQueryUtil pageUtil);
	
	List<Blog> findBlogListByType(@Param("type") int type, @Param("limit") int limit);

}
