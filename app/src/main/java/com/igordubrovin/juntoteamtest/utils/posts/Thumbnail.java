
package com.igordubrovin.juntoteamtest.utils.posts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("media_type")
    @Expose
    private String mediaType;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
