package utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object)  {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T fromJson(String response, Class<T> valueType)  {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, valueType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

}
