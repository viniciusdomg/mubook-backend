package br.com.mubook.mubook.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        File dir = new File(uploadDir);
        String path = dir.isAbsolute()
                ? "file:" + dir.getAbsolutePath() + "/"
                : "file:" + System.getProperty("user.dir") + "/" + uploadDir + "/";

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(path);
    }
}
