package tk.rht0910.blacklistener.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import tk.rht0910.blacklistener.BlackListener;
import tk.rht0910.blacklistener.interfaces.Command;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

public class Lookup implements Command {
    public static Logger logger = LogManager.getLogger(BlackListener.class);

    @Override
    public void run(MessageReceivedEvent event, String command, String[] args) {
        JSONObject data = new JSONObject();
        try {
            URI uri = new URI("http://api.blacklistener.tk/v1/users/" + args[0]);
            data = new JSONObject(new JSONTokener(uri.toURL().openStream()));
        } catch(URISyntaxException | IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
        String bannedFromServerOwner = data.get("bannedFromServerOwner").toString().length() <= 2
                ? "Not banned"
                : data.get("bannedFromServerOwner").toString();

        String bannedFromUser = data.get("bannedFromUser").toString().length() <= 2
                ? "Not banned"
                : data.get("bannedFromUser").toString();

        String probes = data.get("probes").toString().length() <= 2
                ? "Not banned"
                : data.get("probes").toString();

        String reasons = data.get("reasons").toString().length() <= 2
                ? "Not banned"
                : data.get("reasons").toString();

        String username_changes = data.get("username_changes").toString().length() <= 2
                ? "Not banned"
                : data.get("username_changes").toString();

        String nickname = event.getMember().getNickname() != null ? event.getMember().getNickname() : "(none)";
        event.getChannel().sendMessage(new EmbedBuilder()
                .setTitle("Result")
                .setColor(Color.green)
                .addField("Reputation", data.get("rep").toString(), false)
                .addField("Owner of the server that executed BAN", bannedFromServerOwner, false)
                .addField("They banned you", bannedFromUser, false)
                .addField("Probes", probes, false)
                .addField("Reasons", reasons, false)
                .addField("User Tag", event.getJDA().getUserById(args[0]).getName()+"#"+event.getJDA().getUserById(args[0]).getDiscriminator(), false)
                .addField("Nickname", nickname, false)
                .addField("User ID", event.getAuthor().getId(), false)
                .addField("Username changes", username_changes, false)
                .addField("Is bot", event.getAuthor().isBot() ? "Yes" : "No", false)
                .addField("Created at", event.getAuthor().getCreationTime().toString(), false)
                .addField("Joined at", !event.getMember().getJoinDate().toString().isEmpty() ? event.getMember().getJoinDate().toString() : "(Not joined in this guild)", false)
                .addField("Current Time", new Date().toString(), false)
                .setThumbnail(event.getJDA().getUserById(args[0]).getAvatarUrl())
                .build()
        ).queue();
    }
}
