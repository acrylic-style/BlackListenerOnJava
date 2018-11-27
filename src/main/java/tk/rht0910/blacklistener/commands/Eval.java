package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang3.exception.ExceptionUtils;
import tk.rht0910.blacklistener.interfaces.Command;
import tk.rht0910.blacklistener.util.TextUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Eval implements Command {
    public void run(MessageReceivedEvent event, String command, String[] args) {
        if (!event.getAuthor().getId().equals("254794124744458241")) { event.getChannel().sendMessage("You don't have permission.").queue(); return; }
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            String source = String.join(" ", args);
            engine.put("message", event.getMessage());
            engine.put("event", event);
            engine.put("channel", event.getChannel());
            engine.put("guild", event.getGuild());
            engine.put("member", event.getMember());
            engine.put("self", event.getJDA().getSelfUser());
            Object result = engine.eval(source);
            event.getChannel().sendMessage(":ok_hand:\n" + TextUtils.toCodeBlock(String.valueOf(result))).queue();
        } catch(ScriptException e) {
            String exception = ExceptionUtils.getStackTrace(e);
            event.getChannel().sendMessage("Oh, there was error: " + TextUtils.toCodeBlock(exception.substring(0, 1900) + String.format("\n        ... and %s bytes more", exception.length() - 1900))).queue();
        }
    }
}
