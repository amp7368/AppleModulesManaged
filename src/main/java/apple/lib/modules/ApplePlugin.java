package apple.lib.modules;

import apple.utilities.util.FileFormatting;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ApplePlugin extends AppleLifeCycle<AppleModule> implements IAppleModuleParent {

    private final Logger logger = LogManager.getLogger(this.getName());

    public void start() {
        this.onInit_();
        this.onBoot_();
        this.onLoad_();
        this.onEnable_();
    }

    private void onInit_() {
        this.toEachModule((sub) -> {
            sub.init_(this);
        });
        this.registerConfigs();
    }

    public File getDataFolder() {
        return FileFormatting.getDBFolder(ApplePlugin.class);
    }

    @Override
    public Logger logger() {
        return logger;
    }

    @Override
    public String getFullName() {
        return this.getName();
    }

    @Override
    public ApplePlugin getPlugin() {
        return this;
    }
}
