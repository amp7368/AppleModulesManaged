package apple.lib.modules.configs.data.config.init;


import apple.lib.modules.configs.data.config.AppleConfig;

public interface IAppleConfigInit {

    default void setManager(AppleConfig<?> manager) {
    }

}
