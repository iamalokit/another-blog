package com.iamalokit.anotherblog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.service.TagService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;
import com.iamalokit.anotherblog.vo.BlogTagCount;

@Service
public class TagServiceImpl implements TagService {

	@Override
	public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalTags() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean saveTag(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBatch(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlogTagCount> getBlogTagCountForIndex() {
		// TODO Auto-generated method stub
		return null;
	}

}
