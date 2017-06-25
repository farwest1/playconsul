package com.moeller;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Bernd on 20.06.2017.
 *
 * Package com.moeller
 */

public class MyExecutor{

  private static final Logger LOGGER = LoggerFactory.getLogger(MyExecutor.class);

  private Consul consul;
  private AgentClient agentClient;


    @Value("${server.port}")
  private String port;

  @Autowired
  private PlayConfiguration playConfiguration;

  @Autowired
  private ConsulConfig consulConfig;

  private String serviceId;


  @Scheduled(fixedDelay=2000)
  public void triggerConsul (){
    try {
      agentClient.pass(serviceId); // check in with Consul, serviceId required only.  client will prepend "service:" for service level checks.
    } catch (NotRegisteredException e) {
      e.printStackTrace();
    }
  }

  private void init(){
    consul = Consul.builder().build();
    agentClient = consul.agentClient();
    serviceId = playConfiguration.getServiceId();


    agentClient.register(Integer.parseInt(port), 3L, playConfiguration.getServiceName(), serviceId); // registers with a TTL of 3 seconds


    try {
      agentClient.registerCheck("1", "CheckHello", new URL("http://localhost:" + port + "/"), 3L);
    }catch (MalformedURLException e){
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
