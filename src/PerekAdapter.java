import java.lang.reflect.Type;

import com.google.gson.*;
//an adapter to allow Gson to deserialize to a 2d string array from any of a string, 1d string array, or 2d string array
public class PerekAdapter implements JsonDeserializer<String[][]> {
    @Override
    public String[][] deserialize(JsonElement element, Type typeOf, JsonDeserializationContext context) throws JsonParseException {
        //checks if it is _not_ an array
        if (element.isJsonPrimitive()) {
            //if so, treats it as an string, and packages into 2d array as [[string]]
            String[][] out = {{element.getAsString()}};
            return out;
        //then checks if it _is_ an array
        } else if (element.isJsonArray()) {
            //gets it as a JsonArray instead of JsonElemnent
            JsonArray jsonArray = element.getAsJsonArray();
            //checks that it is not empty and that the first elemnent is a JsonArray (if element of JsonArray is JsonArray it is 2d)
            if (jsonArray.size() > 0 && jsonArray.get(0).isJsonArray()) {
                int i = jsonArray.size();
                String[][] out = new String[i][];
                //loops through all elements in the outer array
                for (int a = 0; a < i; a++) {
                    //deserializes each one as a 1d array, then adds it to the output 2d array
                    out[a] = context.deserialize(jsonArray.get(a), new String[0].getClass());
                }
                return out;
            //else (if it is a jsonArray but it does not contain any, then it is a 1d array)
            } else {
                //deserializes it as 1d array, then puts it as only element in outside array to make it 2d
                String[][] out = {context.deserialize(jsonArray, new String[0].getClass())};
                return out;
            }
        } else {
            //if it is not json primitive or json array, throw an error
            throw new JsonParseException("the json didnt work");
        }
    }
}