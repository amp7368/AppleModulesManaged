package apple.lib.modules.configs.factory;

import apple.lib.modules.configs.data.config.AppleConfig;
import apple.lib.modules.configs.data.config.AppleConfigProps;

public interface AppleConfigLike {

    AppleConfig<?>[] build(AppleConfigProps addedProps);

    default AppleConfig<?>[] build() {
        return this.build(AppleConfigProps.empty());
    }
}
