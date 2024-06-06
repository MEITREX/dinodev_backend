package de.unistuttgart.iste.meitrex.scrumgame.util;

import jakarta.annotation.Nullable;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class PersistenceUtils {

    /**
     * Replaces the content of a given list with new content. This method is useful when working with Hibernate entities
     * that have collections. Instead of replacing the collection with a new one (which can cause Hibernate to throw an
     * exception), this method modifies the existing collection to match the new content.
     *
     * <p>Usage:</p>
     * <pre>
     * {@code
     * @OneToMany
     * private List<SomeEntity> oneToManyList = //..
     *
     * public void setOneToManyList(List<SomeEntity> newList) {
     *   oneToManyList = PersistenceUtils.replaceContent(oneToManyList, newList);
     * }
     * }
     * </pre>
     *
     * <p>Note:</p>
     * <p>After calling this method, the original list will have the same elements as the new list, and Hibernate should
     * not throw an exception when saving the entity.</p>
     *
     * @param mutableList The original list whose content is to be replaced. If this is null, a new ArrayList will be
     *                    created.
     * @param content     The new content to be added to the list.
     * @param <T>         The type of the list elements.
     * @return The list with replaced content. If the original list was null, a new list is returned.
     */
    public static <T> List<T> replaceContent(@Nullable List<T> mutableList, List<T> content) {
        var result = mutableList;
        if (result == null) {
            result = new ArrayList<>();
        }
        result.clear();
        result.addAll(content);
        return result;
    }

}
