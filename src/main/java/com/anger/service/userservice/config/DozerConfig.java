package com.anger.service.userservice.config;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {
    @Bean
    public DozerBeanMapper mapper() throws Exception {
        DozerBeanMapper mapper = new DozerBeanMapper();
        //mapper.addMapping(objectMappingBuilder);
        return mapper;
    }

//    BeanMappingBuilder objectMappingBuilder = new BeanMappingBuilder() {
//        @Override
//        protected void configure() {
//            mapping(Bean1.class, Bean2.class)
//                    .fields("id", "id").fields("name", "name");
//        }
//    };
}
