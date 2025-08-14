package config;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

public class ConfigReader {
    @Getter
    private static final DeviceConfig deviceConfig =
            ConfigFactory.create(DeviceConfig.class, System.getProperties());

}
