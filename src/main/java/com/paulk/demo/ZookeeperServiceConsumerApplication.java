package com.paulk.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperServiceConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperServiceConsumerApplication.class, args);
    }
}
