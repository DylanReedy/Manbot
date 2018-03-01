package com.rapidprototyping;

import com.rapidprototyping.Command.CommandListener;

/**
 *
 *  Overview
 *  1 - User types command in discord (e.g. !help)
 *  2 - CommandListener.class receives the command using the relevant listener (e.g. onMessageReceived())
 *  3 - The relevant listener calls handleMessage() and passes the message and info about the sender
 *  4 - handleMessage() calls parseCommand() to ensure command is valid
 *  5 - parseCommand() returns Command.class to handleMessage()
 *  6 - handleMessage() then searches through our functions and calls the relevant one and passes the Command.class
 *
 **/

public class Main {

    public static void main(String[] args) throws Exception{
        // Creating the bot
        Bot bot = new Bot("NDE1MzI4MTExMzc1NDE3MzU2.DW0UOQ.hVKNfXX4qUbTZ4xsoKOy_Bug7Qg");
        // This is where the bot will listen for commands
        bot.jda.addEventListener(new CommandListener());
    }
}
