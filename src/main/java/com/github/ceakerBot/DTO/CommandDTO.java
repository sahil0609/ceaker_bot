package com.github.ceakerBot.DTO;

import javax.annotation.Nullable;

import com.github.ceakerBot.commands.enums.CommandsEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CommandDTO {

   
    private final CommandsEnum command;
    
    @Nullable
    public String[] args = null;
    @Nullable
    public String rawArgs = null;
    

}
