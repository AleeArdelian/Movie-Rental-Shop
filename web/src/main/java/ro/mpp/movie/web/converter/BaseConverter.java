package ro.mpp.movie.web.converter;


import ro.mpp.movie.core.model.BaseEntity;
import ro.mpp.movie.web.dto.BaseDto;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by radu.
 */

public abstract class BaseConverter<Model extends BaseEntity<Integer>, Dto extends BaseDto> implements Converter<Model, Dto> {

    public Set<Integer> convertModelsToIDs(Set<Model> models) {
        return models.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    public Set<Integer> convertDTOsToIDs(Set<Dto> dtos) {
        return dtos.stream()
                .map(BaseDto::getId)
                .collect(Collectors.toSet());
    }

    public Set<Dto> convertModelsToDtos(Collection<Model> models) {
        return models.stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toSet());
    }

}
