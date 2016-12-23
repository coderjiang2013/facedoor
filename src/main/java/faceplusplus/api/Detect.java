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

    private final String URL = "https://api-cn.faceplusplus.com/facepp/v3/detect";
    private HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    private CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
    private HttpPost httpPost = new HttpPost(this.URL);

    private List<NameValuePair> params = new ArrayList();

    private final  String api_key = "";
    private final  String api_secret = "";
    private File image_file;

    private int return_landmark = 1;    //是否检测并返回人脸五官和轮廓的83个关键点。1:检测 0:不检测
    private String return_attributes = "gender,age,smiling,glass,headpose,facequality,blur";

    public Detect(){
    }

    public void send() throws IOException {

        initParams();

        UrlEncodedFormEntity entity;
        try {
            entity = new UrlEncodedFormEntity(this.params, "UTF-8");
            httpPost.setEntity(entity);

            HttpResponse httpResponse;
            //post请求
            httpResponse = closeableHttpClient.execute(httpPost);

            //getEntity()
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                //打印响应内容
                System.out.println("response:" + EntityUtils.toString(httpEntity, "UTF-8"));
            }
            //释放资源
            closeableHttpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initParams() {
        this.params.add(new BasicNameValuePair("api_key", this.api_key));
        this.params.add(new BasicNameValuePair("api_secret", this.api_secret));
        this.params.add(new BasicNameValuePair("image_url", "https://www.starjf.com/static/ueditor/php/upload/61771469585412.jpg"));
        this.params.add(new BasicNameValuePair("return_landmark", String.valueOf(this.return_landmark)));
        this.params.add(new BasicNameValuePair("return_attributes", this.return_attributes));
    }

}
