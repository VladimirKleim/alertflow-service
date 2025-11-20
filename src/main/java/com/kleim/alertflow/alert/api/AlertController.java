package com.kleim.alertflow.alert.api;


import com.kleim.alertflow.alert.AlertDto;
import com.kleim.alertflow.alert.domain.AlertServiceImpl;
import com.kleim.alertflow.alert.domain.SearchAlertRequestDto;
import com.kleim.alertflow.alert.domain.UpdateAlertRequestDto;
import com.kleim.alertflow.alert.domain.mapper.AlertDtoMapper;
import com.kleim.alertflow.alert.domain.mapper.CreateAlertMapper;
import com.kleim.alertflow.alert.domain.CreateAlertRequestDto;
import com.kleim.alertflow.alert.domain.mapper.SearchAlertMapper;
import com.kleim.alertflow.alert.domain.mapper.UpdateAlertMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/alert")
@Tag(name = "Alerts", description = "Alert management API")
public class AlertController {

    private final Logger log = LoggerFactory.getLogger(AlertController.class);
    private final AlertServiceImpl alertService;
    private final CreateAlertMapper createAlertMapper;
    private final AlertDtoMapper alertDtoMapper;
    private final SearchAlertMapper searchAlertMapper;
    private final UpdateAlertMapper updateAlertMapper;

    public AlertController(AlertServiceImpl alertService, CreateAlertMapper createAlertMapper, AlertDtoMapper alertDtoMapper, SearchAlertMapper searchAlertMapper, UpdateAlertMapper updateAlertMapper) {
        this.alertService = alertService;
        this.createAlertMapper = createAlertMapper;
        this.alertDtoMapper = alertDtoMapper;
        this.searchAlertMapper = searchAlertMapper;
        this.updateAlertMapper = updateAlertMapper;
    }


    @PostMapping
    public ResponseEntity<AlertDto> createAlertion(
            @RequestBody @Valid CreateAlertRequestDto createAlertRequestDto
    ) {
        log.info("Got request to create alert");
        var createdAlert = alertService.createAlertion(createAlertMapper.toDomain(createAlertRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alertDtoMapper.toDto(createdAlert));
    }


    @GetMapping("/{alertId}")
    public ResponseEntity<AlertDto> getAlertionById(
            @PathVariable("alertId") Long alertId
    ) {
        log.info("Got request to recieve all alertions");
        var gotAlertion = alertService.getAlertionById(alertId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(alertDtoMapper.toDto(gotAlertion));
    }


    @GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlertions(
    ) {
        log.info("Got request to receive all alertions");
        return ResponseEntity.status(HttpStatus.OK)
                .body(alertService.getAllAlertions()
                        .stream()
                        .map(alertDtoMapper::toDto)
                        .toList());
    }


    @DeleteMapping("/cancel/{alertId}")
    public ResponseEntity<Void> deleteAlertion(
            @PathVariable("alertId") Long alertId
    ) throws AccessDeniedException {
        log.info("Got request to delete alert with id:{}", alertId);
        alertService.deleteAlertion(alertId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping("/search")
    public ResponseEntity<List<AlertDto>> searchAlertionFilter(
            @RequestBody SearchAlertRequestDto searchAlertRequestDto
    ) {
        log.info("Got request to get alertions by filter");
        var foundAlertionByFilter = alertService.searchFilter(searchAlertMapper.toDomain(searchAlertRequestDto));
        return ResponseEntity.status(HttpStatus.OK)
                .body(foundAlertionByFilter.stream()
                        .map(alertDtoMapper::toDto)
                        .toList());
    }


    @PutMapping("/{alertId}")
    public ResponseEntity<AlertDto> updateAlertion(
            @PathVariable("alertId") Long alertId,
            @RequestBody @Valid UpdateAlertRequestDto updateAlertRequestDto
    ) throws AccessDeniedException {
        log.info("Got request to update alertion with id:{}", alertId);
        var updatedAlert = alertService.updateAlertion(alertId, updateAlertMapper.toDomain(updateAlertRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(alertDtoMapper.toDto(updatedAlert));
    }


    @PatchMapping("/status/{alertId}")
    public ResponseEntity<Void> updateAlertionStatusToInProgress(
            @PathVariable("alertId") Long alertId
    ) throws AccessDeniedException {
        log.info("Request to update status to -> IN_PROGRESS");
        alertService.changeStatusToInProgress(alertId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
