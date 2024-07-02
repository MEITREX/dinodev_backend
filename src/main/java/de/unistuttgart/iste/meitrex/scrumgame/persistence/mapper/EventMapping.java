package de.unistuttgart.iste.meitrex.scrumgame.persistence.mapper;

import de.unistuttgart.iste.meitrex.generated.dto.DefaultEvent;
import de.unistuttgart.iste.meitrex.generated.dto.DefaultEventType;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.EventEntity;
import de.unistuttgart.iste.meitrex.scrumgame.persistence.entity.events.UserDefinedEventTypeEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.Module;

public class EventMapping implements Module {

    @Override
    public void setupModule(ModelMapper modelMapper) {
        modelMapper.createTypeMap(EventEntity.class, DefaultEvent.class);
        modelMapper.createTypeMap(UserDefinedEventTypeEntity.class, DefaultEventType.class);
    }
}
