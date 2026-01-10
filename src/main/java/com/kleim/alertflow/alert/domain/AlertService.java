package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.Alert;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface AlertService {

    Alert createAlertion(CreateAlertRequest request);
    void changeStatusToInProgress(Long alertId) throws AccessDeniedException;
    Alert getAlertionById(Long alertId);
    List<Alert> getAllAlertions();
    void deleteAlertion(Long alertId) throws AccessDeniedException;
    Alert updateAlertion(Long alertId, UpdateAlertRequest request) throws AccessDeniedException;
    List<Alert> searchFilter(SearchAlertRequest searchAlertRequest);
}