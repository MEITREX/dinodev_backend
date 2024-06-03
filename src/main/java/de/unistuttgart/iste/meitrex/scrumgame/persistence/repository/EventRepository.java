package de.unistuttgart.iste.meitrex.scrumgame.persistence.repository;

import de.unistuttgart.iste.meitrex.common.persistence.MeitrexRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EventRepository extends MeitrexRepository<EventEntity, UUID> {

    /**
     * Retrieves a page of events for a specific user. This query is optimized for the display
     * on the main page of the app.
     * <p>
     * This method queries the event repository to retrieve a page of events that meet the specified criteria. The
     * events are filtered based on the provided project ID and userId. Only top-level
     * events are returned, i.e., events that do not have a parent event. The visibility level must be either
     * public or private. Additionally, the events must meet one of the following
     * conditions:
     * - The visibility level is 'PUBLIC'
     * - The user ID matches the provided user ID
     * - The provided user ID is a member of the visibleToUserIds list of the event
     * <p>
     * This ensures that this method only returns events that are
     * visible to the specified user.
     * <p>
     * The events are ordered by the timestamp in descending order.
     *
     * @param projectId The ID of the project to filter the events by
     * @param userId    The ID of the user to filter the events by
     * @param pageable  The pagination information for retrieving the page of events
     * @return A page of events that meet the specified criteria
     */
    @Query("SELECT e FROM EventEntity e WHERE (e.projectId = :projectId OR e.projectId IS NULL) " +
           "AND e.parent IS NULL " +
           "AND (e.visibility = 'PUBLIC'" +
           "OR (e.visibility = 'PRIVATE' AND (e.userId = :userId OR :userId MEMBER OF e.visibleToUserIds))) " +
           "ORDER BY e.timestamp DESC")
    Page<EventEntity> findAllForUser(
            UUID projectId,
            UUID userId,
            Pageable pageable);

    @Query("SELECT e FROM EventEntity e WHERE e.parent.id = :parentId " +
           "AND (e.visibility = 'PUBLIC'" +
           "OR (e.visibility = 'PRIVATE' AND (e.userId = :userId OR :userId MEMBER OF e.visibleToUserIds))) " +
           "ORDER BY e.timestamp ASC")
    List<EventEntity> findChildren(UUID parentId, UUID userId);

    @Query("SELECT e FROM EventEntity e WHERE (e.projectId = :projectId OR e.projectId IS NULL) " +
           "AND e.parent IS NULL " +
           "AND e.visibility = 'PUBLIC' " +
           "AND (e.userId = :userId OR :userId MEMBER OF e.visibleToUserIds) " +
           "ORDER BY e.timestamp DESC")
    Page<EventEntity> findPublicEventsForUser(
            UUID projectId,
            UUID userId,
            Pageable pageable);

    @Query("SELECT e FROM EventEntity e JOIN e.eventData d WHERE d.key = 'issueId' AND d.value = :issueId " +
           "ORDER BY e.timestamp DESC " +
           "LIMIT 1")
    Optional<EventEntity> findLastSyncForIssue(String issueId);

    @Query("SELECT e FROM EventEntity e JOIN e.eventData d WHERE d.key = 'issueId' AND d.value = :issueId " +
           "AND e.parent IS NULL " +
           "ORDER BY e.timestamp DESC")
    List<EventEntity> findForIssue(String issueId);

    @Override
    default String getEntityName() {
        return "Event";
    }
}
