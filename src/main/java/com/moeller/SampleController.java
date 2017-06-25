package com.moeller;

/**
 * Created by Bernd on 20.06.2017.
 *
 * Package com.moeller
 */
import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import com.moeller.MyExecutor;

@Controller
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SampleController {


  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Hello World!";
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SampleController.class, args);
  }

  @Bean(destroyMethod = "cleanUp", initMethod = "init")
  public MyExecutor myExecutor() {
    return new MyExecutor();
  }

  @Bean()
  ConsulConfig consulConfig(){return new ConsulConfig();}

}
