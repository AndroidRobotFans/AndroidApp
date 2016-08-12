package com.one.duanone.bean;

/**
 * PC: Masterr_Robot.
 * Created by DKL on 2016/7/25  22:29.
 */
public class Pages {
    private String pageUrl;
    private String pageTitle;
    private int k;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public void setPageTitle(String pageTitle) {

        this.pageTitle = pageTitle;
    }

    /**
     * 重写 toString() 方法, 方便打印log日志, 观察数据解析
     * @return
     */
    @Override
    public String toString() {
        String srt = "name: " + pageTitle + ": url: " + pageUrl;
        return srt;
    }
}
