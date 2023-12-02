package apple.lib.modules;

import apple.lib.modules.configs.factory.AppleConfigModule;
import apple.utilities.util.FileFormatting;
import java.io.File;

public interface IAppleModuleParent extends AppleConfigModule {

    String getName();

    default File getFile(String... children) {
        return FileFormatting.fileWithChildren(getDataFolder(), children);
    }

    String getFullName();

    ApplePlugin getPlugin();
}
