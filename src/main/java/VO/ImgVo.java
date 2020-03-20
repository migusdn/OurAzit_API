package VO;
/**
 * Created by IntelliJ IDEA.
 * User: migusdn
 * Date: 20. 3. 17.
 * Time: 오전 4:05
 * GitHub: https://migusdn.github.com/
 * Blog: https://migusdn.tistory.com/
 */
public class ImgVo {
    private String mediaType;
    private String originalFileName;
    private String savedPath;
    private long fileSize;
    private String savedName;

    public String getSavedPath() {
        return savedPath;
    }

    public void setSavedPath(String savedPath) {
        this.savedPath = savedPath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getSavedName() {
        return savedName;
    }

    public void setSavedName(String savedName) {
        this.savedName = savedName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
