package com.github.ceakerBot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {

    public boolean supports(Update update);
    
    public void handle(Update update);
    
    
}
