package io.github.aafactory.rest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Proxy {

    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public static String getPersons() {
        return "Hello";
    }
    
    @RequestMapping("/picture/{id}")
    public HttpEntity<byte[]>  getArticleImage(@PathVariable("id") String id) {
        ByteArrayOutputStream output = null;
        InputStream input;
        try {
            input = openUrlStream("https://ahocevar.com/geoserver/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=topp%3Astates&TILED=true&WIDTH=512&HEIGHT=256&CRS=EPSG%3A3857&STYLES=&BBOX=-13884991%2C3833530.4571513445%2C-11380302.457151344%2C5085874.728575672");
            output = new ByteArrayOutputStream();
            IOUtils.copy(input, output);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(output.toByteArray().length);

        return new HttpEntity<byte[]>(output.toByteArray(), headers);
    }
    
    public static void main(String[] args) {
        sendPostRequest("https://ahocevar.com/geoserver/wms?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap&FORMAT=image%2Fpng&TRANSPARENT=true&LAYERS=topp%3Astates&TILED=true&WIDTH=512&HEIGHT=256&CRS=EPSG%3A3857&STYLES=&BBOX=-13884991%2C3833530.4571513445%2C-11380302.457151344%2C5085874.728575672", "");
    }
    
    public static InputStream openUrlStream(String requestUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            System.out.println(connection.getResponseCode());
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            writer.write(payload);
//            writer.close();
            inputStream = connection.getInputStream();
        } catch (Exception e) {
              e.printStackTrace();
        }
        return inputStream;
    }
    
    public static String sendPostRequest(String requestUrl, String payload) {
        StringBuffer jsonString = new StringBuffer();
        
//        TrustManager[] trustAllCerts = new TrustManager[]{
//                new X509TrustManager() {
//                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                        return null;
//                    }
//                    public void checkClientTrusted(
//                            java.security.cert.X509Certificate[] certs, String authType) {
//                    }
//                    public void checkServerTrusted(
//                            java.security.cert.X509Certificate[] certs, String authType) {
//                    }
//                }
//        };
        
//        try {
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//        } catch (Exception e) {
//        }
        
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            System.out.println(connection.getResponseCode());
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setRequestMethod("GET");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//            writer.write(payload);
//            writer.close();
            
            IOUtils.copy(connection.getInputStream(), new FileOutputStream(new File("C:/temp/1.png")));
            connection.disconnect();
        } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
        }
        return jsonString.toString();
    }
}
