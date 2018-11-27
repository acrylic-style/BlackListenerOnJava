package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import tk.rht0910.blacklistener.interfaces.Command;

public class Help implements Command {
    @Override
    public void run(MessageReceivedEvent event, String command, String[] args) {
        MessageBuilder mb = new MessageBuilder();
        mb.append("```md\n");
        mb.append("# Moderation\n");
        mb.append(" - lookup <UserID>       => Lookup specified user by ID.\n");
        mb.append(" - leave                 => Leave from this guild.\n");
        mb.append("\n");
        mb.append("# Admin\n");
        mb.append(" - eval <Code>           => Execute code.\n");
        mb.append("\n");
        mb.append("# Utilities\n");
        mb.append(" - members               => Show members count in this guild.\n");
        mb.append(" - ping                  => Measure the latency.\n");
        mb.append("\n");
        mb.append("# Other\n");
        mb.append(" - help                  => This help.\n");
        mb.append("\n");
        mb.append("```");
        event.getChannel().sendMessage(mb.build()).queue();
    }
}
