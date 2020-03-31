package Upload;


import VO.ImgVo;
import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class UploadFileUtils {

    public static List<ImgVo> uploadFile(String uploadPath, List<MultipartFile> fileList) throws Exception {
        System.out.println("uploadFile method access");
        // UUID 발급

        // 파일 경로(기존의 업로드경로+날짜별경로), 파일명을 받아 파일 객체 생성
        //File target = new File(uploadPath + savedPath, savedName);
        // 임시 디렉토리에 업로드된 파일을 지정된 디렉토리로 복사
        //FileCopyUtils.copy(fileData, target);
        // 썸네일을 생성하기 위한 파일의 확장자 검사
        // 파일명이 aaa.bbb.ccc.jpg일 경우 마지막 마침표를 찾기 위해

        //ImgVo ArrList 생성
        List<ImgVo> ImgList = new ArrayList<ImgVo>();
        ImgVo Temp;
        String savedPath = calcPath(uploadPath);
        if (fileList != null)
            System.out.println(fileList.size());
        else
            System.out.println("fileList is null");
        for (MultipartFile mf : fileList) {
            Temp = new ImgVo();
            String originalFileName = mf.getOriginalFilename();
            System.out.println(originalFileName);
            UUID uuid = UUID.randomUUID();
            // 저장할 파일명 = UUID + 원본이름
            String savedName = uuid.toString() + "_" + originalFileName;
            String safeFile = uploadPath + savedPath + File.separator + savedName;
            // 업로드할 디렉토리(날짜별 폴더) 생성
            long fileSize = mf.getSize();
            System.out.println("originalFileName: " + originalFileName);
            System.out.println("fileSize: " + fileSize);
            System.out.println("savedpath: " + safeFile);
            try {
                mf.transferTo((new File(safeFile)));
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            String formatName = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            String uploadedFileName = null;
            // 이미지 파일은 썸네일 사용
            if (MediaUtils.getMediaType(formatName) != null) {
                // 썸네일 생성
                uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
                // 나머지는 아이콘
            } else {
                // 아이콘 생성
                uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
            }
            Temp.setFileSize(fileSize);
            Temp.setMediaType(formatName);
            Temp.setOriginalFileName(originalFileName);
            Temp.setSavedName(savedName);
            Temp.setSavedPath(savedPath);
            ImgList.add(Temp);
        }
        System.out.println(ImgList.toString());
        return ImgList;
    }

    // 날짜별 디렉토리 추출
    private static String calcPath(String uploadPath) {
        System.out.println("calcpath method");
        Calendar cal = Calendar.getInstance();
        // File.separator : 디렉토리 구분자(\\)
        // 연도, ex) \\2017
        String yearPath = File.separator + cal.get(Calendar.YEAR);
        //System.out.println(yearPath);
        // 월, ex) \\2017\\03
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        //System.out.println(monthPath);
        // 날짜, ex) \\2017\\03\\01
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
        //System.out.println(datePath);
        // 디렉토리 생성 메서드 호출
        makeDir(uploadPath, yearPath, monthPath, datePath);
        return datePath;
    }

    // 디렉토리 생성
    private static void makeDir(String uploadPath, String... paths) {
        // 디렉토리가 존재하면
        System.out.println("makeDir method access");
        if (new File(paths[paths.length - 1]).exists()){
            System.out.println("dir is exist");
            return;
        }
        // 디렉토리가 존재하지 않으면
        for (String path : paths) {

            //
            File dirPath = new File(uploadPath + path);
            // 디렉토리가 존재하지 않으면
            if (!dirPath.exists()) {
                dirPath.mkdir(); //디렉토리 생성
            }
        }
    }

    // 썸네일 생성
    private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
        System.out.println("makeThumbnail method access");
        // 이미지를 읽기 위한 버퍼
        BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
        // 100픽셀 단위의 썸네일 생성
        BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
        // 썸네일의 이름을 생성(원본파일명에 's_'를 붙임)
        String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
        File newFile = new File(thumbnailName);
        String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 썸네일 생성
        ImageIO.write(destImg, formatName.toUpperCase(), newFile);
        // 썸네일의 이름을 리턴함
        return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }

    // 아이콘 생성
    private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
        System.out.println("makeIcon method access");
        // 아이콘의 이름
        String iconName = uploadPath + path + File.separator + fileName;
        // 아이콘 이름을 리턴
        // File.separatorChar : 디렉토리 구분자
        // 윈도우 \ , 유닉스(리눅스) /
        return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
    }
}
