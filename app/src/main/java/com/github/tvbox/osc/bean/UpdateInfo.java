package com.github.tvbox.osc.bean;

import com.google.gson.annotations.SerializedName;

public class UpdateInfo {
    @SerializedName("version")
    private String version;
    @SerializedName("downloadUrl")
    private String downloadUrl;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("isMustUpdate")
    private boolean isMustUpdate;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMustUpdate() {
        return isMustUpdate;
    }

    public void setMustUpdate(boolean mustUpdate) {
        isMustUpdate = mustUpdate;
    }
}
