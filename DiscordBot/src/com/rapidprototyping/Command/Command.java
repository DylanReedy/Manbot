package com.rapidprototyping.Command;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public class Command
{
    public final JDA jda;
    public final User sender;
    public final MessageChannel channel;
    public final String name;
    public final String args;

    public Command(JDA jda, User sender, MessageChannel channel, String name, String args)
    {
        this.jda = jda;
        this.sender = sender;
        this.channel = channel;
        //name of function
        this.name = name;
        //all text after function name
        this.args = args;
    }
}
