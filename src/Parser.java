import com.google.gson.*;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Parser {
    static Gson thing;
    public Parser() {}
    static {
        thing = new Gson();
    }

    public static <type> type parsePath(String path, Class<type> type) {
        String json = new String();

        try {
            json = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            System.out.println("you had an exception");
        }

        return parse(json,type);
    }

    public static <type> type parse(String text, Class<type> type) {
        return thing.fromJson(text,type);
    }
}