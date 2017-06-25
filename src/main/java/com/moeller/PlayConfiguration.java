package com.moeller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Bernd on 24.06.2017.
 *
 * Package com.moeller
 */


@ConfigurationProperties("play")
@Component
public class PlayConfiguration {

  private String serviceName;
  private String serviceId;

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }
}
