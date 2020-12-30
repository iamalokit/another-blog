package com.iamalokit.anotherblog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.iamalokit.anotherblog.dao.BlogCommentDao;
import com.iamalokit.anotherblog.entity.BlogComment;
import com.iamalokit.anotherblog.mapper.BlogCommentMapper;
import com.iamalokit.anotherblog.service.CommentService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private BlogCommentDao blogCommentDao;

	@Autowired
	private BlogCommentMapper blogCommentMapper;

	@Override
	public Boolean addComment(BlogComment blogComment) {
		return blogCommentMapper.insertSelective(blogComment) > 0;
	}

	@Override
	public PageResult getCommentsPage(PageQueryUtil pageUtil) {
		List<BlogComment> comments = blogCommentDao.findBlogCommentList(pageUtil);
		int total = blogCommentDao.getTotalBlogComments(pageUtil);
		PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
		return pageResult;
	}

	@Override
	public int getTotalComments() {
		return blogCommentDao.getTotalBlogComments(null);
	}

	@Override
	public Boolean checkDone(Long[] ids) {
		return blogCommentDao.checkDone(ids) > 0;
	}

	@Override
	public Boolean deleteBatch(Long[] ids) {
		return blogCommentDao.deleteBatch(ids) > 0;
	}

	@Override
	public Boolean reply(Long commentId, String replyBody) {
		BlogComment blogComment = blogCommentMapper.selectByPrimaryKey(commentId);
		if (blogComment != null && blogComment.getCommentStatus().intValue() == 1) {
			blogComment.setReplyBody(replyBody);
			blogComment.setReplyCreateTime(new Date());
			return blogCommentMapper.updateByPrimaryKeySelective(blogComment) > 0;
		}
		return false;
	}

	@Override
	public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
		if (page < 1) {
			return null;
		}
		Map<String, Object> params = new HashMap<>();
		params.put("page", page);
		params.put("limit", 8);
		params.put("blogId", blogId);
		params.put("commentStatus", 1);
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		List<BlogComment> comments = blogCommentDao.findBlogCommentList(pageUtil);
		if (!CollectionUtils.isEmpty(comments)) {
			int total = blogCommentDao.getTotalBlogComments(pageUtil);
			PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
			return pageResult;
		}
		return null;
	}

}
