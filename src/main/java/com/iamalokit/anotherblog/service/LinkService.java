package com.iamalokit.anotherblog.service;

import com.iamalokit.anotherblog.entity.BlogLink;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

public interface LinkService {
	PageResult getBlogLinkPage(PageQueryUtil pageUtil);

	int getTotalLinks();

	Boolean saveLink(BlogLink link);

	BlogLink selectById(Integer id);

	Boolean updateLink(BlogLink tempLink);

	Boolean deleteBatch(Integer[] ids);
}
