package faceplusplus.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by coderJiang on 2016/12/23.
 */
public class Detect {

    public static void main(String[] args) throws IOException {
        Detect detect = new Detect();
        detect.send();
    }

    private final String URL = "https://api-cn.faceplusplus.com/facepp/v3/compare";
    private HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    private CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    private HttpPost httpPost = new HttpPost(this.URL);

    private List<NameValuePair> params = new ArrayList();

    private final String api_key = "Ox8uEKtcmjfPLPzEnxFZ51-DzDCkvGjJ";
    private final String api_secret = "KrPMts9wAwmOYPV7bOB3sWfNQpAy78PW";

    private String image_url1 = "http://tse1.mm.bing.net/th?&id=OIP.Mf62555cb9be25d4b1d9ca06340473d13o1&w=218&h=300&c=0&pid=1.9&rs=0&p=0&r=0";
    //private String image_url2 = "http://tse1.mm.bing.net/th?&id=OIP.Meb7bef59054a04b5b2c43b95e1d782f9o1&w=250&h=300&c=0&pid=1.9&rs=0&p=0&r=0";
    private String image_url2 = "https://www.starjf.com/static/ueditor/php/news/20161215/14817667054095.png";



    public Detect() {
    }

    public void send() {

        this.params.add(new BasicNameValuePair("api_key", this.api_key));
        this.params.add(new BasicNameValuePair("api_secret", this.api_secret));
        this.params.add(new BasicNameValuePair("image_url", "https://www.starjf.com/static/ueditor/php/upload/61771469585412.jpg"));

        this.params.add(new BasicNameValuePair("image_url1", this.image_url1));
        this.params.add(new BasicNameValuePair("image_url2", this.image_url2));

        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(this.params, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse httpResponse = null;
            //post请求
            httpResponse = closeableHttpClient.execute(httpPost);


            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String responseJson = EntityUtils.toString(httpEntity, "UTF-8");
                System.out.println(responseJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            try {
                closeableHttpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
