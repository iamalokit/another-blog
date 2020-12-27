package com.iamalokit.anotherblog.dao;

import org.apache.ibatis.annotations.Param;

public interface BlogDao {

	int updateBlogCategorys(@Param("categoryName") String categoryName, @Param("categoryId") Long categoryId,
			@Param("ids") Long[] ids);

}
