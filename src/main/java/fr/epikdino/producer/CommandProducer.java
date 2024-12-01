package fr.epikdino.producer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.plugin.Plugin;

import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class CommandProducer extends EventProducer{

    private boolean playerCommands = false;
    private boolean serverCommands = true;
    private Pattern filter;
    private Pattern capture;
    
    public CommandProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
        if (listener.source == "both" || listener.source == "player") playerCommands = true;
        if (listener.source == "player") serverCommands = false;
        this.capture = Pattern.compile(listener.capture);
        this.filter = Pattern.compile(listener.filter);

    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (!playerCommands) {
            return;
        }
        captureCommand(event.getPlayer(), event.getMessage(), event.isCancelled());
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        if (!serverCommands) {
            return;
        }
        captureCommand(event.getSender(), event.getCommand(), event.isCancelled());
    }

    private void captureCommand(CommandSender sender, String command, boolean isCancelled) {
        Matcher matcher = filter.matcher(command);
        if (!matcher.matches()) return;
        Matcher args = capture.matcher(command);
        String result = isCancelled ? "cancelled" : "executed";

        StringBuilder arguments = new StringBuilder();
        for (int i = 1; i <= args.groupCount(); i++) {
            arguments.append("arg"+i+":").append(args.group(i)).append(";");
        }
        this.value = String.format("sender:%s;result:%s;%s",
                    sender instanceof Player ? ((Player) sender).getName() : "server", 
                    result,
                    arguments.toString().trim());

        sendStat(getStatNow());
    }

}
