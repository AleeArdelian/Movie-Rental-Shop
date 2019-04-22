package com.shop.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com/shop/core/repository", "com/shop/core/service", "ui"})
public class RentalConfig {
}
