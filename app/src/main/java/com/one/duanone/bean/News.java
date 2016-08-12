package com.one.duanone.bean;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/23  16:30.
 */
public class News {

    /**
     * 消息类型. 有message的是普通类型, 没有的是直播
     * 若是: success 则表明这条数据没有问题
     */
    private String message;
    /**
     * 一次更新了几条数据
     */
    private String tip;
    /**
     * 除了每条消息内容等属性, 都存在group里面
     */
    private Group group;
    /**
     * 直播对象
     */
    private LiveNew liveNew;
    /**
     * 是否是直播 ,true为直播
     */
    private boolean isLive = false;

    public boolean isLive() {
        return isLive;
    }

    public void setIsLive(boolean live) {
        isLive = live;
    }

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
        /**
         * 类型:
         */
        private int type;
        /**
         * 用户, 发消息的用户, 没有则是匿名
         */
        private User user;
        /**
         * 消息类型 0 - 4
         * 0: 纯文本
         * 1: 只有图片
         * 2: git 图
         * 3: 视频
         * 4: 图片
         */
        private int media_type;
        /**
         * 踩数
         */
        private int bury_count;
        /**
         * 内容分类的 id:  如每日精选, 今天张这样等
         */
        private int category_id;
        /**
         * 评论数量
         */
        private int comment_count;
        /**
         * 点赞数
         */
        private int digg_count;
        /**
         * 收藏数
         */
        private int favorite_count;
        /**
         * 忘记了
         */
        private int go_detail_count;
        /**
         * 组id:
         */
        private int group_id;
        /**
         * 分享次数
         */
        private int share_count;
        /**
         * 图片类型的内容
         */
        private ImageNew imageNew;

        /**
         * 视屏消息的内容
         */
        private OriginVideo originVideo;
        /**
         * 已经踩过
         */
        private boolean isBury;
        /**
         * 以及顶过
         */
        private boolean isDigg;

        public boolean isBury() {
            return isBury;
        }

        public void setBury(boolean bury) {
            isBury = bury;
        }

        public boolean isDigg() {
            return isDigg;
        }

        public void setDigg(boolean digg) {
            isDigg = digg;
        }

        public OriginVideo getOriginVideo() {
            return originVideo;
        }

        public void setOriginVideo(OriginVideo originVideo) {
            this.originVideo = originVideo;
        }

        public ImageNew getImageNew() {
            return imageNew;
        }

        public void setImageNew(ImageNew imageNew) {
            this.imageNew = imageNew;
        }

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

    /**
     * 视频类型
     */
    public static class OriginVideo {

        /**
         * 播放次数
         */
        private int play_count;
        /**
         * 视频封面image地址
         */
        private String medium_cover_url;
        /**
         * 视频地址
         */
        private String origin_video_url;
        /**
         * 视频时长 单位是: 秒"
         */
        private int duration;

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

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }

    /**
     * 用户信息
     */
    public static class User {

        /**
         * 用户名字
         */
        private String name;
        /**
         * 用户ID
         */
        private int user_id;
        /**
         * 用户头像URL
         */
        private String avatar_url;

        /**
         * 发表评论时间
         */
        private int time;
        /**
         * 个人主页-自己以往的评论
         */
        private String personal_comment;
        /**
         * 系统通知
         */
        private String inform;

        public String getInform() {
            return inform;
        }

        public void setInform(String inform) {
            this.inform = inform;
        }

        public String getPersonal_comment() {
            return personal_comment;
        }

        public void setPersonal_comment(String personal_comment) {
            this.personal_comment = personal_comment;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

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

    /**
     * 直播类型的内容
     */
    public static class LiveNew {

        /**
         * 直播的信息请求URL
         */
        private String liveUrl;
        /**
         * 直播封面图 url
         */
        private String imgUrl;
        /**
         * 房主地址
         */
        private String city;
        /**
         * 粉丝数量
         */
        private int fan_ticket_count;
        /**
         * 在线人数
         */
        private int user_count;
        private int id;
        /**
         * 星座
         */
        private String constellation;

        public String getConstellation() {
            return constellation;
        }

        public void setConstellation(String constellation) {
            this.constellation = constellation;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_count() {
            return user_count;
        }

        public void setUser_count(int user_count) {
            this.user_count = user_count;
        }

        /**
         * 主播星座
         */
        private String birthday_description;
        /**
         * 主播的信息
         */
        private User user;

        public LiveNew() {
            user = new User();
        }

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
        if (getGroup() == null) {
            return "null";
        }
        String content = "用户：" + group.getUser().getName() + "：消息类型：" + group.getMedia_type() + ": 内容：" + group.getContent();
        if (message != null) {
            if (getGroup().getMedia_type() == 3) {
                return content + ": 视频地址: " + getGroup().getOriginVideo().getOrigin_video_url();
            }
            if (getGroup().getMedia_type() == 1 || getGroup().getMedia_type() == 2 || getGroup().getMedia_type() == 4) {
                ImageNew imageNew = getGroup().getImageNew();
                if (imageNew != null)
                    return content + ": 图片地址: " + getGroup().getImageNew().getImgUrl();
            }
        }
        return content;
    }
}
