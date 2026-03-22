import java.lang.reflect.Type;

import com.google.gson.*;

public class PerekAdapter implements JsonDeserializer<String[][]> {
    @Override
    public String[][] deserialize(JsonElement element, Type typeOf, JsonDeserializationContext context) throws JsonParseException {

        if (element.isJsonPrimitive()) {
            String[][] out = {{element.getAsString()}};
            return out;
        } else if (element.isJsonArray()) {
            JsonArray jsonArray = element.getAsJsonArray();
            if (jsonArray.size() > 0 && jsonArray.get(0).isJsonArray()) {
                return context.deserialize(jsonArray, new String[0][0].getClass());
            } else {
                String[][] out = {context.deserialize(jsonArray, new String[0].getClass())};
                return out;
            }
        } else {
            throw new JsonParseException("errorrrrrr");
        }
    }
}