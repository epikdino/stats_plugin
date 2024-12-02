package fr.epikdino.statsgenerator;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Stat {

    public String name;
    public String value;
    public Instant tsp;

    public Stat(String name, String value, Instant tsp){
        this.name = name;
        this.value = value;
        this.tsp = tsp;
    }

    public Stat(String name, String value){
        this(name, value, Instant.now());
    }

    public Stat(String name, long value){
        this(name, Long.toString(value));
    }

    public Stat(String name, double value){
        this(name, Double.toString(value));
    }

    public String tspString(){
        return this.tspString("yyyyMMddTHHmmssSSS");
    }

    public String tspString(String format){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format)
                                                        .withZone(ZoneId.systemDefault());
        return formatter.format(this.tsp);
    }

    public String getJsonValue() {
        return "{tsp:" + this.tspString() + ",name:" + this.name + ",value:" + this.value + "}";
    }

}
