package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tk.rht0910.blacklistener.interfaces.Command;

public class Members implements Command {
    @Override
    public void run(MessageReceivedEvent event, String command, String[] args) {
        event.getChannel().sendMessage("" + event.getGuild().getMembers().size()).queue();
    }
}
