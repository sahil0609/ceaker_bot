package com.github.ceakerBot.commands;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;
import com.github.ceakerBot.utils.SendUtils;
import com.google.common.collect.Streams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class HelpCommand implements Command {
    
    
    private final SendUtils send;

    @Override
    public CommandsEnum getCommandName() {
        // TODO Auto-generated method stub
        return CommandsEnum.HELP;
    }

    //TODO
    @Override
    public void execute(CommandDTO command, Update update) {
        
        log.info("in the help command");
        
        SendMessage message = new SendMessage();
        message.setChatId(update.getMessage().getChatId().toString());
        
        String helpText = Arrays.stream(CommandsEnum.values())
        .map((v) -> "/" + v.toString() + " -- " + v.getDescription())
        .collect(Collectors.joining("\n"));
        
        message.setText(helpText);  
        
        try {
            send.sendMessage(message);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    
    }

}
