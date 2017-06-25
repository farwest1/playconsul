package com.moeller;

import com.orbitz.consul.Consul;
import com.orbitz.consul.KeyValueClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Bernd on 21.06.2017.
 *
 * Package com.moeller
 */
@ConfigurationProperties("cs")
@Component
public class ConsulConfig
{

  private String testprop;

  public String getTestprop() {
    return testprop;
  }

  public void setTestprop(String testprop) {
    this.testprop = testprop;
  }
}
