package com.iamalokit.anotherblog.controller.admin;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iamalokit.anotherblog.service.CommentService;
import com.iamalokit.anotherblog.util.BlogStringUtil;
import com.iamalokit.anotherblog.util.PageQueryUtil;
import com.iamalokit.anotherblog.util.Result;
import com.iamalokit.anotherblog.util.ResultGenerator;

@Controller
@RequestMapping("/admin")
public class CommentController {
	@Resource
	private CommentService commentService;
	
	
	@GetMapping("/comments")
    public String list(HttpServletRequest request) {
        request.setAttribute("path", "comments");
        return "admin/comment";
    }
	
	@GetMapping("/comments/list")
	@ResponseBody
	public Result list(@RequestParam Map<String, Object> params) {
		if (params.get("page") == null || params.get("limit") == null) {
			return ResultGenerator.genFailResult("Incorrect parameters");
		}
		PageQueryUtil pageUtil = new PageQueryUtil(params);
		return ResultGenerator.genSuccessResult(commentService.getCommentsPage(pageUtil));
	}

	@PostMapping("/comments/checkDone")
	@ResponseBody
	public Result checkDone(@RequestBody Long[] ids) {
		if (ids.length < 1) {
			return ResultGenerator.genFailResult("No ids to check");
		}
		if (commentService.checkDone(ids)) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("Unable to check done for the comments");
		}
	}
	
	@PostMapping("/comments/reply")
    @ResponseBody
    public Result checkDone(@RequestParam("commentId") Long commentId,
                            @RequestParam("replyBody") String replyBody) {
        if (commentId == null || commentId < 1 || BlogStringUtil.isNullOrEmpty(replyBody)) {
            return ResultGenerator.genFailResult("Unable to check done for the comment");
        }
        if (commentService.reply(commentId, replyBody)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Unable to check done for the comment");
        }
    }
	
	
	@PostMapping("/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Long[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("No ids to delete");
        }
        if (commentService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("Unable to delete comments");
        }
    }
	
	
}
