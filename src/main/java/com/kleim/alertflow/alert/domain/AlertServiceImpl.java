package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.Alert;
import com.kleim.alertflow.alert.AlertStatus;
import com.kleim.alertflow.alert.db.AlertRepository;
import com.kleim.alertflow.alert.domain.mapper.AlertMapper;
import com.kleim.alertflow.alert.domain.mapper.CreateAlertMapper;
import com.kleim.alertflow.alert.domain.mapper.UpdateAlertMapper;
import com.kleim.alertflow.location.domain.LocationService;
import com.kleim.alertflow.security.auth.domain.AuthenticationService;
import com.kleim.alertflow.security.auth.domain.UserRole;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
@Service
public class AlertServiceImpl implements AlertService {
    private final Logger log = LoggerFactory.getLogger(AlertServiceImpl.class);
    private final AuthenticationService authenticationService;
    private final CreateAlertMapper createAlertMapper;
    private final AlertMapper alertConverter;
    private final LocationService locationService;
    private final AlertRepository alertRepository;
    private final UpdateAlertMapper updateAlertMapper;

    public AlertServiceImpl(AuthenticationService authenticationService, CreateAlertMapper createAlertMapper, AlertMapper alertConverter, LocationService locationService, AlertRepository alertRepository, UpdateAlertMapper updateAlertMapper) {
        this.authenticationService = authenticationService;
        this.createAlertMapper = createAlertMapper;
        this.alertConverter = alertConverter;
        this.locationService = locationService;
        this.alertRepository = alertRepository;
        this.updateAlertMapper = updateAlertMapper;
    }

    @Override
    public Alert createAlertion(CreateAlertRequest request) {

        log.info("Successfully saved the new location with the category:%s".formatted(request.category()));
        var analystId = authenticationService.getAuthenticatedUser().id();

        if (!locationService.isLocationExists(request.locationId())) {
            throw new IllegalArgumentException("Does not exist location with id: %s".formatted(request.locationId()));
        }

        var alert = createAlertMapper.toCreate(request.locationId(), analystId, request);
        var alertEntity = alertRepository.save(alert);

        return alertConverter.toDomain(alertEntity);
    }

    @Override
    public Alert getAlertionById(Long alertId) {
        return alertConverter.toDomain(alertRepository.findById(alertId).
                orElseThrow(() -> new IllegalArgumentException("Not found")));
    }

    /**
     * Basis from {@link AlertRepository} decision N+1
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alert> getAllAlertions() {
        return alertRepository.findAlertsWithComments()
                .stream()
                .map(alertConverter::toDomain)
                .toList();
    }


    /**
     * Soft delete - изменяет статус на CANCELLED.
     * Подробнее в документации: /static/openapi-doc.json
     */
    @Override
    @Transactional
    public void deleteAlertion(Long alertId) throws AccessDeniedException {
        var alertion = getAlertionById(alertId);
        checkAccessToModifyAlerts(alertId);
        if (alertion.getStatus().equals(AlertStatus.CANCELLED)) {
            return;
        }
        alertRepository.modifyAlertionStatus(alertId, AlertStatus.CANCELLED);
    }

    @Override
    public Alert updateAlertion(Long alertId, UpdateAlertRequest request) throws AccessDeniedException {
        var alertion = alertRepository.findById(alertId).orElseThrow(() ->
                new IllegalArgumentException("Alert with id %s does not exist".formatted(alertId)));
        checkAccessToModifyAlerts(alertId);
        if (!locationService.isLocationExists(request.locationId())) {
            throw new IllegalArgumentException("Does not exist location with id: %s".formatted(request.locationId()));
        }
        updateAlertMapper.toUpdate(request, alertion);
        var updatedAlertion = alertRepository.save(alertion);
        return alertConverter.toDomain(updatedAlertion);
    }

    @Override
    public List<Alert> searchFilter(SearchAlertRequest searchAlertRequest) {
        return null;
    }

    @Override
    @Transactional
    public void changeStatusToInProgress(Long alertId) throws AccessDeniedException {
        var alert = getAlertionById(alertId);
        checkAccessToModifyAlerts(alertId);
        if (alert.getStatus().equals(AlertStatus.CLOSED) || alert.getStatus().equals(AlertStatus.CANCELLED)) {
            return;
        }
        if (alert.getStatus().equals(AlertStatus.IN_PROGRESS) || alert.getStatus().equals(AlertStatus.RESOLVED)) {
            throw new IllegalArgumentException("Alert already in progress or resolved");
        }

        alertRepository.modifyAlertionStatus(alertId, AlertStatus.IN_PROGRESS);
    }

    public void checkAccessToModifyAlerts(Long alertId) throws AccessDeniedException {
        var alert = getAlertionById(alertId);
        var user = authenticationService.getAuthenticatedUser();
        boolean hasAccess = alert.getAnalystId().equals(user.id()) || user.role().equals(UserRole.LEAD);

        if (!hasAccess) {
            throw new AccessDeniedException("Access denied exception.");
        }
    }



}
