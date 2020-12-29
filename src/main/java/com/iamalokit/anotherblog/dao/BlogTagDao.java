package com.iamalokit.anotherblog.dao;

import java.util.List;

import com.iamalokit.anotherblog.entity.BlogTag;
import com.iamalokit.anotherblog.util.PageQueryUtil;

public interface BlogTagDao {
	BlogTag selectByTagName(String tagName);

	int getTotalTags(PageQueryUtil pageUtil);
	
	List<BlogTag> findTagList(PageQueryUtil pageUtil);
	
	int deleteBatch(Long[] ids);
}
