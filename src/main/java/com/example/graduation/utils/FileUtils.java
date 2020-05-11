package com.example.graduation.utils;

import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: huiyuan
 * @date: 2020/5/9 17:48
 * @desc: 这是描述
 **/
@Slf4j
public class FileUtils {
    public static AjaxVoResult saveUploadFile(MultipartFile file){
        String basePath = "~".concat(File.separator).concat("enclosure");
        String filePath = basePath.concat(File.separator).concat(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).concat("-").concat(file.getOriginalFilename());
        File dir = new File(basePath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        File uploadExcel = new File(filePath);
        if (file != null && file.getSize() > 0) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(uploadExcel)) {
                fileOutputStream.write(file.getBytes());
                fileOutputStream.flush();
                log.info("文件：" + filePath + "--保存完成");
                return new AjaxVoResult(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage(),filePath);
            } catch (FileNotFoundException e) {
                log.error("文件没有找到");
                return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "Excel导入出错");
            } catch (IOException e) {
                log.error("MultipartFile文件转化类型出错");
                return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "Excel导入出错");
            }finally {
                dir = null;
                uploadExcel = null;
            }
        }else {
            return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(),StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(),"上传文件为空");
        }
    }

    /**
     * 下载文件
     *
     * @param file     要下载的文件
     * @param filename 要下载的文件名
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    public static void download(File file, String filename, HttpServletRequest request, HttpServletResponse response) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType(request.getSession().getServletContext().getMimeType(filename));
            bos = new BufferedOutputStream(response.getOutputStream());
            if (file.exists()) {
                long fileLength = file.length();
                // fileName = java.net.URLEncoder.encode(fileName, "utf-8");
                String agent = request.getHeader("user-agent");
                if (agent.contains("MSIE")) {
                    // IE浏览器
                    filename = URLEncoder.encode(filename, "utf-8");
                    filename = filename.replace("+", " ");
                } else {
                    String type = response.getHeader("use-down-type");
                    if (type != null && agent.contains("Firefox")) {
                        // 火狐浏览器
                        filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
                    } else {
                        // 其它浏览器
                        filename = URLEncoder.encode(filename, "utf-8");
                    }
                }

                response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-Disposition", "attachment; filename=" + filename);
                response.setHeader("Content-Length", String.valueOf(fileLength));
                bis = new BufferedInputStream(new FileInputStream(file));
                byte[] buff = new byte[102400];
                int bytesRead;
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + java.net.URLEncoder.encode("文件不存在.txt", "utf-8"));
                bos.write("文件已不存在".getBytes());
            }
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
    }
}
