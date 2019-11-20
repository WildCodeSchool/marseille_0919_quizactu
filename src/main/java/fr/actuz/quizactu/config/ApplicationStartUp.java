package fr.actuz.quizactu.config;
import java.util.TimeZone;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartUp {

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}