package apple.lib.modules;

import apple.lib.modules.configs.factory.AppleConfigModule;
import java.io.File;
import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AppleModule extends AppleLifeCycle<AppleModule> implements AppleConfigModule, IAppleModuleParent {

    private IAppleModuleParent parent;
    private File dataFolder = null;
    private Logger logger;
    private String fullName;

    public void init_(IAppleModuleParent parent) {
        this.parent = parent;
        this.logger = LogManager.getLogger(getFullName());
        this.toEachModule((sub) -> {
            sub.init_(this);
        });
        this.registerConfigs();
    }

    @Override
    public String getFullName() {
        if (this.fullName != null) return this.fullName;
        return this.fullName = parent.getFullName() + "." + this.getName();
    }

    public Logger logger() {
        return this.logger;
    }

    public File getDataFolder() {
        if (this.dataFolder == null) {
            this.dataFolder = parent.getFile(getName());
            if (!this.dataFolder.exists()) this.dataFolder.mkdirs();
        }
        return this.dataFolder;
    }

    public ApplePlugin getPlugin() {
        return this.parent.getPlugin();
    }

    public AppleModule getModule() {
        return this;
    }

    @Override
    public List<AppleModule> createModules() {
        return Collections.emptyList();
    }
}
