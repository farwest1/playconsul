package com.moeller;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Bernd on 24.06.2017.
 *
 * Package com.moeller
 */

@ConfigurationProperties("play")
public class PlayConfiguration {

  private String servicename;

  public String getServicename() {
    return servicename;
  }

  public void setServicename(String servicename) {
    this.servicename = servicename;
  }



}
