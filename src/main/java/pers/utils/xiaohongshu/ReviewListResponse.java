package pers.utils.xiaohongshu;

import java.util.List;

/**
 * @author yuejianbin
 * @date 2024/11/7 21:16
 */

public class ReviewListResponse {
    private List<ReviewList> review_list;

    public List<ReviewList> getReview_list() {
        return review_list;
    }

    public void setReview_list(List<ReviewList> review_list) {
        this.review_list = review_list;
    }

    public static class ReviewList {
        /**
         * user_info : {"name":"铛哩个吨儿","avatar":"https://sns-avatar-qc.xhscdn.com/avatar/649ba011d7ca2f223fb975d8.jpg?imageView2/2/w/80/format/jpg"}
         * sku_info : {"item_id":"660ced34f46f6600013ee667","sku_id":"660ced34f46f6600013ee668","variants":[{"name":"款式","id":"","value":"银色金属"},{"name":"套装明细","id":"","value":"1个"}]}
         * button_list : [{"button_type":9,"disabled":0,"text":"回复评价","status":0},{"button_type":7,"disabled":0,"text":"申诉评价","status":0}]
         * review_info : {"review_id":"414687134381900871","logistics_score":5,"sku_score":5,"create_time":1729899517000,"service_score":5,"anonymous":false,"type":4,"descendants":[],"order_id":"P743732167048124531","content":{"images":[],"text":"适合野外露营用 平时放车里不占地方 很实用 随便什么垃圾袋都能套上"},"review_type":4,"tags":[{"name":"好评","id":"410804115013300457"}]}
         */

        private UserInfo user_info;
        private SkuInfo sku_info;
        private ReviewInfo review_info;
        private List<ButtonList> button_list;

        public UserInfo getUser_info() {
            return user_info;
        }

        public void setUser_info(UserInfo user_info) {
            this.user_info = user_info;
        }

        public SkuInfo getSku_info() {
            return sku_info;
        }

        public void setSku_info(SkuInfo sku_info) {
            this.sku_info = sku_info;
        }

        public ReviewInfo getReview_info() {
            return review_info;
        }

        public void setReview_info(ReviewInfo review_info) {
            this.review_info = review_info;
        }

        public List<ButtonList> getButton_list() {
            return button_list;
        }

        public void setButton_list(List<ButtonList> button_list) {
            this.button_list = button_list;
        }

        public static class UserInfo {
            /**
             * name : 铛哩个吨儿
             * avatar : https://sns-avatar-qc.xhscdn.com/avatar/649ba011d7ca2f223fb975d8.jpg?imageView2/2/w/80/format/jpg
             */

            private String name;
            private String avatar;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }

        public static class SkuInfo {
            /**
             * item_id : 660ced34f46f6600013ee667
             * sku_id : 660ced34f46f6600013ee668
             * variants : [{"name":"款式","id":"","value":"银色金属"},{"name":"套装明细","id":"","value":"1个"}]
             */

            private String item_id;
            private String sku_id;
            private List<Variants> variants;

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public List<Variants> getVariants() {
                return variants;
            }

            public void setVariants(List<Variants> variants) {
                this.variants = variants;
            }

            public static class Variants {
                /**
                 * name : 款式
                 * id :
                 * value : 银色金属
                 */

                private String name;
                private String id;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class ReviewInfo {
            /**
             * review_id : 414687134381900871
             * logistics_score : 5
             * sku_score : 5
             * create_time : 1729899517000
             * service_score : 5
             * anonymous : false
             * type : 4
             * descendants : []
             * order_id : P743732167048124531
             * content : {"images":[],"text":"适合野外露营用 平时放车里不占地方 很实用 随便什么垃圾袋都能套上"}
             * review_type : 4
             * tags : [{"name":"好评","id":"410804115013300457"}]
             */

            private String review_id;
            private int logistics_score;
            private int sku_score;
            private long create_time;
            private int service_score;
            private boolean anonymous;
            private int type;
            private String order_id;
            private Content content;
            private int review_type;
            private List<?> descendants;
            private List<ItemInfoResponse.ReviewInfoList.SkuInfo.Variants> tags;

            public String getReview_id() {
                return review_id;
            }

            public void setReview_id(String review_id) {
                this.review_id = review_id;
            }

            public int getLogistics_score() {
                return logistics_score;
            }

            public void setLogistics_score(int logistics_score) {
                this.logistics_score = logistics_score;
            }

            public int getSku_score() {
                return sku_score;
            }

            public void setSku_score(int sku_score) {
                this.sku_score = sku_score;
            }

            public long getCreate_time() {
                return create_time;
            }

            public void setCreate_time(long create_time) {
                this.create_time = create_time;
            }

            public int getService_score() {
                return service_score;
            }

            public void setService_score(int service_score) {
                this.service_score = service_score;
            }

            public boolean isAnonymous() {
                return anonymous;
            }

            public void setAnonymous(boolean anonymous) {
                this.anonymous = anonymous;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public Content getContent() {
                return content;
            }

            public void setContent(Content content) {
                this.content = content;
            }

            public int getReview_type() {
                return review_type;
            }

            public void setReview_type(int review_type) {
                this.review_type = review_type;
            }

            public List<?> getDescendants() {
                return descendants;
            }

            public void setDescendants(List<?> descendants) {
                this.descendants = descendants;
            }

            public List<ItemInfoResponse.ReviewInfoList.SkuInfo.Variants> getTags() {
                return tags;
            }

            public void setTags(List<ItemInfoResponse.ReviewInfoList.SkuInfo.Variants> tags) {
                this.tags = tags;
            }

            public static class Content {
                /**
                 * images : []
                 * text : 适合野外露营用 平时放车里不占地方 很实用 随便什么垃圾袋都能套上
                 */

                private String text;
                private List<?> images;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                public List<?> getImages() {
                    return images;
                }

                public void setImages(List<?> images) {
                    this.images = images;
                }
            }
        }

        public static class ButtonList {
            /**
             * button_type : 9
             * disabled : 0
             * text : 回复评价
             * status : 0
             */

            private int button_type;
            private int disabled;
            private String text;
            private int status;

            public int getButton_type() {
                return button_type;
            }

            public void setButton_type(int button_type) {
                this.button_type = button_type;
            }

            public int getDisabled() {
                return disabled;
            }

            public void setDisabled(int disabled) {
                this.disabled = disabled;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
