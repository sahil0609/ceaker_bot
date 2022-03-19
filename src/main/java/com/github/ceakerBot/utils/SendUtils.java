package com.github.ceakerBot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.ceakerBot.exceptions.BotException;

import lombok.extern.slf4j.Slf4j;

//making a separate class for circular dependency so that if i can fix it I can fix it in one location
@Component
@Slf4j
public class SendUtils {

    //circular dependency so that I can send request anywhere in the code
    private  TelegramLongPollingBot bot;
    
    @Autowired
    SendUtils(@Lazy TelegramLongPollingBot bot){
        this.bot = bot;
    }
    
    public void sendMessage(SendMessage message) throws TelegramApiException {
 
            bot.execute(message);
       
        
    }
    
    public void sendErrorMessage(BotException exception, String chatId) {
        
       SendMessage message = SendMessage.builder().chatId(chatId).text(exception.getMessageToUSer()).build();
       
       try {
        bot.execute(message);
    } catch (TelegramApiException e) {
        log.error("Error occured while sending the message "+ e);
        e.printStackTrace();
    }
        
    }
    
    
    
}
