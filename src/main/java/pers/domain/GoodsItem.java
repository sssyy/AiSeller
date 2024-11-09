package pers.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author yuejianbin
 * @date 2024/11/7 22:52
 */

@Data
@Table(name = "goods")
public class GoodsItem {

    @Id
    @Column(name = "goodsId")
    private String goodsId;

    @Column(name = "goodsDesc")
    private String goodsDesc;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "D")
    private Integer delete;

    @Column(name = "updateTime")
    private Date updateTime;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "platform")
    private Integer platform;

}
