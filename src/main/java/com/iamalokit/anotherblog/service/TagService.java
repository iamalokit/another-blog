package com.iamalokit.anotherblog.service;

import java.util.List;

import com.iamalokit.anotherblog.entity.BlogTagCount;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

public interface TagService {
	PageResult getBlogTagPage(PageQueryUtil pageUtil);

	int getTotalTags();

	Boolean saveTag(String tagName);

	Boolean deleteBatch(Long[] ids);

	List<BlogTagCount> getBlogTagCountForIndex();

}
