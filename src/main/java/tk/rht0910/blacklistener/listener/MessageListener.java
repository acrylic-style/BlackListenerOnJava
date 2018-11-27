package tk.rht0910.blacklistener.listener;

import org.apache.logging.log4j.Logger;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.apache.logging.log4j.LogManager;
import tk.rht0910.blacklistener.BlackListener;
import tk.rht0910.blacklistener.util.Commands;

public class MessageListener extends ListenerAdapter {
    private static final Logger logger = LogManager.getLogger(BlackListener.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) return;
        logger.info(String.format("[%s]: '%s'\n", event.getAuthor().getName(), event.getMessage().getContentRaw()));
        final String message = event.getMessage().getContentRaw();
        final Commands commands = new Commands();
        String cmd = message.replace(BlackListener.prefix, "").split(" ")[0];
        String[] args = message.replaceAll(BlackListener.prefix+cmd+" ", "").split(" ");
        if (message.equals(event.getJDA().getSelfUser().getAsMention()) && event.getMessage().getAttachments().size() == 0) {
            event.getChannel().sendMessage(String.format("Prefix is: %s", BlackListener.prefix)).queue();
            return;
        }
        if (event.getMessage().getContentRaw().startsWith(BlackListener.prefix)) {
            if (cmd.equalsIgnoreCase("ping")) {
                commands.getPing().run(event, cmd, args);
            } else if (cmd.equalsIgnoreCase("lookup")) {
                commands.getLookup().run(event, cmd, args);
            } else if (cmd.equalsIgnoreCase("leave")) {
                commands.getLeave().run(event, cmd, args);
            } else if (cmd.equalsIgnoreCase("members")) {
                commands.getMembers().run(event, cmd, args);
            } else if (cmd.equalsIgnoreCase("eval")) {
                commands.getEval().run(event, cmd, args);
            } else if (cmd.equalsIgnoreCase("help")) {
                commands.getHelp().run(event, cmd, args);
            }
        }
    }
}
