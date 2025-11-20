
package com.kleim.alertflow.alert.domain;

import com.kleim.alertflow.alert.Alert;

import java.nio.file.AccessDeniedException;
import java.util.List;

public class CachedAlertionServiceImpl implements AlertService{


    @Override
    public Alert createAlertion(CreateAlertRequest request) {
        return null;
    }

    @Override
    public void changeStatusToInProgress(Long alertId) throws AccessDeniedException {

    }

    @Override
    public Alert getAlertionById(Long alertId) {
        return null;
    }

    @Override
    public List<Alert> getAllAlertions() {
        return null;
    }

    @Override
    public void deleteAlertion(Long alertId) throws AccessDeniedException {

    }

    @Override
    public Alert updateAlertion(Long alertId, UpdateAlertRequest request) throws AccessDeniedException {
        return null;
    }

    @Override
    public List<Alert> searchFilter(SearchAlertRequest searchAlertRequest) {
        return null;
    }
}