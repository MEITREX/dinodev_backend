package de.unistuttgart.iste.meitrex.scrumgame.service.meeting;

import de.unistuttgart.iste.meitrex.common.service.AbstractCrudService;
import de.unistuttgart.iste.meitrex.generated.dto.RetrospectiveMeeting;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveColumnEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveCommentEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.meeting.retrospective.RetrospectiveMeetingEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.RetrospectiveColumnRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.RetrospectiveCommentRepository;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.repository.RetrospectiveMeetingRepository;
import de.unistuttgart.iste.meitrex.scrumgame.service.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RetrospectiveMeetingActivityService
        extends AbstractCrudService<UUID, RetrospectiveMeetingEntity, RetrospectiveMeeting> {

    private final AuthService                    auth;
    private final RetrospectiveColumnRepository  retrospectiveColumnRepository;
    private final RetrospectiveCommentRepository retrospectiveCommentRepository;
    private final MeetingService                 meetingService;

    public RetrospectiveMeetingActivityService(
            RetrospectiveMeetingRepository repository,
            ModelMapper modelMapper,
            AuthService auth,
            RetrospectiveColumnRepository retrospectiveColumnRepository,
            RetrospectiveCommentRepository retrospectiveCommentRepository,
            MeetingService meetingService
    ) {
        super(repository, modelMapper, RetrospectiveMeetingEntity.class, RetrospectiveMeeting.class);
        this.retrospectiveColumnRepository = retrospectiveColumnRepository;
        this.auth = auth;
        this.retrospectiveCommentRepository = retrospectiveCommentRepository;
        this.meetingService = meetingService;
    }

    public RetrospectiveMeeting addComment(UUID id, UUID columnId, String content) {
        RetrospectiveCommentEntity comment = RetrospectiveCommentEntity.builder()
                .setContent(content)
                .setAuthorId(auth.getCurrentUserId())
                .build();

        RetrospectiveColumnEntity column = retrospectiveColumnRepository.findByIdOrThrow(columnId);
        column.getComments().add(comment);
        retrospectiveColumnRepository.save(column);

        var result = getOrThrow(id);
        meetingService.publishMeetingUpdated(result);
        return result;
    }

    public RetrospectiveMeeting editComment(UUID id, UUID commentId, String content) {
        RetrospectiveCommentEntity comment = retrospectiveCommentRepository.findByIdOrThrow(commentId);
        comment.setContent(content);
        retrospectiveCommentRepository.save(comment);

        var result = getOrThrow(id);
        meetingService.publishMeetingUpdated(result);
        return result;
    }

    public RetrospectiveMeeting thumbsUpComment(UUID id, UUID commentId) {
        RetrospectiveCommentEntity comment = retrospectiveCommentRepository.findByIdOrThrow(commentId);
        comment.getThumbsUpBy().add(auth.getCurrentUserId());
        retrospectiveCommentRepository.save(comment);

        var result = getOrThrow(id);
        meetingService.publishMeetingUpdated(result);
        return result;
    }

    public RetrospectiveMeeting deleteComment(UUID id, UUID commentId) {
        retrospectiveCommentRepository.deleteById(commentId);

        var result = getOrThrow(id);
        meetingService.publishMeetingUpdated(result);
        return result;
    }
}
