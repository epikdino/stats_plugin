package fr.epikdino.statsgenerator.logger;

import org.bukkit.plugin.Plugin;
import java.util.logging.Logger;

public class PluginLogger {

    private final Logger logger;
    private String name;

    public PluginLogger(Plugin plugin, String name) {
        this.logger = plugin.getLogger();
        this.name = name;
    }

    private String getFullMessage(String message) {
        return "[" + this.name + "] " + message;
    } 

    public void info(String message) {
        logger.info(getFullMessage(message));
    }

    public void warn(String message) {
        logger.warning(getFullMessage(message));
    }

    public void severe(String message) {
        logger.severe(getFullMessage(message));;
    }
}