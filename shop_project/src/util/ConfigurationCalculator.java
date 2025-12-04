package util;

import model.Configuration;

import java.math.BigDecimal;
import java.util.List;

public abstract class ConfigurationCalculator {
    public static BigDecimal getConfigurationPrice(List<Configuration> config) {
        return config.stream()
                .map(configuration -> configuration.getConfigOption().getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static String getConfigurationString(List<Configuration> config) {
        StringBuilder builder = new StringBuilder();
        config.forEach(configuration -> {
            builder.append(configuration.toString());
            builder.append(" zł ");
        });
        return builder.toString();
    }
}
