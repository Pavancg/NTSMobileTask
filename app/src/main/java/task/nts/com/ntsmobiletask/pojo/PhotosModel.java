package task.nts.com.ntsmobiletask.pojo;

import com.google.gson.annotations.SerializedName;

public class PhotosModel {

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnailUrl")
    private String thumbUrl;

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }
}
