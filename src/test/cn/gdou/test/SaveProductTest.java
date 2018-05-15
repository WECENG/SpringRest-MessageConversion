package cn.gdou.test;

import cn.gdou.DAO.entity.Product;
import cn.gdou.web.config.SpringmvcDispatcherInitializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringmvcDispatcherInitializer.class})
public class SaveProductTest {

    public Product fetchProduct(){
        try{
            HttpClient client= HttpClients.createDefault();

            HttpPost request=new HttpPost("http://localhost:8080/rest/save");
            HttpEntity entity=new StringEntity(
                    "{\"id\":16,\"name\":\"orange\",\"descr\":\"yellow\",\"price\":6.0,\"quantity\":10.0}",
                    ContentType.APPLICATION_JSON);
            request.setEntity(entity);
            HttpResponse response=client.execute(request);

            HttpEntity responseEntity=response.getEntity();
            ObjectMapper mapper=new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            return mapper.readValue(responseEntity.getContent(),Product.class);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Test
    public void test(){
        System.out.println(this.fetchProduct());
    }
}
