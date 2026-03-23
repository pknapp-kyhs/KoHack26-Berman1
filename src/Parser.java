import com.google.gson.*;
import java.nio.file.Files;
import java.nio.file.Paths;
//a general class to parse jsons
public class Parser {

    //makes a Gson, the object that parses in the Gson library
    static Gson thing;
    static {
        /*adds my custom type addapter to be called when parsing to a 2d array of strings
        this adapter allows a string or 1d array of strings to be parsed as a 2d array
        this allows API calls for psukim, perakim, and sefarim to all be handled by the same workflows */
        GsonBuilder builder = new GsonBuilder().registerTypeAdapter(String[][].class, new PerekAdapter());
        thing = builder.create();
    }

    //parses a json stored at a file location denoted by the "path" variable
    public static <type> type parsePath(String path, Class<type> type) {
        String json = new String();
        //parses the file at the path into a string
        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            System.out.println("you had an exception, couldnt read the json file");
        }
        //passes that string into the general parse function
        return parse(json,type);
    }

    //parses a json to a specific class (lines up the json parameters with the class instance variables)
    //returns whatever type is denoted in the input
    public static <type> type parse(String text, Class<type> type) {
        //calls our custom Gson parser with the json text and class type
        return thing.fromJson(text,type);
    }
}