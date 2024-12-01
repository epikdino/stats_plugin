package fr.epikdino.producer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.Plugin;

import fr.epikdino.Stat;
import fr.epikdino.config.ConfigManager.Config.ListenerConfig;
import fr.epikdino.consumer.ConsumerPool;

public class OSResourceProducer extends TimedProducer{

    public OSResourceProducer(ConsumerPool consumers, ListenerConfig listener, Plugin plugin) {
        super(consumers, listener, plugin);
    }

    @Override
    protected List<Stat> getStatNow() {
        List<Stat> stats = new ArrayList<>();
        stats.add(getCpuUsage());
        stats.add(getRamUsage());
        stats.add(getGlobalCpuUsage());
        stats.add(getGlobalRamUsage());
        stats.add(getDiskUsage());
        return stats;
    }

    private Stat getCpuUsage() {
        com.sun.management.OperatingSystemMXBean osBean = 
            (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        double cpuUsage = osBean.getProcessCpuLoad() * 100;
        return new Stat("cpuUsage", cpuUsage);
    }

    private Stat getRamUsage() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        double ramUsage = ((double) usedMemory / totalMemory) * 100;
        return new Stat("ramUsage", ramUsage);
    }

    private Stat getGlobalCpuUsage() {
        com.sun.management.OperatingSystemMXBean osBean = 
            (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        double cpuLoad = osBean.getCpuLoad() * 100;
        return new Stat("globalCpuUsage", cpuLoad);
    }

    private Stat getGlobalRamUsage() {
        com.sun.management.OperatingSystemMXBean osBean = 
            (com.sun.management.OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        long totalPhysicalMemorySize = osBean.getTotalMemorySize();
        long freePhysicalMemorySize = osBean.getFreeMemorySize();
        long usedPhysicalMemorySize = totalPhysicalMemorySize - freePhysicalMemorySize;
        double ramUsage = ((double) usedPhysicalMemorySize / totalPhysicalMemorySize) * 100;
        return new Stat("globalRamUsage", ramUsage);
    }

    private Stat getDiskUsage() {
        File root = new File("/");
        long totalSpace = root.getTotalSpace();
        long freeSpace = root.getFreeSpace();
        long usedSpace = totalSpace - freeSpace;
        double diskUsage = ((double) usedSpace / totalSpace) * 100;
        return new Stat("diskUsage", diskUsage);
    }
    
}
