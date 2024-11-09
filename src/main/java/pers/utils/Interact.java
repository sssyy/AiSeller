package pers.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Interact {

    private static final Logger logger = Logger.getLogger(Interact.class.getName());
    private static final String DIFY_KEY = "";
    private static final String DIFY_URL = "https://api.dify.ai/v1/chat-messages";

    private static final String ITEM_TYPE_PATH = "itemType";
    private static final String ITEM_INTRO_PATH = "itemIntro";
    private static final String REVIEW_INFO_PATH = "reviewInfo";
    private static final String TEXT_PATH = "text";
    private static final String QUERY_PATH = "query";
    private static final String RESPONSE_MODE_PATH = "responseMode";
    private static final String USER_PATH = "user";
    private static final String CONVERSATION_PATH = "conversation";
    private static final String INPUT_PATH = "inputs";

    private static final Map<String, String> itemIDTypeMapping = new HashMap<>();

    static {
        itemIDTypeMapping.put("6564c049474aad0001c7641a", "裤夹|通过裤夹可以将裤子挂起来收纳到衣柜中,简单方便,整齐,省空间");
        itemIDTypeMapping.put("660ced34f46f6600013ee667", "垃圾架|可以适配各种类型的外卖袋和购物袋颜值超高,清洗方便");
        itemIDTypeMapping.put("669b9f0c0916240001ff6ac4", "吊带衣架|一个顶20个的吊带衣架,挂的多,省空间,拿取方便");
        itemIDTypeMapping.put("65e0718b3f330b0001d94c29", "缩脖子衣架|衣架缩脖子设计,省空间,垂直空间节约8厘米");
        itemIDTypeMapping.put("66ad1c39274e530001234bf9", "收纳线槽|桌底收纳电线整齐,方便调整");
    }

    public static void main(String[] args) throws IOException {

        Param param = new Param();
        param.setItemId("6564c049474aad0001c7641a");
        param.setItemInfo("通过裤夹可以将裤子挂起来收纳到衣柜中,简单方便,整齐,省空间");
        param.setReviewContent("不要买！ 质量很差 ！跟我之前买的一点也不一样！合住都对不上！");
        String s = new Interact().sendRequest( param);

    }
    public String sendRequest( Param param) throws IOException {

        // Prepare request body
        JSONObject jsonObject = newRequestBody( param);
        String bodyStr = jsonObject.toString();

        // Log the request body
        logger.info(String.format("new request body: %s with param: %s", bodyStr, param));

//        String proxyHost = "127.0.0.1";
//        int proxyPort = 17890;  // 代理端口
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        URL url = new URL("https://api.dify.ai/v1/chat-messages");
        URLConnection urlConnection = url.openConnection();
        HttpsURLConnection connection = (HttpsURLConnection) urlConnection;
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // 设置连接超时时间和读取超时时间
        connection.setConnectTimeout(120000); // 连接超时，单位：毫秒
        connection.setReadTimeout(120000);    // 读取超时，单位：毫秒
        connection.setRequestProperty("Authorization", DIFY_KEY);
        connection.setRequestProperty("Content-Type", "application/json");

        byte[] byteArray = bodyStr.getBytes("UTF-8");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            outputStream.write(byteArray);
            connection.getOutputStream().write(outputStream.toByteArray());
        } catch (IOException e) {
            throw new IOException("Failed to write request body: " + bodyStr, e);
        }

        // Send request and handle response
        try (InputStream responseStream = connection.getInputStream()) {
            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                String responseBody = IOUtils.toString(responseStream, "UTF-8");
                logger.severe(String.format("get dify response error. statusCode: %d responseBody: %s", statusCode, responseBody));
                throw new IOException(String.format("Dify response error. StatusCode: %d, ResponseBody: %s", statusCode, responseBody));
            }

            // Read the response body
            String responseBody = IOUtils.toString(responseStream, "UTF-8");

            // Return the answer
            return responseBody;
        } catch (IOException e) {
            throw new IOException(String.format("Request failed with param: %s, body: %s", param, bodyStr), e);
        }
    }


    // Simulate the method to create a new request body
    private JSONObject newRequestBody(Param param) {
        // Initialize an empty JSON object
        JSONObject queryJsonData = new JSONObject();

        // Log the start of the conversion
        logger.info("--正在转化商品信息--");

        // Get the item type info based on itemId
        String itemTypeInfo = itemIDTypeMapping.get(param.getItemId());
        if (itemTypeInfo == null) {
            throw new RuntimeException(String.format("could not find itemtype by itemid. SearchParam:%s", param));
        }

        // Split itemTypeInfo into item type and intro
        String[] splitItemTypeInfo = itemTypeInfo.split("\\|");
//        logFromContext(ctx, String.format("itemId:%s -> itemType:%s", param.getItemId(), splitItemTypeInfo[0]));
//        logFromContext(ctx, String.format("text:%s", splitItemTypeInfo[1]));

        // Set the itemType and itemIntro in the query JSON
        queryJsonData.put(ITEM_TYPE_PATH, splitItemTypeInfo[0]);
        queryJsonData.put(ITEM_INTRO_PATH, splitItemTypeInfo[1]);

        // Add review info to the query JSON
        queryJsonData.put(REVIEW_INFO_PATH + "." + TEXT_PATH, param.getReviewContent());

        // Create a new JSON object for the overall request (difyData)
        JSONObject difyData = new JSONObject();

        // Set the query JSON, response mode, user, and conversation info
        difyData.put(QUERY_PATH, queryJsonData);
        difyData.put(RESPONSE_MODE_PATH, "blocking");
        difyData.put(USER_PATH, "abc-123");
        difyData.put(CONVERSATION_PATH, "");

        // Set raw input JSON (empty object here, similar to the Go code)
        JSONObject emptyInput = new JSONObject();
        difyData.put(INPUT_PATH, emptyInput);

        // Return the final constructed JSON object
        return difyData;
    }

    // Simulate withdrawal of an answer from the response

    public static class Param {
        private String itemId;
        private String itemInfo;
        private String reviewContent;

        // Getters and Setters
        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getItemInfo() {
            return itemInfo;
        }

        public void setItemInfo(String itemInfo) {
            this.itemInfo = itemInfo;
        }

        public String getReviewContent() {
            return reviewContent;
        }

        public void setReviewContent(String reviewContent) {
            this.reviewContent = reviewContent;
        }
        // Implement the param class
    }


}
