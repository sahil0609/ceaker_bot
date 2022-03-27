package com.github.ceakerBot.commands.enums;

public enum CommandsEnum {

    
    
    START("Welcome to ceaker bot. to get the list of the commands type /help"),
    HELP("type /help to get all the list of available commands"),
    FETCH("Usage /FETCH \"subreddit name:sortby:limit\". Will fetch the reddit pot from reddit current max_limit is 10");
    
    private final String description;
    
    private CommandsEnum(String description) {
        this.description = description;
    
    
}
    
    public String getDescription() {
        return this.description;
    }
     
    
    
}
