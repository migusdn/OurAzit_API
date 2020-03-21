package VO;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: migusdn
 * Date: 20. 3. 17.
 * Time: 오전 4:05
 * GitHub: https://migusdn.github.com/
 * Blog: https://migusdn.tistory.com/
 */
@Data
public class ImgVo {
    private String mediaType;
    private String originalFileName;
    private String savedPath;
    private long fileSize;
    private String savedName;
}
