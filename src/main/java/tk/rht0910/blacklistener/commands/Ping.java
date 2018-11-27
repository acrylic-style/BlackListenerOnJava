package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.rht0910.blacklistener.BlackListener;
import tk.rht0910.blacklistener.interfaces.Command;

import java.util.Date;

public class Ping implements Command {
    public static Logger logger = LogManager.getLogger(BlackListener.class);

    @Override
    public void run(MessageReceivedEvent event, String command, String[] args) {
        Message message = event.getChannel().sendMessage("Pinging...").complete();
        message.editMessage("Pong!\nLatency: "
                + (Date.from(message.getCreationTime().toInstant()).getTime() - Date.from(event.getMessage().getCreationTime().toInstant()).getTime())
                + "ms\nAPI Latency: " + event.getJDA().getPing() + "ms").queue();
    }
}