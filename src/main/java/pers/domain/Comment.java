package pers.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yuejianbin
 * @date 2024/11/7 19:45
 */

@Data
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "commentId")
    private Long commentId;

    @Column(name = "commentText")
    private String commentText;


    @Column(name = "replyContent")
    private String replyContent;


    @Column(name = "commentType")
    private Integer commentType;


    @Column(name = "replyStatus")
    private Integer replyStatus;


    @Column(name = "platformType")
    private Integer platformType;


    @Column(name = "createTime")
    private Date createTime;


    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "reviewId")
    private Long reviewId;

    @Column(name = "itemId")
    private String itemId;

    @Column(name = "userReplyTime")
    private Date userReplyTime;
}
