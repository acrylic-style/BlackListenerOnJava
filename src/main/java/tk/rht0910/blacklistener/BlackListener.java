package tk.rht0910.blacklistener;

import javax.security.auth.login.LoginException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Game.GameType;
import tk.rht0910.blacklistener.listener.MessageListener;
import tk.rht0910.blacklistener.listener.ReadyListener;


public class BlackListener {
	private static final Logger logger = LogManager.getLogger(BlackListener.class);
	public static String prefix = "c:";

	public static void main(String[] args) {
		System.setProperty("log4j.configurationFile", "./log4j2.xml");
		if (args.length < 2) {
            logger.error("Usage: <Prefix> <Bot token>");
            return;
        }
		logger.info("Initializing");
		try {
			final String token = args[1];
			prefix = args[0];
            logger.info("Prefix is: " + prefix);
			JDA jda = new JDABuilder(token)
				.setAutoReconnect(true)
				.build();
			jda.addEventListener(new MessageListener());
			jda.addEventListener(new ReadyListener());
			/*
			JDABuilder shardBuilder = new JDABuilder(token);
			shardBuilder.addEventListener(new MessageListener());
			for (int i = 0; i < 10; i++)
			{
				shardBuilder.useSharding(i, 10)
					.build();
			}
			*/
		} catch(LoginException | IllegalArgumentException e) {
			logger.error("An error occurred while logging in: " + e);
		}
	}
}
