package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class BackendApplication {

    private Log log = LogFactory.getLog(BackendApplication.class);

    private String catPic = "http://i.imgur.com/1uYroRF.gif";

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @RequestMapping("/cats")
    public Message cats() {
      log.info("Respond with cats");
      return new Message();
    }

    private class Message {
        private String message;

        public Message() {
          this.message = catPic;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
