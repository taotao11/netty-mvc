package com.netty.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netty.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@Configuration
//@ComponentScan(basePackages = {"com.netty"})
@EnableWebMvc
@EnableAsync
public class SpringMvcConfig extends WebMvcConfigurationSupport {
    //@EnableWebMvc 等于开启SpringMVC注解方式
    //@EnableWebMvc 底层会帮助我们注入WebMvcConfigurationSupport这类 覆盖我们自定义配置类
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //增加科托消息转换器
//        KeyTopMsgConverter converter  = new KeyTopMsgConverter(MediaType.APPLICATION_JSON,ktkey,ktiv);
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(new MediaType("text/plain;charset=UTF-8"));
        mediaTypes.add(new MediaType("text/html;charset=UTF-8"));
        mediaTypes.add(new MediaType("application/json;charset=UTF-8"));
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(mediaTypes);

        MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverter.setObjectMapper(ObjectMapper());
        converters.add(1,httpMessageConverter);
        converters.add(0,converter);//将自定义的设置为优先级最高
    }

    @Bean
    public ObjectMapper ObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        // 1.需要前缀
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        // 2.需要后缀
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
//
//    // 1.手动注入拦截器到Spring中
    @Bean
    public TokenInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }
//

//    // 2.添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).addPathPatterns("/suc");// 拦截所有的请求
        super.addInterceptors(registry);
    }

}
