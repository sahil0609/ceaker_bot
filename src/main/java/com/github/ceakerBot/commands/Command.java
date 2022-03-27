package com.github.ceakerBot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;
import com.github.ceakerBot.exceptions.BotException;

public interface Command {
    
    public CommandsEnum getCommandName();
    
    public void execute(CommandDTO command, Update update) throws BotException;
    

}
