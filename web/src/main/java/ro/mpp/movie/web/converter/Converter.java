package ro.mpp.movie.web.converter;

import ro.mpp.movie.core.model.BaseEntity;
import ro.mpp.movie.web.dto.BaseDto;

/**
 * Created by radu.
 */

public interface Converter<Model extends BaseEntity<Integer>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);
    Dto convertModelToDto(Model model);

}

