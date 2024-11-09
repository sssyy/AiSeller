package pers.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.domain.ReviewCustom;
import pers.utils.xiaohongshu.ReviewListResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreReviewRequest {

    private static final String SCORE_REVIEW_URL = "https://ark.xiaohongshu.com/api/edith/review/seller/review_manager/score_review";
    private static final Logger logger = LoggerFactory.getLogger(ScoreReviewRequest.class);
    private final ObjectMapper objectMapper;


    public ScoreReviewRequest() {
        this.objectMapper = new ObjectMapper();
    }


    public  List<ReviewCustom>  getReviewInfoByItemId(String itemId, Integer page, Integer pageSize) {
        ScoreReviewRequest reviewRequest = new ScoreReviewRequest();

        List<ReviewCustom> reviewCustoms = new ArrayList<ReviewCustom>();
        try {
            String scoreReview = reviewRequest.getScoreReview(itemId, page, pageSize);

            JSONObject jsonObject = JSON.parseObject(scoreReview);
            String data = jsonObject.get("data").toString();

            ObjectMapper objectMapper = new ObjectMapper();

            try {

                ReviewListResponse response = objectMapper.readValue(data, ReviewListResponse.class);



                List<ReviewListResponse.ReviewList> review_list = response.getReview_list();

                for (ReviewListResponse.ReviewList reviewList : review_list) {
                    ReviewCustom reviewCustom = new ReviewCustom();


                    ReviewListResponse.ReviewList.SkuInfo sku_info = reviewList.getSku_info();

                    String item_id = sku_info.getItem_id();
                    reviewCustom.setItemId(item_id);

                    ReviewListResponse.ReviewList.ReviewInfo review_info = reviewList.getReview_info();

                    ReviewListResponse.ReviewList.ReviewInfo.Content content = review_info.getContent();
                    String text = content.getText();
                    reviewCustom.setContent(text);
                    long create_time = review_info.getCreate_time();
                    reviewCustom.setCreateTime(create_time);
                    String review_id = review_info.getReview_id();
                    reviewCustom.setReviewId(review_id);
                    reviewCustoms.add(reviewCustom);
                }
                // 筛选信息
                // 可以继续访问 `response.getData().getReviewInfoList()` 等信息
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Score review: " + scoreReview);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return reviewCustoms;
    }

    public String getScoreReview(String itemId, Integer page, Integer pageSize) {

        StringBuilder content = new StringBuilder();

        try {
            // URL 和请求方法
            URL url = new URL("https://ark.xiaohongshu.com/api/edith/review/seller/review_manager/score_review?page=" + page +  "&page_size=" + pageSize +"&item_id=" + itemId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // 设置请求头
            connection.setRequestProperty("accept", "application/json, text/plain, */*");
            connection.setRequestProperty("accept-language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            connection.setRequestProperty("authorization", "AT-");
            connection.setRequestProperty("priority", "u=1, i");
            connection.setRequestProperty("referer", "https://ark.xiaohongshu.com/app-item/comment/analysis");
            connection.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"130\", \"Microsoft Edge\";v=\"130\", \"Not?A_Brand\";v=\"99\"");
            connection.setRequestProperty("sec-ch-ua-mobile", "?0");
            connection.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");
            connection.setRequestProperty("sec-fetch-dest", "empty");
            connection.setRequestProperty("sec-fetch-mode", "cors");
            connection.setRequestProperty("sec-fetch-site", "same-origin");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0");
            connection.setRequestProperty("x-b3-traceid", "a7962c388c86e827");
            connection.setRequestProperty("x-s", "0gTG0jciO2FLZ2slZYMCZY5iO6wksg9is6aJZjAp0YM3");
            connection.setRequestProperty("x-t", String.valueOf(System.currentTimeMillis()));

            // 发送请求
            connection.connect();

            // 检查响应代码
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 读取响应
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // 打印响应内容
                System.out.println("Response: " + content.toString());

                // 关闭流
                in.close();
            } else {
                System.out.println("Request failed with response code: " + responseCode);
            }

            // 断开连接
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return content.toString();
    }
}
