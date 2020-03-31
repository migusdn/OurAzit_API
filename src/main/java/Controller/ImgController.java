/**
 * Created by IntelliJ IDEA.
 * User: migusdn
 * Date: 20. 3. 31.
 * Time: 오전 1:06
 * GitHub: https://migusdn.github.com/
 * Blog: https://migusdn.tistory.com/
 */
package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class ImgController {
    @Autowired
    String uploadPath;

    @RequestMapping(value = {"/img/{year}/{month}/{day}/{img_name:.+}"}, method = RequestMethod.GET)
    public void setImageFileByName(@PathVariable(name = "year") String year, @PathVariable(name = "month") String month, @PathVariable(name = "day") String day, @PathVariable(name = "img_name") String img_name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //file real path
        String filePath = uploadPath + year + File.separator + month + File.separator + day;
        String fileName = img_name;
        //System.out.println("File_name: "+img_name);
        BufferedOutputStream out = null;
        InputStream in = null;

        try {
            response.setContentType("image");
            response.setHeader("Content-Disposition", "inline");
            System.out.println("real file path:" + filePath + File.separator + fileName);
            File file = new File(filePath + File.separator + fileName);

            if (file.exists()) {
                System.out.println("File is exists");
                in = new FileInputStream(file);
                out = new BufferedOutputStream(response.getOutputStream());
                int len;
                byte[] buf = new byte[1024];
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);

                }
            }
        } catch (Exception e) {

        } finally {
            if (out != null) {
                out.flush();
            }
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        // StringBuilder filePath = new StringBuilder(uploadPath);


    }
}
