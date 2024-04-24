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
                }
            }
            """);

    public static final String BASE_PROJECT_FRAGMENT = gql("""
                                                                   fragment ProjectFragment on Project {
                                                                       id
                                                                       name
                                                                       description
                                                                       projectSettings {
                                                                           ...ProjectSettingsFragment
                                                                       }
                                                                   }
                                                                   """ + PROJECT_SETTINGS_FRAGMENT);
}
