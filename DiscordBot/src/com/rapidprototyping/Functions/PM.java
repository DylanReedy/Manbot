package com.rapidprototyping.Functions;

import com.rapidprototyping.Command.Command;
import com.rapidprototyping.Command.CommandHandler;

/**
 *
 * Just an example of how to reply in a public channel
 *
 */

public class PM implements CommandHandler{
    @Override
    public void handle(Command cmd) {
        cmd.channel.sendMessage("Here's a PM").queue();
    }

    @Override
    public String getName() {
        return "!pm";
    }
}
