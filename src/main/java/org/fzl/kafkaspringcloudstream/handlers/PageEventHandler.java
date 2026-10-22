package org.fzl.kafkaspringcloudstream.handlers;

import org.fzl.kafkaspringcloudstream.events.PageEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class PageEventHandler {

    @Bean
    public Consumer<PageEvent> pageEventComponent(){
        return (input) ->{
            System.out.println("************************");
            System.out.println(input.date());
            System.out.println("************************");

        };
    }
}
