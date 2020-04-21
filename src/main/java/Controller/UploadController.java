package Controller;

import Upload.UploadFileUtils;
import VO.ImgVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

/* sourced by :https://doublesprogramming.tistory.com/130*/
@Controller
public class UploadController {

    @Autowired
    String uploadPath;


    @RequestMapping(value="/upload/uploadAjax", method=RequestMethod.GET)
    public void uploadAjax(){
        // uploadAjax.jsp로 포워딩
    }


    @RequestMapping(value = "/upload/Form")
    public String Form() {
        return "FormUpload";
    }


    @RequestMapping(value = "/upload/uploadForm", method = RequestMethod.POST)
    public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception {
        String savedName = file.getOriginalFilename();
        File target = new File(uploadPath, savedName);
        FileCopyUtils.copy(file.getBytes(), target);
        mav.setViewName("FormUploadResult");
        mav.addObject("savedName", savedName);
        return mav;
    }

    //file upload controller
    @CrossOrigin(origins = {"http://ourazit.com", "http://localhost:8080"})
    @ResponseBody
    @RequestMapping(value = "upload")
    public List<ImgVo> uploadmul(MultipartHttpServletRequest request) throws Exception {
        List<MultipartFile> fileList = request.getFiles("file");
        String src = request.getParameter("src");
        System.out.println(fileList.size());
        /*for(MultipartFile mf : fileList){
            String originalFileName = mf.getOriginalFilename();
            long fileSize = mf.getSize();
            System.out.println("originalFileName: "+ originalFileName);
            System.out.println("fileSize: "+ fileSize);
            String safeFile = uploadPath+System.currentTimeMillis()+originalFileName;
            try{
                mf.transferTo((new File(safeFile)));
            }catch (IllegalStateException | IOException e){
                e.printStackTrace();
            }
        }*/
        List<ImgVo> ImgList = UploadFileUtils.uploadFile(uploadPath, fileList);
        return ImgList;
    }





}

