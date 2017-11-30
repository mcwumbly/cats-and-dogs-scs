package frontend;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.util.UriComponentsBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
@EnableCircuitBreaker
public class BackendClient {

  private Log log = LogFactory.getLog(BackendClient.class);

  private String dogPic = "http://i2.kym-cdn.com/photos/images/original/000/234/765/b7e.jpg";

  @Autowired
  private RestTemplate rest;

  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
      return new RestTemplate();
  }

  @HystrixCommand(fallbackMethod = "dogs")
  Message cats() {
    URI uri = UriComponentsBuilder.fromUriString("//backend/cats")
      .build()
      .toUri();

    Message message;
    try {
      message = rest.getForObject(uri, Message.class);
    } catch (Exception e) {
      log.error(e.getMessage());
      throw e;
    }

    return message;
  }

  Message dogs() {
    return new Message(dogPic);
  }
}
