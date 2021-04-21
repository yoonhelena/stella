package com.example.service;

import okhttp3.MultipartBody;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.HashMap;

public interface ApiService {

    @FormUrlEncoded
    @POST("/v2/vision/face/detect")
    Call<HashMap<String, Object>> faceDetectUrl(@FieldMap HashMap<String, Object> search);

    @Multipart
    @POST("/v2/vision/face/detect")
    Call<HashMap<String, Object>> faceDetectFile(@Part MultipartBody.Part file);
}
