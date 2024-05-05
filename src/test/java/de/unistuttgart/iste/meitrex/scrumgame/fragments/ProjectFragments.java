package de.unistuttgart.iste.meitrex.scrumgame.fragments;

import static de.unistuttgart.iste.meitrex.common.util.GraphQlUtil.gql;

public class ProjectFragments {
    private ProjectFragments() {
    }

    public static final String PROJECT_SETTINGS_FRAGMENT = gql("""
            fragment ProjectSettingsFragment on ProjectSettings {
                codeRepositorySettings {
                    codeRepositoryName
                }
                imsSettings {
                    imsName
                    imsProjectId
                    imsIssueTemplateId
                    effortEstimationFieldName
                    sprintFieldName
                    issuePriorities {
                        imsPriorityId
                        issuePriority
                    }
                    issueStates {
                        name
                        imsStateId
                        type
                    }
                }
                definitionOfDone {
                    text
                    required
                    implies {
                        text
                        required
                        implies {
                            text
                            required
                        }
                    }
                }
            }
            """);

    public static final String BASE_PROJECT_FRAGMENT = gql("""
                                                                   fragment ProjectFragment on Project {
                                                                       id
                                                                       name
                                                                       description
                                                                       currentSprintNumber
                                                                       projectSettings {
                                                                           ...ProjectSettingsFragment
                                                                       }
                                                                   }
                                                                   """ + PROJECT_SETTINGS_FRAGMENT);
}
