package pers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ItemReviewRequest {


    private static final String URL = "https://ark.xiaohongshu.com/api/edith/review/v2/seller/review_manager";
    private static final Logger logger = LoggerFactory.getLogger(ItemReviewRequest.class);
    private final ObjectMapper objectMapper;


    public ItemReviewRequest() {
        this.objectMapper = new ObjectMapper();
    }

    public String sendReviewRequest(int pageSize, int page, int source) throws IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
            connection.setRequestProperty("Authorization", "AT-"); // 替换为实际 token
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Referer", "https://ark.xiaohongshu.com/app-item/comment/analysis");
            connection.setRequestProperty("Origin", "https://ark.xiaohongshu.com");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36");
            connection.setRequestProperty("X-B3-Traceid", "92d1aeba44557f62");
            connection.setRequestProperty("X-S", "O2MlO61bZjsCO2qUsBdvZ6F+OgsbsYMGOisb02qUOls3");
            connection.setRequestProperty("X-T", String.valueOf(System.currentTimeMillis())); // 使用当前时间戳

            // 创建请求体
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("page_size", pageSize);
            requestBody.put("page", page);
            requestBody.put("source", source);

            // 发送请求
            try (OutputStream os = connection.getOutputStream()) {
                String jsonInputString = objectMapper.writeValueAsString(requestBody);
                os.write(jsonInputString.getBytes(StandardCharsets.UTF_8)); // 指定编码为 UTF-8
                os.flush();
            }

            int statusCode = connection.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                logger.error("Request failed with status code: {}. Response: {}", statusCode, readResponse(connection));
                throw new IOException("Request failed with status code: " + statusCode);
            }

            // 读取响应
            return readResponse(connection);

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String readResponse(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }
        return response.toString();
    }

    public static void main(String[] args) {
        ItemReviewRequest reviewRequest = new ItemReviewRequest();
        try {
            String response = reviewRequest.sendReviewRequest(1, 1, 0);
            System.out.println("Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
