package kiwi.board.common.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDateTime;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper(){
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new LocalDateSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateDeserializer());

        registerModule(simpleModule);
    }
}