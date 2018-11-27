package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.rht0910.blacklistener.BlackListener;
import tk.rht0910.blacklistener.interfaces.Command;

public class Leave implements Command {
    public static Logger logger = LogManager.getLogger(BlackListener.class);

    @Override
    public void run(MessageReceivedEvent event, String command, String[] args) {
        if (event.getAuthor().getId() != event.getGuild().getOwnerId()) { event.getChannel().sendMessage("You don't have permission.");  return; }
        event.getChannel().sendMessage(":wave: Bye bye, " + event.getGuild().getName() + "...").complete();
        event.getGuild().leave().queue();
    }
}
