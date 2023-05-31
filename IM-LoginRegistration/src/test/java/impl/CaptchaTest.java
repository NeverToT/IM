package impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class CaptchaTest {

    private static HttpURLConnection createConnection(String method,String url,String data) throws IOException {
        // 创建 URL 对象
        URL apiUrl = new URL(url);
        // 打开连接
        HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
        // 设置请求方法为 POST
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        // 启用输入和输出流
        connection.setDoInput(true);
        connection.setDoOutput(true);
        // 发送请求数据
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(data.getBytes());
        outputStream.flush();
        outputStream.close();
        return connection;
    }

    public static String captcha(String url,String data,String method) throws IOException {

        HttpURLConnection connection = createConnection(method,url,data);


        // 获取响应码
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        // 读取响应内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // 关闭连接
        connection.disconnect();
        // 打印响应内容
        System.out.println("Response: " + response);

        return response.toString();
    }
}
