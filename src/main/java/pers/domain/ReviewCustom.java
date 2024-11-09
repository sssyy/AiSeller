package pers.domain;

import lombok.Data;

/**
 * @author yuejianbin
 * @date 2024/11/7 21:26
 */

@Data
public class ReviewCustom {

    private String itemId;

    private String skuId;

    private String reviewId;

    private String content;

    private Long createTime;

}
