package frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@SpringBootApplication
@EnableDiscoveryClient
@Controller
public class FrontendApplication {

    @Autowired
    BackendClient backendClient;

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String catsOrDogs(Model model) {
      Message response = backendClient.cats();
      model.addAttribute("catsOrDogs", response.getMessage());
      return "cats-or-dogs";
    }
}
