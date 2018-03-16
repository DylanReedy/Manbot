package com.rapidprototyping.Command;

public interface CommandHandler
{
    void handle(Command cmd);

    String getName();
}
