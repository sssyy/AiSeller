package pers.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pers.dao.ArticleMapper;
import pers.dao.CommentMapper;
import pers.domain.Comment;
import pers.domain.PageResult;
import pers.domain.ResponseResult;
import pers.domain.ResponseResultUtils;
import pers.utils.Interact;
import pers.utils.ReviewReply;
//import pers.utils.ReviewReply;

import java.io.IOException;
import java.util.*;

@RestController
//@CrossOrigin(origins = "http://127.0.0.1:8080", methods = "GET,POST,PUT,DELETE", allowedHeaders = "*")
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private ArticleMapper mapper;

    @Autowired
    private CommentMapper commentMapper;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Comment> replyAll(){


//        Page<Object> objects = PageHelper.startPage(1, 1);
        return commentMapper.selectAll();
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public PageResult<Comment> list(
            @RequestParam(name = "startTime", required =false) String startTime,
            @RequestParam(name = "endTime", required =false) String endTime,
            @RequestParam(name = "platform", required =false) Integer platform,
            @RequestParam(name = "commentType", required =false) Integer commentType,
            @RequestParam(name = "replyType", required =false) Integer replyType,
            @RequestParam(name = "page", required =false) Integer page,
            @RequestParam(name = "pageSize", required =false) Integer pageSize
            ){
        Page<Object> pageInfo = PageHelper.startPage(page, pageSize);
        List<Comment> comments = commentMapper.selectByCondition(startTime, endTime, commentType, replyType, platform);

        return new PageResult<>(pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal(), comments);
    }

    @PostMapping("/reply")
    public ResponseResult<Object> updateStatus(@RequestParam(name = "commentId", required = false) Long commentId, @RequestParam(name = "replyContent", required = false) String replyContent ){

        ReviewReply reviewReply = new ReviewReply();
        ReviewReply.ReviewReplyParam reviewReplyParam = new ReviewReply.ReviewReplyParam();

        Comment comment1 = new Comment();
        comment1.setCommentId(commentId);
        Comment comment2 = commentMapper.selectOne(comment1);
        if (comment2 == null) {
            return ResponseResult.replyFail();
        }
        ArrayList<String> strings = new ArrayList<>();
        strings.add(comment2.getReviewId().toString());
        reviewReplyParam.setReviewIds(strings);
        reviewReplyParam.setReplyContent(replyContent);
        try {
//            boolean reply = reviewReply.reply(reviewReplyParam);
            boolean reply = reviewReply.replyTrue(reviewReplyParam);
            if (reply) {
                logger.info("回复成功 commentId" + commentId + "content" + replyContent);
                // 保存回复内容
                Comment comment = new Comment();
                comment.setCommentId(commentId);
                comment.setReplyContent(replyContent);
                comment.setUserReplyTime(new Date());
                comment.setReplyStatus(2); // 2：已回复
                commentMapper.updateByPrimaryKeySelective(comment);
            }
        } catch (Exception e) {
            logger.error("回复失败 commentId" + commentId + "content" + replyContent + "error" + e.getMessage());
            e.printStackTrace();
            return ResponseResult.replyFail();
        }

        return ResponseResult.replySuccess();

    }

    @RequestMapping(path = "/getReplyContent", method = RequestMethod.GET)
    public ResponseResult<Object> getInfo(@RequestParam(name = "commentId", required = false) Long commentId) {
        Comment comment = new Comment();
        comment.setCommentId(Long.valueOf(commentId));
        Comment comment1 = commentMapper.selectOne(comment);

        Interact interact = new Interact();

        Interact.Param param = new Interact.Param();
        param.setItemId(comment1.getItemId());
        param.setReviewContent(comment1.getCommentText());

        String replyContent;

//        return ResponseResult.replySuccess("这是一条测试回复内容");
        try {
            replyContent = interact.sendRequest(param);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseResult.replySuccess("这是一条测试回复内容");
//            return ResponseResult.replyFail();
        }
        return ResponseResult.replySuccess(replyContent);
    }

    @RequestMapping(path = "/batchReply", method = RequestMethod.POST)
    public ResponseResult<Object> batchReply(@RequestParam(name = "commentId", required = false) String commentIds) {

        if (StringUtils.isEmpty(commentIds)) {
            return ResponseResult.replyFail();
        }
        String[] split = commentIds.split(",");

        HashMap<String, Object> res = new HashMap<>();

        Integer succ = 0;
        Integer fail = 0;
        ArrayList<Long> failItemIds = new ArrayList<>();
        for (String commentId : split) {
            ResponseResult<Object> info = this.getInfo(Long.valueOf(commentId));

            if (ResponseResultUtils.isFail(info)) {
                // 获取内容失败
                fail++;
                failItemIds.add(Long.valueOf(commentId));
                continue;
            }
            ResponseResult<Object> objectResponseResult = this.updateStatus(Long.valueOf(commentId), info.getValue().toString());

            if (ResponseResultUtils.isFail(objectResponseResult)) {
                fail++;
                failItemIds.add(Long.valueOf(commentId));
                // 回复失败
                continue;
            }

            succ++;

        }
        res.put("succ", succ);
        res.put("fail", fail);
        res.put("failItemIds", failItemIds);
        return ResponseResult.replySuccess(res);
    }
}
