package com.example.config;

import com.example.service.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpLogging;
import org.springframework.http.converter.json.GsonFactoryBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

@ComponentScan(basePackages="com.example")
@Configuration
public class ApiConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Interceptor apiInterceptor;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean("restApiClient")
    public OkHttpClient restApiClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(apiInterceptor)
                .addInterceptor(httpLoggingInterceptor())
                .build();
    }
    @Bean("objectMapper")
    public ObjectMapper objectMapper(){
        return Jackson2ObjectMapperBuilder.json().build();
    }
    @Bean("apiRetrofit")
    public Retrofit apiRetrofit(@Qualifier("restApiClient") OkHttpClient restApiClient,
                                @Qualifier("objectMapper") ObjectMapper objectMapper){
        return new Retrofit.Builder()
                .baseUrl("https://dapi.kakao.com")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(restApiClient)
                .build();
    }

    @Bean("apiService")
    public ApiService apiService(@Qualifier("apiRetrofit") Retrofit apiRetrofit){
        return apiRetrofit.create(ApiService.class);
    }

    private HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NotNull String s) {
                logger.debug("test"+s);
            }
        });
        return httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }
}
