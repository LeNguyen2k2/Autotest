package Autotest.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);
    private HashMap<String, HashMap<String, String>> jsonData;

    public JsonUtils(String filePath) {
        loadJsonData(filePath);
    }

    private void loadJsonData(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info("Loading JSON data from file '{}'", filePath);
            // Read the JSON file and convert it to a JsonNode
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            jsonData = new HashMap<>();

            // Iterate over the JSON nodes and populate the HashMap
            Iterator<Map.Entry<String, JsonNode>> fields = rootNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String testCaseId = field.getKey();
                JsonNode testCaseNode = field.getValue();

                // Create a HashMap for each test case
                HashMap<String, String> testCaseData = new HashMap<>();
                testCaseNode.fields().forEachRemaining(entry ->
                        testCaseData.put(entry.getKey(), entry.getValue().asText())
                );

                jsonData.put(testCaseId, testCaseData);
                logger.info("Loaded test case '{}' with data: {}", testCaseId, testCaseData);
            }
            logger.info("Successfully loaded JSON data from file '{}'", filePath);
        } catch (IOException e) {
            logger.error("Failed to load JSON data from file '{}'. Root cause: {}", filePath, e.getMessage());
            throw new RuntimeException("Failed to load JSON data from file: " + filePath, e);
        }
    }

    // This method retrieves the entire test case data
    public HashMap<String, String> getTestData(String testCaseId) {
        HashMap<String, String> testCase = jsonData.get(testCaseId);
        if (testCase != null) {
            return testCase; // Return the whole test case data as HashMap
        } else {
            throw new IllegalArgumentException("Test case ID '" + testCaseId + "' does not exist in JSON data");
        }
    }
}
