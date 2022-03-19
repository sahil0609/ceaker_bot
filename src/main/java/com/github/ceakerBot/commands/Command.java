package com.github.ceakerBot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;

public interface Command {
    
    public CommandsEnum getCommandName();
    
    public void execute(CommandDTO command, Update update);
    

}
