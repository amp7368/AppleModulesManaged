package apple.modules.managed;

import apple.utilities.util.FileFormatting;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ManagedModuleParent {
    private final List<ManagedModule> modules = new ArrayList<>();
    private File dataFolder = null;

    public void load() {
        onLoad();
    }

    public void enable() {
        onEnable();
    }

    public void disable() {
        onDisable();
    }

    public void onLoad() {
        onLoadPre();
        loadModules();
        onLoadPost();
    }

    public void onLoadPre() {
    }

    public void onLoadPost() {
    }


    public void onEnable() {
        onEnablePreInit();
        setUp();
        onEnablePre();
        initialize();
        enableModules();
        onEnablePost();
    }


    public void onEnablePreInit() {
    }

    public void onEnablePre() {
    }

    public void initialize() {
    }

    public void onEnablePost() {
    }


    public void onDisable() {
        onDisablePre();
        for (ManagedModule module : modules) {
            if (module.shouldEnable()) {
                module.onDisable();
                module.setEnabled(false);
            }
        }
        onDisablePost();
    }

    public void onDisablePre() {
    }

    public void onDisablePost() {
    }


    //
    // Dealing with modules
    //
    private void loadModules() {
        for (ManagedModule module : getModules()) {
            registerModule(module);
            if (module.shouldEnable()) {
                loadModule(module);
            }
        }
    }

    public abstract Collection<ManagedModule> getModules();

    private void enableModules() {
        for (ManagedModule module : getRegisteredModules()) {
            if (module.shouldEnable()) {
                enableModule(module);
            }
        }
    }

    public List<ManagedModule> getRegisteredModules() {
        return modules;
    }

    private void registerModule(ManagedModule module) {
        modules.add(module);
        module._init(this);
        module.setLogger(getLogger());
    }

    protected abstract Logger getLogger();

    private void loadModule(ManagedModule module) {
        module.init();
    }

    public void enableModule(ManagedModule module) {
        module.doEnable();
        getLogger().log(Level.INFO, "Enabled Module: " + module.getName());
    }

    // 
    // Setting up integration
    //
    private void setUp() {
    }

    public File getDataFolder() {
        if (this.dataFolder == null) this.dataFolder = FileFormatting.getDBFolder(getClass());
        return this.dataFolder;
    }
}
