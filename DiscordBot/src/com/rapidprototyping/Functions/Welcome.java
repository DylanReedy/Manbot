package com.rapidprototyping.Functions;

import com.rapidprototyping.Command.Command;
import com.rapidprototyping.Command.CommandHandler;

import net.dv8tion.jda.core.entities.User;

import database.TableObjects.RPUser;
import database.ManbotDBHelper;

import java.lang.Integer;

public class Welcome implements CommandHandler{

	@Override
	public void handle(Command cmd) {
		// TODO Auto-generated method stub
		String params[] = cmd.args.split(" ");
		int yearsExperience;
		String favLanguage;
		String groupLevel;
		
		String format = "\"!welcome yearsCodingExperience primaryLanguage\"";
		String welcomeMsg = "Please, reply to this message with some information about yourself in the following format:" + format +
				" so that we can get you started";
		String senderTag = "@" + cmd.sender.getName() + cmd.sender.getDiscriminator();
		
		if(params.length != 3) {
			cmd.channel.sendMessage("Wrong format for command !welcome. Please reply as follows: " + format);
			return;
		}
		
		switch(params[0]) {
			
			case "NULL":
				cmd.channel.sendMessage("Welcome " + senderTag + "to Learn and Code! We're happy to have you. " + welcomeMsg).queue();
			default:
				try{
					yearsExperience = Integer.parseInt(params[1]);
					
					if(yearsExperience < 3) {
						groupLevel = "Beginner";
					}else if(yearsExperience <= 6) {
						groupLevel = "Intermediate";
					}else {
						groupLevel = "Advanced";
					}
				}catch(NumberFormatException e) {
					cmd.channel.sendMessage("Wrong format for command !welcome. Please reply as follows: " + format +
							"with yearsCodingExperience in integer form");
					break;
				}
				
				favLanguage = params[2];
				
				//TODO: push info to DB here
				RPUser newUser = ManbotDBHelper.getUser(cmd.sender.getName());
				if(newUser != null) {
					cmd.channel.sendMessage(senderTag + " you seem to already be in our database, and it doesn't like duplicates. " + 
						"If you don't think this is accurate then please contact one of the members of the Manbot Dev team to resolve this issue");
					break;
				}
				
				//populate new RPUser then add to the DB
				newUser.setHandle();
				newUser.setExperience();
				newUser.setLanguage();
				newUser.setRole();
				ManbotDBHelper.newUser(newUser);
				
				if(yearsExperience >= 8) {
					cmd.channel.sendMessage("Wow " + senderTag + " you seem to have a lot of experience! You have been placed in the Advanced group level" +
							", but if you would be interested in becoming a Mentor for our group please contact @gavin.tynan#0669");
				}else {
					cmd.channel.sendMessage("Thank you " + senderTag + "for the information. You have been placed in the " + groupLevel + "group");
				}
		}
	}

	@Override
	public String getName() {
		return "welcome";
	}
	
}