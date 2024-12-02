package fr.epikdino.statsgenerator.api;

public class StatsGeneratorAPI {
    private static StatsGenerator plugin;

    private StatsGeneratorAPI() {
    }

    public static StatsGenerator getSGenerator(){
        return plugin;
    }

    public static void setInstance(StatsGenerator plugin){
        StatsGeneratorAPI.plugin = plugin;
    }

    public static int getVersion(){
        return 1;
    }

    public static void addStat(String name, String value){
        plugin.addStat(name, value);
    }
}
