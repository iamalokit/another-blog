package com.iamalokit.anotherblog.dao;

import java.util.List;
import java.util.Map;

import com.iamalokit.anotherblog.entity.BlogComment;

public interface BlogCommentDao {
	List<BlogComment> findBlogCommentList(Map<String, Object> map);

	int getTotalBlogComments(Map<String, Object> map);

	int deleteBatch(Long[] ids);
	
	int checkDone(Long[] ids);
}
