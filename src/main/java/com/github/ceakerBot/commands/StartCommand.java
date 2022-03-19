package com.github.ceakerBot.commands;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;
import com.github.ceakerBot.utils.SendUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartCommand implements Command {

    private final SendUtils send;
    
    @Value("${ceakerBot.welcomeMessage}")
    private String welcomeMessage;
    
    @Override
    public CommandsEnum getCommandName() {
        return CommandsEnum.START;
    }

    @Override
    public void execute(CommandDTO command, Update update) {
        
        log.info("executing start command");
        SendMessage message = SendMessage.builder().chatId(update.getMessage().getChatId().toString())
        .text(welcomeMessage).build();
        
        try {
            send.sendMessage(message);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
