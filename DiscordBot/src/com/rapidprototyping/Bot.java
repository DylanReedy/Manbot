package com.rapidprototyping;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class Bot
{
    public final JDA jda;

    public Bot(String token) throws Exception
    {
        jda = new JDABuilder(AccountType.BOT)
                .setToken(token)
                .buildBlocking();
    }
}
