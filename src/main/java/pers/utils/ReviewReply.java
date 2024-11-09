package pers.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewReply {

    private static final String XHS_REVIEW_REPLY_URL = "https://ark.xiaohongshu.com/api/edith/review/seller_reply";
    private static final int REPLY_SUCCESS = 0;

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Constructor to initialize HTTP client and ObjectMapper
    public ReviewReply() {
        this.httpClient = HttpClients.createDefault();
        this.objectMapper = new ObjectMapper();
    }

    public boolean replyTrue(ReviewReplyParam reviewReplyParam) {
        return true;
    }

    // Request parameter class
    public static class ReviewReplyParam {
        private List<String> reviewIds;
        private String replyContent;

        public List<String> getReviewIds() {
            return reviewIds;
        }

        public void setReviewIds(List<String> reviewIds) {
            this.reviewIds = reviewIds;
        }

        public String getReplyContent() {
            return replyContent;
        }

        public void setReplyContent(String replyContent) {
            this.replyContent = replyContent;
        }
    }

    // Method to generate request body
    private String generateRequestBody(ReviewReplyParam param) throws IOException {
        // Validate param (simple check, can be extended)
        if (param.getReviewIds() == null || param.getReviewIds().isEmpty()) {
            throw new IllegalArgumentException("ReviewIds are required");
        }
        if (param.getReplyContent() == null || param.getReplyContent().isEmpty()) {
            throw new IllegalArgumentException("ReplyContent is required");
        }

        // Create a Map to store the request body fields
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("review_id", param.getReviewIds());
        requestBody.put("content", param.getReplyContent());

        // Convert the Map to JSON string
        return objectMapper.writeValueAsString(requestBody);
    }

    // Method to verify response
    private void verifyResponse(String responseBody) throws IOException {
        // Assuming responseBody is a JSON string with "code" and "msg" fields
        Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
        int code = (int) responseMap.get("code");
        if (code != REPLY_SUCCESS) {
            String msg = (String) responseMap.get("msg");
            throw new IOException("Error: " + msg);
        }
    }

    // Method to reply to reviews
    public boolean reply(ReviewReplyParam param) throws IOException {

        // Step 1: Generate the request body
        String requestBody = generateRequestBody(param);

        // Step 2: Create HTTP POST request
        HttpPost httpPost = new HttpPost(XHS_REVIEW_REPLY_URL);
        StringEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("User-Agent", "Mozilla/5.0");

        // Step 3: Execute the request
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // Check the status code
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new IOException("Failed to reply, status code: " + statusCode);
            }

            // Step 4: Read and verify the response
            String responseBody = EntityUtils.toString(response.getEntity());
            verifyResponse(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Main method for testing the reply functionality
    public static void main(String[] args) {
        try {
            // Example usage
            ReviewReply reviewReply = new ReviewReply();

            ReviewReplyParam param = new ReviewReplyParam();
            ArrayList<String> strings = new ArrayList<>();
            strings.add("414870205050713845");
            param.setReviewIds(strings);
            param.setReplyContent("Thank you for your feedback!");

            reviewReply.reply(param);
            System.out.println("Reply sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
