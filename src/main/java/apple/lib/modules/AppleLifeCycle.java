package apple.lib.modules;

import apple.lib.modules.AppleBootState.AppleBootStatus;
import apple.lib.modules.AppleBootState.AppleBootStep;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.apache.logging.log4j.Logger;

public abstract class AppleLifeCycle<M extends AppleLifeCycle<?>> {

    private List<M> subModules = null;
    private final AppleBootState state = new AppleBootState();

    public abstract String getName();

    public abstract Logger logger();

    public abstract List<M> createModules();

    public List<M> getModules() {
        return this.subModules;
    }

    public AppleBootState getState() {
        return this.state;
    }

    public boolean isAsync() {
        return false;
    }

    public void toEachModule(Consumer<M> run) {
        if (this.subModules == null) this.subModules = createModules();
        List<M> modules = getModules();
        if (modules.isEmpty()) return;
        Stream<M> stream = this.isAsync() ? modules.parallelStream() : modules.stream();
        stream.forEach(run);
    }


    public void onBoot_() {
        logger().trace("Booting Module: " + getName());
        this.getState().step(AppleBootStep.BOOT);
        this.onBoot();
        this.getState().status(AppleBootStatus.PROCESSING);
        this.toEachModule(AppleLifeCycle::onBoot_);
        this.onBootPost();
        this.getState().status(AppleBootStatus.DONE);
        logger().trace("Booted Module: " + getName());
    }

    public void onBootPost() {
    }

    public void onBoot() {
    }

    public void onLoad_() {
        logger().info("Loading Module: " + getName());
        this.getState().step(AppleBootStep.LOAD);
        this.onLoad();
        this.getState().status(AppleBootStatus.PROCESSING);
        this.toEachModule(AppleLifeCycle::onLoad_);
        this.onLoadPost();
        this.getState().status(AppleBootStatus.DONE);
        logger().info("Loaded Module: " + getName());
    }

    public void onLoadPost() {
    }


    public void onLoad() {
    }

    public void onEnable_() {
        logger().info("Enabling Module: " + getName());
        this.getState().step(AppleBootStep.BOOT);
        this.onEnable();
        this.getState().status(AppleBootStatus.PROCESSING);
        this.toEachModule(AppleLifeCycle::onEnable_);
        this.onEnablePost();
        this.getState().status(AppleBootStatus.DONE);
        logger().info("Enabled Module: " + getName());
    }

    public void onEnablePost() {
    }

    public void onEnable() {
    }

    public void onDisable_() {
        logger().info("Disabling Module: " + getName());
        this.getState().step(AppleBootStep.BOOT);
        this.onDisable();
        this.getState().status(AppleBootStatus.PROCESSING);
        this.toEachModule(AppleLifeCycle::onDisable_);
        this.onDisablePost();
        this.getState().status(AppleBootStatus.DONE);
        logger().info("Disabled Module: " + getName());
    }

    public void onDisablePost() {
    }

    public void onDisable() {
    }
}
