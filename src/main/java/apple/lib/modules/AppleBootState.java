package apple.lib.modules;

public class AppleBootState {

    private AppleBootStep step = AppleBootStep.INIT;
    private AppleBootStatus status = AppleBootStatus.STARTING;

    public AppleBootState() {
    }

    public void step(AppleBootStep step) {
        this.step = step;
        this.status = AppleBootStatus.STARTING;
    }

    public void status(AppleBootStatus stage) {
        this.status = stage;
    }

    public AppleBootStep step() {
        return this.step;
    }

    public AppleBootStatus status() {
        return this.status;
    }

    public enum AppleBootStep {
        INIT,
        BOOT,
        LOAD,
        ENABLE,
        DISABLE,
    }

    public enum AppleBootStatus {
        STARTING,
        PROCESSING,
        DONE
    }
}
