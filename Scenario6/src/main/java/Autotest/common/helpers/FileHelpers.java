//package Autotest.common.helpers;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//public class FileHelpers {
//    private static final Logger logger = LoggerFactory.getLogger(FileHelpers.class);
//    private Map<String, String> screenSizeMap = new HashMap<>();
//
//    public FileHelpers(String filePath) {
//        loadScreenSizeOptions(filePath);
//    }
//
//    private void loadScreenSizeOptions(String filePath) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode root = mapper.readTree(new File(filePath));
//            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
//
//            while (fields.hasNext()) {
//                Map.Entry<String, JsonNode> field = fields.next();
//                screenSizeMap.put(field.getKey(), field.getValue().asText());
//            }
//
//            logger.info("Screen size options loaded successfully from JSON.");
//        } catch (IOException e) {
//            logger.error("Failed to load screen size options from JSON: " + e.getMessage());
//        }
//    }
//
//    public String getScreenSizeXPath(String key) {
//        return screenSizeMap.getOrDefault(key, null);
//    }
//}
