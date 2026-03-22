import java.lang.classfile.constantpool.Utf8Entry;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
public class API {
    public static String requestBody(String URL, String spot, String[][] params) {
        try {
            return makeRequest(URL, spot, params).body();
        } catch (Exception e) {
            return "wfiho";
        }
    }

    public static HttpResponse<String> makeRequest(String URL, String spot, String[][] params) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String URLString = URL + "/" + spot + "?";
        for (String[] param : params) {
            URLString += param[0] + "=" + param[1] + "&";
        }
        HttpRequest.Builder builder = HttpRequest.newBuilder().uri(URI.create(URLString));
        HttpRequest request = builder.GET().build();
        HttpResponse<String> response;
        response = client.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        return response;
    }
}
