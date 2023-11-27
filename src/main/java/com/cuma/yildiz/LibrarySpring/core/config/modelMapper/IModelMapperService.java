package com.cuma.yildiz.LibrarySpring.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;

public interface IModelMapperService {
    ModelMapper forRequest();
    ModelMapper forResponse();
}
