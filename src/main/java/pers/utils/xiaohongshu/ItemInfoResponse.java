package pers.utils.xiaohongshu;

import java.util.List;

/**
 * @author yuejianbin
 * @date 2024/11/7 21:42
 */

public class ItemInfoResponse {
    /**
     * review_info_list : [{"sku_info":{"sku_id":"662541bfe265da0001b257b6","name":"【笔记同款】不锈钢带钩裤夹多功能夹子无痕帽子短裤 【🌟第二代-双头浸胶】米白 买20送20--共40个","image_link":"//qimg.xiaohongshu.com/arkgoods/104100ao311rhh21r10066dc63ejjl00006d2b7kf0mgsg?itemId=662541bfe265da0001b257b6&imageView2/1/w/320/h/320/q/90.jpeg","item_id":"6564c049474aad0001c7641a","order_id":"P746392609891098281","price":3400,"quantity":1,"variants":[{"value":"【🌟第二代-双头浸胶】米白","id":"5a60c42f69bd891ed8939bc2","name":"款式"},{"id":"5a60c42f69bd891ed8939a42","name":"包装数量","value":"买20送20--共40个"}]},"review_data":{"logistics_score":5,"content":{"text":"","images":[]},"create_time":1730974282,"tags":[],"descendants":[],"search_id":"96879949","review_id":"419195025081438675","sku_score":5,"service_score":5,"review_type":4,"anonymous":false},"interation_info":{"reply_num":0,"like_num":0},"button_list":[{"text":"申诉评价","disabled":0,"button_type":7,"status":0}]}]
     * total : 1426
     */

    private int total;
    private List<ReviewInfoList> review_info_list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ReviewInfoList> getReview_info_list() {
        return review_info_list;
    }

    public void setReview_info_list(List<ReviewInfoList> review_info_list) {
        this.review_info_list = review_info_list;
    }

    public static class ReviewInfoList {
        /**
         * sku_info : {"sku_id":"662541bfe265da0001b257b6","name":"【笔记同款】不锈钢带钩裤夹多功能夹子无痕帽子短裤 【🌟第二代-双头浸胶】米白 买20送20--共40个","image_link":"//qimg.xiaohongshu.com/arkgoods/104100ao311rhh21r10066dc63ejjl00006d2b7kf0mgsg?itemId=662541bfe265da0001b257b6&imageView2/1/w/320/h/320/q/90.jpeg","item_id":"6564c049474aad0001c7641a","order_id":"P746392609891098281","price":3400,"quantity":1,"variants":[{"value":"【🌟第二代-双头浸胶】米白","id":"5a60c42f69bd891ed8939bc2","name":"款式"},{"id":"5a60c42f69bd891ed8939a42","name":"包装数量","value":"买20送20--共40个"}]}
         * review_data : {"logistics_score":5,"content":{"text":"","images":[]},"create_time":1730974282,"tags":[],"descendants":[],"search_id":"96879949","review_id":"419195025081438675","sku_score":5,"service_score":5,"review_type":4,"anonymous":false}
         * interation_info : {"reply_num":0,"like_num":0}
         * button_list : [{"text":"申诉评价","disabled":0,"button_type":7,"status":0}]
         */

        private SkuInfo sku_info;
        private ReviewData review_data;
        private InterationInfo interation_info;
        private List<ButtonList> button_list;

        public SkuInfo getSku_info() {
            return sku_info;
        }

        public void setSku_info(SkuInfo sku_info) {
            this.sku_info = sku_info;
        }

        public ReviewData getReview_data() {
            return review_data;
        }

        public void setReview_data(ReviewData review_data) {
            this.review_data = review_data;
        }

        public InterationInfo getInteration_info() {
            return interation_info;
        }

        public void setInteration_info(InterationInfo interation_info) {
            this.interation_info = interation_info;
        }

        public List<ButtonList> getButton_list() {
            return button_list;
        }

        public void setButton_list(List<ButtonList> button_list) {
            this.button_list = button_list;
        }

