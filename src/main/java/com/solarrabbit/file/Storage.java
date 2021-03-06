package com.solarrabbit.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class to write to and from disk.
 */
public class Storage {
    private static FileWriter fileWriter;
    private final String inputFilePath;
    private final String outputFilePath;

    /**
     * Constructs a storage class for json file io.
     *
     * @param inputFilePath  file path to read from.
     * @param outputFilePath file path to write to.
     */
    public Storage(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    /**
     * Returns the Json object read from the file.
     */
    public JSONObject load() {
        JSONParser jsonParser = new JSONParser();
        JSONObject parsedInput = null;
        try (FileReader reader = new FileReader(inputFilePath)) {
            parsedInput = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return parsedInput;
    }

    /**
     * Writes the result of the calculation into a Json file.
     */
    public void write(long result) {
        HashMap<String, Long> jsonMap = new HashMap<>();
        jsonMap.put("value", result);
        JSONObject value = new JSONObject(jsonMap);
        String valueAsJsonString = value.toJSONString();
        try {
            fileWriter = new FileWriter(outputFilePath);
            fileWriter.write(valueAsJsonString);
            System.out.println(valueAsJsonString);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
