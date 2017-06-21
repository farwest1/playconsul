package com.moeller;

import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;

/**
 * Created by Bernd on 21.06.2017.
 *
 * Package com.moeller
 */
public class ConsulConfig
{

  private Consul consul;


  public ConsulConfig(){
    consul = Consul.builder().build();
    KeyValueClient keyValueClient = consul.keyValueClient();
    keyValueClient.putValue("playconsul/port", "8080");
  }
}
