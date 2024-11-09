package pers.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pers.dao.CommentMapper;
import pers.dao.GoodsItemMapper;
import pers.domain.Comment;
import pers.domain.GoodsItem;
import pers.domain.ReviewCustom;
import pers.utils.ItemReviewRequest;
import pers.utils.LongUUIDGenerator;
import pers.utils.ScoreReviewRequest;
import pers.utils.xiaohongshu.ItemInfoResponse;

import javax.persistence.Column;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author yuejianbin
 * @date 2024/11/7 20:12
 */

@Component
public class Schedule {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private GoodsItemMapper goodsItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(Schedule.class);

//    @Scheduled(cron ="* 0/20 * * * ? ")
    public void getCommentListAndSave() throws IOException, InterruptedException {

        // 遍历商品

        ItemReviewRequest itemReviewRequest = new ItemReviewRequest();
        int itemPage = 1;
        int size = 10;

        while (true) {
            String s = itemReviewRequest.sendReviewRequest(itemPage, size, 0);
            if (s.isEmpty()) {
                break;
            }

            JSONObject jsonObject = JSONObject.parseObject(s);
            Object data = jsonObject.get("data");
            if (null == data) {
                break;
            }
            String itemInfoListStr = data.toString();
            ItemInfoResponse itemInfoResponse = JSONObject.parseObject(itemInfoListStr, ItemInfoResponse.class);
            List<ItemInfoResponse.ReviewInfoList> review_info_list = itemInfoResponse.getReview_info_list();
            if (CollectionUtils.isEmpty(review_info_list)) {
                break;
            }

            for (ItemInfoResponse.ReviewInfoList reviewInfoList : review_info_list) {
                ItemInfoResponse.ReviewInfoList.SkuInfo sku_info = reviewInfoList.getSku_info();

                // 添加商品信息
                String item_id1 = sku_info.getItem_id();
                String name = sku_info.getName();
                GoodsItem goodsItem = new GoodsItem();
                goodsItem.setGoodsId(item_id1);
                GoodsItem select = goodsItemMapper.selectOne(goodsItem);
                if (null == select) {
                    goodsItem.setDelete(0);
                    goodsItem.setGoodsId(item_id1);
                    goodsItem.setGoodsDesc(name);
                    goodsItemMapper.insertSelective(goodsItem);
                }


                // 通过item_id 获取评论信息
                String item_id = sku_info.getItem_id();

                ScoreReviewRequest scoreReviewRequest = new ScoreReviewRequest();
                int page = 1;
                int pageSize = 10;
                while (true) {

                    List<ReviewCustom> reviewInfoByItemId = scoreReviewRequest.getReviewInfoByItemId(item_id, page, pageSize);
                    if (CollectionUtils.isEmpty(reviewInfoByItemId)) {
                        break;
                    }

                    for (ReviewCustom reviewCustom : reviewInfoByItemId) {

                        // 如果评论内容为空，则不需要进行回复
                        if (StringUtils.isEmpty(reviewCustom.getContent())) {
                            continue;
                        }

                        String reviewId = reviewCustom.getReviewId();
                        Comment queryComment = new Comment();
                        queryComment.setReviewId(Long.valueOf(reviewId));
                        Comment comment1 = commentMapper.selectOne(queryComment);
                        if (null != comment1) {
                            logger.info("该条记录已存在，跳过" + JSON.toJSONString(reviewCustom));
                            continue;
                        }

                        Comment comment = new Comment();
                        comment.setCommentId(LongUUIDGenerator.generateLongUUID());
                        comment.setCommentText(reviewCustom.getContent());
                        comment.setCreateTime(new Date(reviewCustom.getCreateTime()));
                        // 初始为未回复，该处还需调整
                        comment.setReplyStatus(0);
                        comment.setReviewId(Long.valueOf(reviewCustom.getReviewId()));
                        comment.setItemId(reviewCustom.getItemId());
                        commentMapper.insertSelective(comment);
                    }
                    page++;
                    Thread.sleep(10000);
                }
            }
            itemPage++;
        }

    }

}
