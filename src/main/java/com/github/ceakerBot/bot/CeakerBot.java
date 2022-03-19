package com.github.ceakerBot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CeakerBot extends TelegramLongPollingBot {


    private final String bot_name;
    private final String bot_token;
    private final UpdateProcessor processor;
    
    
    CeakerBot(@Value("${ceakerBot.name}") String name, 
             @Value("${ceakerBot.token}") String token,
             UpdateProcessor processor){
        
        this.bot_name = name;
        this.bot_token = token;
        this.processor = processor;
        
    }
    
    
    @Override
    public void onUpdateReceived(Update update) {
      log.info("message recieved with updateId " + update.getUpdateId());
      
      processor.process(update);
      
      
      
        
    }

    @Override
    public String getBotUsername() {
        // TODO Auto-generated method stub
        return this.bot_name;
    }

    @Override
    public String getBotToken() {
        // TODO Auto-generated method stub
        return this.bot_token;
    }

    
    
}
