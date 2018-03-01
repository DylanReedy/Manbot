package com.rapidprototyping.Command;

import com.rapidprototyping.Functions.DM;
import com.rapidprototyping.Functions.PM;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;

public class CommandListener extends ListenerAdapter
{
    private ArrayList<CommandHandler> handlers = new ArrayList<>();

    public CommandListener()
    {
        initDefaults();
    }

    //adds all our functions to the bots handlers array list
    private void initDefaults(){
        handlers.add(new PM());
        handlers.add(new DM());
    }

    //Listens for messages across all Channels within the Programming Group
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        //Checking if it was received in a private channel so that we dont reply twice
        if (event.getPrivateChannel() == null) {
            handleMessage(event.getJDA(), event.getAuthor(), event.getChannel(), event.getMessage().getContentDisplay());
        }
    }

    //Listens for when member Dms the bot
    public void onPrivateMessageReceived(PrivateMessageReceivedEvent event) {
        handleMessage(event.getJDA(), event.getAuthor(), event.getChannel(), event.getMessage().getContentDisplay());
    }

    //Listens for when new members join
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        handleMessage(event.getJDA(), event.getUser(), event.getGuild().getDefaultChannel(), "!welcome");
    }

    // checks validity of command then calls the relevant function
    private void handleMessage(JDA jda, User sender, MessageChannel channel, String cmd){
        if (cmd.startsWith("!")){
            Command c = parseCommand(jda, sender, channel, cmd);
            //ensuring we have the function called
            for (int i = 0; i < handlers.size(); i++){
                if (c.name.equals(handlers.get(i).getName())){
                    handlers.get(i).handle(c);
                    break;
                }
            }
        }
    }

    //For now it's just splitting the command
    private Command parseCommand(JDA jda, User sender, MessageChannel channel, String cmd)
    {
        String [] arr = cmd.split(" ", 2);

        //arr[0] returns the command
        //arr[1] returns all text after the command
        String additionalText;

        //contingency if the command is just the command name and has no text after
        if (arr.length < 2){
            additionalText = "";
        }
        else {
            additionalText = arr[1];
        }
        return new Command(jda, sender, channel, arr[0].toLowerCase(), additionalText);
    }
}