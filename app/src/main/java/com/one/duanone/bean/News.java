package com.one.duanone.bean;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  16:30.
 */
public class News {

    private String message;
    private String tip;
    private Group group;
    private LiveNew liveNew;


    public LiveNew getLiveNew() {
        return liveNew;
    }

    public void setLiveNew(LiveNew liveNew) {
        this.liveNew = liveNew;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public static class Group {
        private int type;
        private User user;
        private int media_type;
        private int bury_count;
        private int category_id;
        private int comment_count;
        private int digg_count;
        private int favorite_count;
        private int go_detail_count;
        private int group_id;
        private int share_count;
        private ImageNew imageNew;

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        private String category_name;
        private String content;
        private String title;
        private String share_url;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getMedia_type() {
            return media_type;
        }

        public void setMedia_type(int media_type) {
            this.media_type = media_type;
        }

        public int getBury_count() {
            return bury_count;
        }

        public void setBury_count(int bury_count) {
            this.bury_count = bury_count;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getDigg_count() {
            return digg_count;
        }

        public void setDigg_count(int digg_count) {
            this.digg_count = digg_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getGo_detail_count() {
            return go_detail_count;
        }

        public void setGo_detail_count(int go_detail_count) {
            this.go_detail_count = go_detail_count;
        }

        public int getGroup_id() {
            return group_id;
        }

        public void setGroup_id(int group_id) {
            this.group_id = group_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }
    }

    public static class OriginVideo {

        private int play_count;
        private String medium_cover_url;
        private String origin_video_url;
        private float duration;

        public int getPlay_count() {
            return play_count;
        }

        public void setPlay_count(int play_count) {
            this.play_count = play_count;
        }

        public String getMedium_cover_url() {
            return medium_cover_url;
        }

        public void setMedium_cover_url(String medium_cover_url) {
            this.medium_cover_url = medium_cover_url;
        }

        public String getOrigin_video_url() {
            return origin_video_url;
        }

        public void setOrigin_video_url(String origin_video_url) {
            this.origin_video_url = origin_video_url;
        }

        public float getDuration() {
            return duration;
        }

        public void setDuration(float duration) {
            this.duration = duration;
        }
    }

    /**
     * yong hu
     */
    public static class User {

        private String name;
        private int user_id;
        private String avatar_url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }
    }

    public static class LiveNew {
        private String liveUrl;
        private String imgUrl;
        private String city;
        private int fan_ticket_count;
        private int fan_ticket;
        private String birthday_description;
        private User user;


        public String getLiveUrl() {
            return liveUrl;
        }

        public void setLiveUrl(String liveUrl) {
            this.liveUrl = liveUrl;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getFan_ticket_count() {
            return fan_ticket_count;
        }

        public void setFan_ticket_count(int fan_ticket_count) {
            this.fan_ticket_count = fan_ticket_count;
        }

        public int getFan_ticket() {
            return fan_ticket;
        }

        public void setFan_ticket(int fan_ticket) {
            this.fan_ticket = fan_ticket;
        }

        public String getBirthday_description() {
            return birthday_description;
        }

        public void setBirthday_description(String birthday_description) {
            this.birthday_description = birthday_description;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    public static class ImageNew {
        private int r_width;
        private int r_height;
        private String imgUrl;

        public int getR_width() {
            return r_width;
        }

        public void setR_width(int r_width) {
            this.r_width = r_width;
        }

        public int getR_height() {
            return r_height;
        }

        public void setR_height(int r_height) {
            this.r_height = r_height;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }

    @Override
    public String toString() {
        if (group == null) {
            return "null";
        }
        return "用户：" + group.getUser().getName() + "：消息类型：" + group.getMedia_type() + ": 内容：" + group.getContent();
    }
}
