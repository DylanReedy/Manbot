package com.rapidprototyping.Functions;

import com.rapidprototyping.Command.Command;
import com.rapidprototyping.Command.CommandHandler;
import net.dv8tion.jda.core.entities.PrivateChannel;

/**
 *
 * Just an example of how to reply in a private channel
 *
 */

public class DM implements CommandHandler{
    @Override
    public void handle(Command cmd) {
        PrivateChannel pc = cmd.sender.openPrivateChannel().complete();
        pc.sendMessage("Here's a DM").queue();
    }

    @Override
    public String getName() {
        return "!dm";
    }
}