        public static class SkuInfo {
            /**
             * sku_id : 662541bfe265da0001b257b6
             * name : 【笔记同款】不锈钢带钩裤夹多功能夹子无痕帽子短裤 【🌟第二代-双头浸胶】米白 买20送20--共40个
             * image_link : //qimg.xiaohongshu.com/arkgoods/104100ao311rhh21r10066dc63ejjl00006d2b7kf0mgsg?itemId=662541bfe265da0001b257b6&imageView2/1/w/320/h/320/q/90.jpeg
             * item_id : 6564c049474aad0001c7641a
             * order_id : P746392609891098281
             * price : 3400
             * quantity : 1
             * variants : [{"value":"【🌟第二代-双头浸胶】米白","id":"5a60c42f69bd891ed8939bc2","name":"款式"},{"id":"5a60c42f69bd891ed8939a42","name":"包装数量","value":"买20送20--共40个"}]
             */

            private String sku_id;
            private String name;
            private String image_link;
            private String item_id;
            private String order_id;
            private int price;
            private int quantity;
            private List<Variants> variants;

            public String getSku_id() {
                return sku_id;
            }

            public void setSku_id(String sku_id) {
                this.sku_id = sku_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage_link() {
                return image_link;
            }

            public void setImage_link(String image_link) {
                this.image_link = image_link;
            }

            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public List<Variants> getVariants() {
                return variants;
            }

            public void setVariants(List<Variants> variants) {
                this.variants = variants;
            }

            public static class Variants {
                /**
                 * value : 【🌟第二代-双头浸胶】米白
                 * id : 5a60c42f69bd891ed8939bc2
                 * name : 款式
                 */

                private String value;
                private String id;
                private String name;

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }

        public static class ReviewData {
            /**
             * logistics_score : 5
             * content : {"text":"","images":[]}
             * create_time : 1730974282
             * tags : []
             * descendants : []
             * search_id : 96879949
             * review_id : 419195025081438675
             * sku_score : 5
             * service_score : 5
             * review_type : 4
             * anonymous : false
             */

            private int logistics_score;
            private Content content;
            private int create_time;
            private String search_id;
            private String review_id;
            private int sku_score;
            private int service_score;
            private int review_type;
            private boolean anonymous;
            private List<?> tags;
            private List<?> descendants;

            public int getLogistics_score() {
                return logistics_score;
            }

            public void setLogistics_score(int logistics_score) {
                this.logistics_score = logistics_score;
            }

            public Content getContent() {
                return content;
            }

            public void setContent(Content content) {
                this.content = content;
            }

            public int getCreate_time() {
                return create_time;
            }

            public void setCreate_time(int create_time) {
                this.create_time = create_time;
            }

            public String getSearch_id() {
                return search_id;
            }

            public void setSearch_id(String search_id) {
                this.search_id = search_id;
            }

            public String getReview_id() {
                return review_id;
            }

            public void setReview_id(String review_id) {
                this.review_id = review_id;
            }

            public int getSku_score() {
                return sku_score;
            }

            public void setSku_score(int sku_score) {
                this.sku_score = sku_score;
            }

            public int getService_score() {
                return service_score;
            }

            public void setService_score(int service_score) {
                this.service_score = service_score;
            }

            public int getReview_type() {
                return review_type;
            }

            public void setReview_type(int review_type) {
                this.review_type = review_type;
            }

            public boolean isAnonymous() {
                return anonymous;
            }

            public void setAnonymous(boolean anonymous) {
                this.anonymous = anonymous;
            }

            public List<?> getTags() {
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
            }

            public List<?> getDescendants() {
                return descendants;
            }

            public void setDescendants(List<?> descendants) {
                this.descendants = descendants;
            }

            public static class Content {
                /**
                 * text :
                 * images : []
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

        public static class InterationInfo {
            /**
             * reply_num : 0
             * like_num : 0
             */

            private int reply_num;
            private int like_num;

            public int getReply_num() {
                return reply_num;
            }

            public void setReply_num(int reply_num) {
                this.reply_num = reply_num;
            }

            public int getLike_num() {
                return like_num;
            }

            public void setLike_num(int like_num) {
                this.like_num = like_num;
            }
        }

        public static class ButtonList {
            /**
             * text : 申诉评价
             * disabled : 0
             * button_type : 7
             * status : 0
             */

            private String text;
            private int disabled;
            private int button_type;
            private int status;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public int getDisabled() {
                return disabled;
            }

            public void setDisabled(int disabled) {
                this.disabled = disabled;
            }

            public int getButton_type() {
                return button_type;
            }

            public void setButton_type(int button_type) {
                this.button_type = button_type;
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
