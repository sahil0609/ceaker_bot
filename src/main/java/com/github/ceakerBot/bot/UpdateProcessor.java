package com.github.ceakerBot.bot;

import java.util.List;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.github.ceakerBot.exceptions.HandlerNotFoundException;
import com.github.ceakerBot.handlers.Handler;
import com.github.ceakerBot.utils.SendUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class UpdateProcessor {

    
    private final List<Handler> handlers;
    private final SendUtils send;
    
    
    @Async
    public void process(Update update) {
        
        //check for session
        // validation
        
        //actual processing
        
        processUpdate(update);
        
        //post processing.
        
        
    }
    
    
    private void processUpdate(Update update) {
        
        try {
        Handler handler = handlers.stream()
        .filter((h)-> h.supports(update))
        .findFirst()
        .orElseThrow(() -> new HandlerNotFoundException("No hanlder found for update message", 
                "something went wrong with the bot. Please try again after some time"));
        
        handler.handle(update);
        }
        catch(HandlerNotFoundException e) {
            log.error("Error Occured when deciding handler " + e);
            //TODO send the error back to the user
            //send.sendError(e);
            
        }
        
    }
   
    
}
