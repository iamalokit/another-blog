package com.iamalokit.anotherblog.service.impl;

import org.springframework.stereotype.Service;

import com.iamalokit.anotherblog.entity.BlogComment;
import com.iamalokit.anotherblog.service.CommentService;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

@Service
public class CommentServiceImpl implements CommentService{

	@Override
	public Boolean addComment(BlogComment blogComment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getCommentsPage(PageQueryUtil pageUtil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getTotalComments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean checkDone(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteBatch(Long[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean reply(Long commentId, String replyBody) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
