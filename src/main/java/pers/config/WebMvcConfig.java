package pers.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pers.security.interceptor.DemoInterceptor;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: guohezuzi
 * \* Date: 2018-11-10
 * \* Time: 下午3:05
 * \* Description:spring web mvc 配置
 * \
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //主页重定向到api文档
        registry.addRedirectViewController("/","/swagger-ui.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(new DemoInterceptor()).addPathPatterns("/**");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许 http://localhost:3000 的跨域请求
        registry.addMapping("/**").allowedOrigins("http://localhost:8080")

        .allowedOrigins("http://localhost:8080")  // Allow requests from specific origin (e.g., frontend at localhost:3000)
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS");  // Allowed HTTP methods
    }
}
