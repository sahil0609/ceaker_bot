package com.github.ceakerBot.commands;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;

public class HelpCommand implements Command {

    @Override
    public CommandsEnum getCommandName() {
        // TODO Auto-generated method stub
        return CommandsEnum.HELP;
    }

    //TODO
    @Override
    public void execute(CommandDTO command, Update update) {
        // TODO Auto-generated method stub
        
    }

}
