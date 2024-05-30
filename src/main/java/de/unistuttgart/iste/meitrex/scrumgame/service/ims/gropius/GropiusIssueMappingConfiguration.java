package de.unistuttgart.iste.meitrex.scrumgame.service.ims.gropius;

import java.util.*;

public interface GropiusIssueMappingConfiguration {

    /**
     * Get the UUID of the Scrum Game project.
     *
     * @return The Scrum Game project ID.
     */
    UUID getScrumGameProjectId();

    /**
     * Get the ID of the project in the IMS.
     *
     * @return The IMS project ID.
     */
    String getImsProjectId();

    /**
     * Get the issue state converter for this configuration, used to convert issue states between the Scrum Game and the
     * IMS.
     *
     * @return The issue state converter.
     */
    IssueStateMapping getIssueStateConverter();

    /**
     * Get the issue priority mapping for this configuration, used to map issue priorities between the Scrum Game and
     * the IMS.
     *
     * @return The issue priority mapping.
     */
    IssuePriorityMapping getIssuePriorityMapping();

    IssueTypeMapping getIssueTypeMapping();

    String getSprintFieldName();

    String getEstimationTemplateFieldName();

    String getIssueTemplateId();

    String getGropiusBaseUrl();
}
