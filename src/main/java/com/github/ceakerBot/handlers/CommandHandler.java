package com.github.ceakerBot.handlers;

import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.Command;
import com.github.ceakerBot.commands.enums.CommandsEnum;
import com.github.ceakerBot.exceptions.Invalidcommand;
import com.github.ceakerBot.utils.SendUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommandHandler implements Handler {
    
    
    private final SendUtils send;
    
    private final List<Command> commands;

    @Override
    public boolean supports(Update update) {
        if(update.hasMessage() 
            && update.getMessage().hasText() 
            && update.getMessage().getText().startsWith("/")) {
       
            return true;   
        }
        
        return false;
    }

    @Override
    public void handle(Update update) { 
        
        CommandDTO currentCommand;
        try {
            currentCommand = getCommand(update.getMessage().getText());
            
            log.info("processing command "+ currentCommand.getCommand());
            
            commands.stream()
            .filter((c) -> c.getCommandName().equals(currentCommand.getCommand()))
            .findFirst()
            .orElseThrow(() -> new Invalidcommand("no command found of that type", "Invalid Command. Type /help to get the list of the commands"))
            .execute(currentCommand, update);
        } catch (Invalidcommand e) {           
            log.error("Error occured while processing the command");
            send.sendErrorMessage(e, update.getMessage().getChatId().toString());
            e.printStackTrace();
        }
        
    }
    
    
    public CommandDTO getCommand(String message) throws Invalidcommand {
        
        if(message.strip().substring(1).length() == 0) {
            throw new Invalidcommand("command only contains /", "Please select Valid Command. Type /help to get the list of valid commands");
        }
        
        
        String[] formattedMessage = message.strip().substring(1).split(" ");
        
        if(formattedMessage.length > 2) {
            throw new Invalidcommand("invalid Command Found Command has multiple spaces",
                    "Invalid command. Command Conatins multiple spaces. Correct Format is /<Command> arg1:arg2:arg3");
            
        }
        
        try {
            if(formattedMessage.length == 1) {
                return new CommandDTO(CommandsEnum.valueOf(formattedMessage[0].toUpperCase()));
                
            }
            
            return new CommandDTO(CommandsEnum.valueOf(formattedMessage[0].toUpperCase()), formattedMessage[1].split(":"), formattedMessage[1]);
        }
        catch(IllegalArgumentException e) {
            
            throw new Invalidcommand("enum has no specified value", "Invalid Command. Type /help to get the list of the commands");
        }
        
       
        
        
    }

}
