package com.github.ceakerBot.commands;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.exceptions.ValidationException;

public interface ValidCommand extends Command {

    public void Validate(CommandDTO command) throws ValidationException;
    
}
