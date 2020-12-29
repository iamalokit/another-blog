package com.iamalokit.anotherblog.service;

import java.util.List;

import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogTagCount;

public interface TagService {
	PageResult getBlogTagPage(PageQueryUtil pageUtil);

	int getTotalTags();

	Boolean saveTag(String tagName);

	Boolean deleteBatch(Long[] ids);

	List<BlogTagCount> getBlogTagCountForIndex();

}
