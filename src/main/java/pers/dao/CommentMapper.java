package pers.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import pers.domain.Comment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


@Repository
public interface CommentMapper extends Mapper<Comment> {

    List<Comment> selectByCondition(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("commentType") Integer commentType, @Param("replyType") Integer replyType, @Param("platform") Integer platform);
}