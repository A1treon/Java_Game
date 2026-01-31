package org.project.settings;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SettingsManager {
    private static final String FILE_PATH = "settings.json";
    private static final ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static Settings load() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new Settings();
        }
        try {
            return mapper.readValue(file, Settings.class);
        } catch (IOException e) {
            System.err.println("Could not load settings, using defaults.");
            return new Settings();
        }
    }

    public static void save(Settings settings) {
        try {
            mapper.writeValue(new File(FILE_PATH), settings);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}