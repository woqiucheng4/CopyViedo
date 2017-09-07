package video.copy.com.copyviedo;

import java.io.Serializable;

/**
 * <ul>
 * <li>功能职责：</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2017-09-07
 */
public class VideoInfo implements Serializable {

    private String thumbPath;
    private String title;
    private String displayName;
    private String filePath;
    private String mimeType;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
