import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class PerekAdapter implements JsonDeserializer<String[][]> {
    @Override
    public String[][] deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
        List<List<String>> result = new ArrayList<>();
        
        if (element.isJsonPrimitive()) {
            result.add(Collections.)
        }
    }
}