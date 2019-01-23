package com.hyzs.cidyth.common.utils;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * 文件操作工具类
 * Created by pengzk on 2017/6/8.
 */
public class FileUtil {

    /**
     * 文件读取
     * @param path
     * @return
     */
    public static  String readFile(HttpServletRequest request, String path) throws Exception{
        String serverPath = getServerPath(request);
        InputStream inputStream = FileUtil.class.getResourceAsStream(path);
        BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 文件读取
     * @param path
     * @return
     */
    public static void read(HttpServletResponse res, String fileName) throws Exception{
        read(res, fileName);
    }

    /**
     * 输出流
     * @param res 响应
     * @param fileName 文件名
     * @param folderName
     * @return
     * @throws Exception
     */
    public static void read(HttpServletResponse res, String fileName, String folderName) throws Exception{
        if(StringUtils.isNotBlank(fileName)){
            String path = Constant.SERVER_UPLOAD_PATH + folderName + "\\" + fileName;
            ServletOutputStream os = res.getOutputStream();
            InputStream is = new FileInputStream(path);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, len);
            }
            os.flush();
            os.close();
        }
    }

    /**
     * 文件上传方法
     * @param file 上传文件
     * @param request
     * @param fileName 保存后的文件名称
     * @return 返回上传文件在服务器的完整路径
     * @throws Exception 
     */
    public static String upload(MultipartFile file, HttpServletRequest request, String fileName) throws Exception{
        String serverPath = getServerPath(request);
        File targetFile = new File(serverPath, fileName);
        String filePath = targetFile.getPath();
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        //保存
        try {
            file.transferTo(targetFile);
        } catch (Exception e) {
            throw e;
        }
        return filePath;
    }

    /**
     * 文件上传方法
     * @param file 上传文件
     * @return 返回唯一字符串
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception{
        return upload(file, null);
    }

    /**
     * 文件上传方法
     * @param file 上传文件
     * @param path 上传文件路径
     * @return 返回唯一字符串
     * @throws Exception
     */
    public static String upload(MultipartFile file, String path) throws Exception{
        String serverPath = Constant.SERVER_UPLOAD_PATH + (StringUtils.isNotBlank(path) ? path : "");
        File targetFile = new File(serverPath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("fileName = " + fileName);
        // 获取文件后缀格式
        String suffixName = fileName.substring(fileName.indexOf("."));
        // 生成文件名
        String generateName =  UUID.randomUUID().toString().replace("-", "");
        //保存
        try {
            file.transferTo(new File(serverPath, generateName + suffixName));
        } catch (Exception e) {
            throw e;
        }
        return generateName;
    }

    /**
     * 文件上传方法
     * @param file 上传文件
     * @param request
     * @param fileName 保存后的文件名称
     * @return
     */
    /**
     * 多文件上传
     * @param files
     * @param request
     * @throws Exception 
     */
    public static void uploads(MultipartFile[] files, HttpServletRequest request) throws Exception{
        String serverPath = getServerPath(request);
        if(files != null && files.length > 0){
            for(int i=0; i<files.length; i++){
                String file = files[i].getOriginalFilename(); //获得文件名称
                String fileType = file.substring(file.indexOf("."),file.length());
                String timeStamp;
				timeStamp = DateUtil.getTimeStamp();
                String fileName = timeStamp+fileType;
                File targetFile = new File(serverPath, fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                //保存
                try {
                    files[i].transferTo(targetFile);
                } catch (Exception e) {
                    throw e;
                }
            }
        }

    }

    /**
     * 下载文件
     * @param filePath 服务器上的文件完整路径，例如：D:/xxx/xxx.txt
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(HttpServletRequest request, String filePath) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        String serverPath = getServerPath(request);
        File file = new File(serverPath + filePath);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("gb2312"), "iso-8859-1"));
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    /**
     * 下载文件
     * @param filePath 服务端的文件完整路径，如：D:\work.xls
     * @param downLoadName 下载时显示的文件名
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String filePath, String downLoadName) throws Exception{
        if(StringUtils.isEmpty(filePath)){
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        File file = new File(Constant.SERVER_UPLOAD_PATH + filePath);
        // 获取文件后缀格式
        String suffixName = filePath.substring(filePath.indexOf("."));
        /*下载文件名不为空，则获取格式：20180625141523103.xls
        否则获取格式：文件名20180625141523103.xls*/
        if(StringUtils.isEmpty(downLoadName)){
            downLoadName = DateUtil.getTimeStamp() + suffixName;
        }else{
            downLoadName = downLoadName + suffixName;
        }

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        byte[] bytes = null;
        headers.setContentDispositionFormData("attachment", new String(downLoadName.getBytes("gb2312"), "iso-8859-1"));
        bytes = FileUtils.readFileToByteArray(file);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    /**
     * 下载文件
     * @param filePath 服务端的文件完整路径，如：D:\work.xls
     * @return
     * @throws IOException
     */
    public static ResponseEntity<byte[]> download(String filePath) throws Exception {
        return download(filePath, null);
    }

    /**
     * 下载文件
     * @param filePath 服务端的文件完整路径，如：D:\work.xls
     * @return 返回字节数组
     * @throws IOException
     */
    public static byte[] downloadByte(String filePath) throws Exception {
        return download(filePath, null).getBody();
    }

    /**
     * 下载文件
     * @param filePath 服务端的文件完整路径，如：D:\work.xls
     * @return 返回字节数组
     * @throws IOException
     */
    public static String toBase64(String filePath) throws Exception {
        return Base64.encodeBase64String(download(filePath, null).getBody()).replace("\r\n", "");
    }

    /**
     * 获取服务器的上传地址
     * @author 陈铭
     * @date 2018-04-12 14:02:18
     * @param request
     * @return java.lang.String
     */
    private static String getServerPath(HttpServletRequest request) {
        return new File(request.getSession().getServletContext().getRealPath("/")).getParent() + "/upload/";
    }

}
