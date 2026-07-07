package com.rajtechnologies.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * A plain Java class exposing methods the model can call.
 * The @Tool annotation + description tells the model when and how to use it.
 */
@Component
public class WeatherTools {

    private final Map<String, String> fakeTemperatures = Map.of(
            "Antwerp", "19°C, partly cloudy",
            "Brussels", "18°C, light rain",
            "Ghent", "20°C, sunny"
    );

    @Tool(description = "Get the current temperature and conditions for a given city")
    public String getTemperature(String city) {
        return fakeTemperatures.getOrDefault(city, "No data available for " + city);
    }
}
