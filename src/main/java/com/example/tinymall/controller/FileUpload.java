package com.example.tinymall.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUpload {

    private String filePath = "D:/photos/";

    @RequestMapping(value = "upload2")
    public void imageUpload(MultipartFile file) throws IllegalStateException {
        File filee = new File(filePath);
        if (!filee.exists()) {
            filee.mkdirs();
        }
        //文件名称
        String realFileName = file.getOriginalFilename();
        //文件后缀
        String suffix = realFileName.substring(realFileName.lastIndexOf(".") + 1);
        /***************文件处理*********************/
        String newFileName = UUID.randomUUID() + realFileName;
        String newFilePath = filePath + newFileName;
        //新文件的路径
        try {
            file.transferTo(new File(newFilePath));
            //将传来的文件写入新建的文件
            System.out.println("上传图片成功进行上传文件测试");

        } catch (IllegalStateException e) {
            //处理异常
        } catch (IOException e1) {
            //处理异常
        }

    }
}
