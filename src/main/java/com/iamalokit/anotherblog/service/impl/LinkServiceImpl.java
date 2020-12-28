package com.iamalokit.anotherblog.service.impl;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.BlogLink;
import com.iamalokit.anotherblog.service.LinkService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

@Service
public class LinkServiceImpl implements LinkService {

	@Override
	public PageResult getBlogLinkPage(PageQueryUtil pageUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalLinks() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean saveLink(BlogLink link) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogLink selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateLink(BlogLink tempLink) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBatch(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

}
