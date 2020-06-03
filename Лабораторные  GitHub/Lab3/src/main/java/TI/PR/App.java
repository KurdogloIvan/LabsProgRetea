package TI.PR;

import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    static final String regex = "<title>[a-zA-Z0-9]</title>";
    final  static String[] strri = new String[70];
    static  StringBuilder builder;
    App() {
        SocketAddress address = new InetSocketAddress("31.14.131.70", 8080);
        Proxy proxy1 = new Proxy(Proxy.Type.HTTP, address);
        OkHttpClient client = new OkHttpClient.Builder().proxy(proxy1).build();
        head(client);
    }
    public static void main(String[] args ) {
        new App();
    }
    public void get(OkHttpClient client){
        Request request = new Request.Builder()
                .url("https://vk.com/id94804879")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
            String str = response.body().string();
            System.out.println(str);

//            Matcher matcher =Pattern.compile(regex).matcher(str);
//            int i = 0;
//            while(matcher.find()){
//                strri[i] = str.substring(matcher.start(),matcher.end());
//                System.out.println(strri);
//                i++;
//           }
            //System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void post(OkHttpClient client){
        RequestBody formBody = new FormBody.Builder()
                .add("search", "Андрей")
                .build();
        Request request = new Request.Builder()
                .url("https://vk.com/id94804879")
                .post(formBody)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!response.isSuccessful()) try {
            throw new IOException("Unexpected code " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void options(OkHttpClient client){
        RequestBody body = new RequestBody() {
            @Nullable
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(@NotNull BufferedSink bufferedSink) throws IOException {

            }
        };
        Request request = new Request.Builder()
                .url("http://unite.md/")
                .method("OPTIONS", body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            Headers responseHeaders = response.headers();
            for (int i = 0; i < responseHeaders.size(); i++) {
                System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void head(OkHttpClient client){
        Request request = new Request.Builder()
                .url("https://api.github.com/repos/square/okhttp/issues")
                .header("User-Agent", "OkHttp Headers.java")
                .addHeader("Accept", "application/json; q=0.5")
                .addHeader("Accept", "application/vnd.github.v3+json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println("Server: " + response.header("Server"));
            System.out.println("Date: " + response.header("Date"));
            System.out.println("Vary: " + response.headers("Vary"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
