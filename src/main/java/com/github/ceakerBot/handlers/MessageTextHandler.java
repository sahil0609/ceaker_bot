package com.github.ceakerBot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;


public class MessageTextHandler implements Handler {

    @Override
    public boolean supports(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            return true;
        }
        return false;
        
    }

    //TODO
    @Override
    public void handle(Update update) {
        
    }

}
