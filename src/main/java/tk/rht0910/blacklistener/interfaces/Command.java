package tk.rht0910.blacklistener.interfaces;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Command {
	void run(MessageReceivedEvent event, String command, String[] args);
}