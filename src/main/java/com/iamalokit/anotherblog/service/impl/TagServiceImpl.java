package com.iamalokit.anotherblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iamalokit.anotherblog.dao.BlogTagDao;
import com.iamalokit.anotherblog.dao.BlogTagRelationDao;
import com.iamalokit.anotherblog.entity.BlogTag;
import com.iamalokit.anotherblog.entity.BlogTagCount;
import com.iamalokit.anotherblog.mapper.BlogTagMapper;
import com.iamalokit.anotherblog.service.TagService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private BlogTagMapper blogTagMapper;

	@Autowired
	private BlogTagDao blogTagDao;

	@Autowired
	private BlogTagRelationDao blogTagRelationDao;

	@Override
	public PageResult getBlogTagPage(PageQueryUtil pageUtil) {
		List<BlogTag> tags = blogTagDao.findTagList(pageUtil);
		int total = blogTagDao.getTotalTags(pageUtil);
		PageResult pageResult = new PageResult(tags, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	@Override
	public int getTotalTags() {
		return blogTagDao.getTotalTags(null);
	}

	@Override
	public Boolean saveTag(String tagName) {
		BlogTag temp = blogTagDao.selectByTagName(tagName);
		if (temp == null) {
			BlogTag blogTag = new BlogTag();
			blogTag.setTagName(tagName);
			return blogTagMapper.insertSelective(blogTag) > 0;
		}
		return false;
	}

	@Override
	public Boolean deleteBatch(Long[] ids) {
		List<Long> relations = blogTagRelationDao.selectDistinctTagIds(ids);
		if (!CollectionUtils.isEmpty(relations)) {
			return false;
		}
		return blogTagDao.deleteBatch(ids) > 0;
	}

	@Override
	public List<BlogTagCount> getBlogTagCountForIndex() {
		List<BlogTagCount> blogTagCounts = blogTagDao.getTagCount();
		return blogTagCounts;
	}

}
