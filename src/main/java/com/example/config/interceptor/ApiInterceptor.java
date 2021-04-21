package com.example.config.interceptor;

import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiInterceptor implements Interceptor {

    final String REST_API_KEY = "388d028c7d8bb9317279f3d39332eba0";

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(
                chain.request()
                .newBuilder()
                .addHeader("Authorization", "KakaoAK "+REST_API_KEY)
                .build()
        );
    }
}
