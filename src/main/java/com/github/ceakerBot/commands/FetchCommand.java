package com.github.ceakerBot.commands;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.github.ceakerBot.DTO.CommandDTO;
import com.github.ceakerBot.commands.enums.CommandsEnum;
import com.github.ceakerBot.exceptions.BotException;
import com.github.ceakerBot.exceptions.ValidationException;
import com.github.ceakerBot.services.RedditService;
import com.github.ceakerBot.utils.SendUtils;
import com.google.common.base.Enums;
import com.google.common.base.Optional;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;

@Component
@Slf4j
@RequiredArgsConstructor
public class FetchCommand implements ValidCommand {

   
    private final SendUtils send;
    private final RedditService reddit;
    
    @Override
    public CommandsEnum getCommandName() {
        // TODO Auto-generated method stub
        return CommandsEnum.FETCH;
    }

    @Override
    public void execute(CommandDTO command, Update update) throws BotException {
        // TODO Auto-generated method stub
        Validate(command);
        log.info("fetching from reddit");
        String[] args = command.getArgs();
        
        List<Listing<Submission>> posts = reddit.getPosts(args[0], Integer.valueOf(args[2]), SubredditSort.valueOf(args[1]));
        
        posts.get(0).forEach((post) -> {
            
           sendMessage(post, update);
            
        });
       
    }
    
    
    
    
    public void sendMessage(Submission post, Update update) {
        
        if(post.isSelfPost()) {
            sendTextMessage(post, update);
            
        }
        
        else if(post.getPostHint() != null && post.getPostHint().toUpperCase().equals("IMAGE")) {
            sendImage(post, update);
        }
        
        else {
            
            log.info("message not supported right now. Message Type " + post.getPostHint());
        }
        
        
    }
    
    
    
    private void sendTextMessage(Submission post, Update update) {
        
       
        String title = post.getTitle();
        String body = post.getSelfText();
        
        String message = "<b>" + title + "</b>" + 
                        " --- " +
                        "<pre> \n\n " +
                            body +
                        " </pre>";
        
        
        SendMessage telegramMessage = SendMessage.builder()
                .chatId(update.getMessage().getChatId().toString())
                .text(message)
                .parseMode("HTML")
                .build();
        
        try {
            send.sendMessage(telegramMessage);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
               
    }
    
    
    private void sendImage(Submission post, Update update) {
        
        
        String title = post.getTitle();
        String imageURL = post.getUrl();
        
        try {
            SendPhoto message = new SendPhoto();
            message.setPhoto(new InputFile(imageURL));
            message.setChatId(update.getMessage().getChatId().toString());
            message.setCaption(title);
          
            
           send.sendImageMessage(message);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
       
        
        
        
        
    }
    
    

    @Override
    public void Validate(CommandDTO command) throws ValidationException {
        
        try {
            if(command.getArgs() == null || command.getArgs().length != 3) {
                
                throw new ValidationException("no of args is not correct", getCommandName().getDescription());
                
            }
            
            if( Integer.valueOf(command.getArgs()[2]) > 10) {
                
                throw new ValidationException("the limit of post is greater than 10", "the current maximum limit is set to 10");
                
            }
            
            Optional<SubredditSort> check =  Enums.getIfPresent(SubredditSort.class, command.getArgs()[1]);
            
            if(!check.isPresent()) {

                throw new ValidationException("sorting value is wrong", "sort value can only take these values ");
            }
            
            
            
        }
       
        catch(NumberFormatException e) {
            
            throw new ValidationException("limit was not a number","Limit argumnet should be a number");        
        
        }
       
        
    }

}
