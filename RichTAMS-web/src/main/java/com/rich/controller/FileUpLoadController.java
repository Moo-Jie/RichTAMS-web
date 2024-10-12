package com.rich.controller;
import com.aliyun.oss.AliOSSUtils;
import com.rich.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping
    public class FileUpLoadController {
    @Autowired
    private AliOSSUtils ossUtil;


    //本地传输文件
    @PostMapping("/local")
    public void fileUpload(MultipartFile file) throws IOException {
        //MultipartFile是SpringMVC提供的文件类型
        //截取原始名的文件后缀
        String originalFilename = file.getOriginalFilename();
        String lastName = originalFilename.substring(originalFilename.lastIndexOf("."));
        //UUID生成文件名
        String fileName = UUID.randomUUID() + lastName;
        //文件上传
        file.transferTo(new File("D:\\AAA_idea_java\\RichTAMS-web\\src\\main\\resources\\img\\"+fileName));
        //日志
        log.info("本地文件上传成功，文件名：{}",fileName);
    }


    //阿里云文件上传
    @PostMapping("/upload")
    public Result aliyunfileUpload(@RequestParam("image") MultipartFile file) throws Exception {
       String url= ossUtil.upLoad(file);
        log.info("阿里云文件上传成功，文件路径：{}",url);
       return Result.success(url);
    }
}
