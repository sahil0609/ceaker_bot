package com.github.ceakerBot.exceptions;

@SuppressWarnings("serial")
public class BotException extends Exception {

    private String messageToUser;
    
    public BotException(String message, String messageToUser) {
        super(message);
        this.messageToUser  = messageToUser;
    }
    
    public String getMessageToUSer() {
        return this.messageToUser;
    }
}
