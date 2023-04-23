package com.horizonbuilders.server.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {
    @Bean
    Cloudinary cloudinary() {
        Dotenv dotenv = Dotenv.load();

        final String cloud = dotenv.get("cloudinary.cloud_name");
        final String api_key = dotenv.get("cloudinary.api_key");
        final String api_secret = dotenv.get("cloudinary.api_secret");

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloud,
                "api_key", api_key,
                "api_secret", api_secret
        ));


    }
}
