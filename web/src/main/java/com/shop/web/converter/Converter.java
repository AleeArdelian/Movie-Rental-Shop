package com.shop.web.converter;

import com.shop.core.model.BaseEntity;
import com.shop.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

