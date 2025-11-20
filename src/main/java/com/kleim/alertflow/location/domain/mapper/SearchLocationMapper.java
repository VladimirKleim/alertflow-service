package com.kleim.alertflow.location.domain.mapper;

import com.kleim.alertflow.location.domain.SearchLocationRequest;
import com.kleim.alertflow.location.domain.SearchLocationRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SearchLocationMapper {

    public SearchLocationRequest toDomain(SearchLocationRequestDto searchLocationRequestDto) {
        return new SearchLocationRequest(
                searchLocationRequestDto.name(),
                searchLocationRequestDto.minWorkers(),
                searchLocationRequestDto.maxWorkers()
        );
    }
}
