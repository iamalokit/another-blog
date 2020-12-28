package com.iamalokit.anotherblog.service;

import com.iamalokit.anotherblog.entity.BlogComment;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.PageResult;

public interface CommentService {
	Boolean addComment(BlogComment blogComment);

	PageResult getCommentsPage(PageQueryUtil pageUtil);

	int getTotalComments();

	Boolean checkDone(Long[] ids);

	Boolean deleteBatch(Long[] ids);

	Boolean reply(Long commentId, String replyBody);

	PageResult getCommentPageByBlogIdAndPageNum(Long blogId, int page);
}
