package com.cuma.yildiz.LibrarySpring.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


@Configuration
public class ModelMapperConfig  {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}