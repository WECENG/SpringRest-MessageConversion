package cn.gdou.test;

import cn.gdou.DAO.entity.Product;
import cn.gdou.web.config.RootConfig;
import cn.gdou.web.config.SpringmvcDispatcherInitializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringmvcDispatcherInitializer.class})
public class MessageConversionClientTest {

    public Product fetchProduct(){
        try{
            HttpClient client= HttpClients.createDefault();

            HttpGet request=new HttpGet("http://localhost:8080/rest/query/0");
            request.setHeader("Content-Type","application/json");

            HttpResponse response=client.execute(request);

            HttpEntity entity=response.getEntity();
            ObjectMapper mapper=new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(entity.getContent(),Product.class);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    public void test(){
        System.out.println(this.fetchProduct());
    }
}
