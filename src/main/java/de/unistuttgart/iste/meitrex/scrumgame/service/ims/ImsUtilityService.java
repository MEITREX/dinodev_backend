package de.unistuttgart.iste.meitrex.scrumgame.service.ims;

import de.unistuttgart.iste.meitrex.generated.dto.ImsSpecificData;
import de.unistuttgart.iste.meitrex.generated.dto.ImsUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImsUtilityService {

    private final ImsUtilityConnector imsUtilityConnector;

    public ImsSpecificData getImsSpecificData(UUID projectId) {
        return new ImsSpecificData(projectId);
    }

    public ImsUser getCurrentUser() {
        return imsUtilityConnector.getCurrentUser();
    }
}
