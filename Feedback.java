import net.dv8tion.jda.core;
import net.dv8tion.jda.core.entities;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.requests;
import net.dv8tion.jda.core.requests.restaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/** Feedback Class
 *
 * Feedback.FeedbackHandler() should be called whenever the user types in
 * "!feedback ..."
 *
 * FeedbackHandler receives the command, creates user and message variables,
 * then sends the message to the local parser, which the handler uses to
 * determine if it is a valid command (calls pmMentor()), or if not, it will
 * PM the user that called it and ask for a valid response, in the following
 * form:
 * "!feedback <MentorNumber> <Message>"
 *
 * @author Casey Nitz
 *
 * Notes from 2/18/2018: I'm not sure if this is how you want me to be
 * implementing this command, so please tell me if I'm doing something wrong or
 * not up to convention. I tried to comment thoroughly so that, even if I'm
 * doing something wrong then at least you know my though processes.
 * I also still have to implement the local message parser still, but I figure
 * this isn't even close to the final implementation anyways so I'll put more time
 * into it in the coming days.
 */

public class Feedback {
	/**
	 *  This function should be the first one called for this class. FeedbackHandler
	 *  will set up the local variables, pass the message to the parser and then
	 *  call the proper function depending on what is returned from feedbackParser
	 *
	 * @param c 	CommandContext object containing the User that called !feedback
	 * 				and the associated message
	 */
	public void FeedbackHandler(CommandContext c) {
		//Initialize local vars
		User feedbackCaller = c.getUser();

		//not sure if "message" will be passed to us as a String or as
		//a Message object, so I will roll with it as a string for now and
		//change it later if need be
		String message = c.getMessage();

		//Not sure how to obtain info on our whole group (i.e. mentor and user lists),
		//but I think I need a Guild object?
		List<Guild> guild = JDA.getGuildsByName("Rapid Prototyping",true);

		//Construct list of Mentors
		List<Member> mentors = new ArrayList<String>();
		Role ment = guild.getRoleById("Mentor");
		mentors = guild.getMembersWithRoles(ment);


		//Prepare args for passing
		List<String> args = feedbackParser(message, mentors);

		//If the !feedback command is called without the proper arguments (or none at all), we want to prompt them
		//to do so
		promptUser(guild, feedbackCaller, mentors);

	}

	/**
	 * Called whenever the user calls !feedback with invalid/no arguments,
	 * will prompt the user to reply with a valid command,
	 *
	 * @param guild		Guild object of the current guild the bot is in (RapidPrototyping)
	 * @param user		User object passed from the handler
	 */
	public void promptUser(Guild guild, User user, List<Member> mentors) {


		//Concatenate the message of all mentors to PM to user
		String outMessage = "Who would you like to send feedback to? Here are the Mentors with their associated MentorNumber: \n";
		for(int i=0; i<mentors.size(); i++) {
			outMessage += "" + (i+1) + ") " + mentors.get(i).getNickname() + "\n";
		}
		outMessage += "Please reply again in the following form: !feedback MentorNumber \"Feedback you would like to send\"";

		if(!user.isBot() && user.hasPrivateChannel()) {
			//TODO: Figure out how to retrieve the private channel already open with the user,
			//		then actually send the message
		}else {
			//creates a private channel with user if none exists
			RestAction<PrivateChannel> chan = user.openPrivateChannel();

			//catchMessageAction is just a temporary placeholder until I know what to do with it
			//All i know is that the sendMessage method returns this type and I haven't read
			//into it yet
			MessageAction catchMessageAction = chan.sendMessage(outMessage);
		}
	}

	/**
	 * PMs the mentor the feedback message the user provides
	 *
	 * @param mentorTag	tag of mentor to PM
	 * @param message	message to send to the mentor
	 */
	public void pmMentor(String mentorTag, String message) {
		/* TODO: Just as we sent a message to user above, send a message to the mentor
		 * Probably just a simple copy/paste, but I'll leave this blank until I figure
		 * out if that message actually sends or not
		 */
	}

	/**
	 * The parser will receive the !feedback arguments, get the nickname of the
	 * mentor to receive the feedback and extract the message to be sent, then
	 * return them to the FeedbackHandler as a List<String> of size 2 if
	 * successful
	 *
	 * @param mentors	Member list of all mentors, which the parser will use to
	 * 					identify
	 * @param input 	arguments passed to "!feedback", from CommandContext object
	 *
	 * @return			null when input is empty or invalid, else will return a list
	 * 					of two Strings, one of which will be the Mentor's nickname,
	 * 					and the other will be the message to be sent
	 */

	public List<String> feedbackParser(String input, List<Member> mentors){
		//TODO: Complete method

		return null;
	}
}
