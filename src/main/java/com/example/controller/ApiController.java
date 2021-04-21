package com.example.controller;

import com.example.service.ApiService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;

@ComponentScan(basePackages="com.example")
@RestController
public class ApiController {

    private final Logger L = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/face/detect/url")
    public ResponseEntity faceDetect() throws IOException {
        HashMap<String, Object> search = new HashMap<>();
        search.put("threshold", 0.7);
        search.put("image_url", "https://t1.daumcdn.net/alvolo/_vision/openapi/r2/images/01.jpg");
        return new ResponseEntity(apiService.faceDetectUrl(search).execute().body(), HttpStatus.OK);
    }

    @GetMapping(value = "/face/detect/file")
    public ResponseEntity faceDetectFile() throws IOException {
        File imagefile = new File("/Users/yoonjungchung/Documents/george.jpg");
        if(imagefile.canRead()){
            L.debug(imagefile.getName());
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), imagefile);
            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("image", imagefile
                            .getName(), requestFile);
            return new ResponseEntity(apiService.faceDetectFile(body).execute().body(), HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
