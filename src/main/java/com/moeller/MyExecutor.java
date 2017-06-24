package com.moeller;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Bernd on 20.06.2017.
 *
 * Package com.moeller
 */
public class MyExecutor{

  private static final Logger LOGGER = LoggerFactory.getLogger(MyExecutor.class);

  Consul consul;
  AgentClient agentClient;
  String serviceName = "MyService";
  String serviceId = "1";


  public MyExecutor(){
    consul = Consul.builder().build();
    agentClient = consul.agentClient();


    agentClient.register(8080, 3L, serviceName, serviceId); // registers with a TTL of 3 seconds

    try {
      agentClient.registerCheck("1", "CheckHello", new URL("http://localhost:8080/"), 3L);
    }catch (MalformedURLException e){
      e.printStackTrace();
    }


// Note that you need to continually check in before the TTL expires, otherwise your service's state will be marked as "critical".

  }

  @Scheduled(fixedDelay=2000)
  public void triggerConsul (){
    try {
      agentClient.pass(serviceId); // check in with Consul, serviceId required only.  client will prepend "service:" for service level checks.
    } catch (NotRegisteredException e) {
      e.printStackTrace();
    }
  }

  public void cleanUp(){
    LOGGER.info("Performing graceful shutdown");
    if(agentClient.isRegistered(serviceId)){
      agentClient.deregister(serviceId);
      LOGGER.info("Successfully unregistered from Consul");
    }
  }
}
