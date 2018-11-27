package tk.rht0910.blacklistener.listener;


import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tk.rht0910.blacklistener.BlackListener;

public class ReadyListener implements EventListener {
    private static final Logger logger = LogManager.getLogger(BlackListener.class);

    @Override
    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            event.getJDA().getPresence()
                .setGame(Game.of(Game.GameType.DEFAULT, BlackListener.prefix + "help | " + event.getJDA().getGuilds().size() + " guilds"));
            logger.info("BlackListener has fully startup.");
        }
    }
}
