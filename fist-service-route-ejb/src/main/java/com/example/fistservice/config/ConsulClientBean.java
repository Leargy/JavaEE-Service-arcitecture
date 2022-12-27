package com.example.fistservice.config;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import com.orbitz.consul.NotRegisteredException;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.agent.Registration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

@Singleton
@Startup
public class ConsulClientBean {
    private final String serviceName = "first-service-soap";

    private AgentClient agentClient;
    private String serviceId;

    @Produces
    public Consul getClient() {
        return Consul
                .builder()
                .build();
    }

    @PostConstruct
    private void registerService() {
        agentClient = getClient().agentClient();

        serviceId = UUID.randomUUID().toString();
        Registration service = ImmutableRegistration.builder()
                .id(serviceId)
                .check(Registration.RegCheck.ttl(20L))
                .port(8081)
                .address("127.0.0.1")
                .name(serviceName)
                .build();

        agentClient.register(service);

        Timer myTimer = new Timer();

        myTimer.schedule(new TimerTask() {
            public void run() {
                try {
                    agentClient.pass(serviceId);
                } catch (NotRegisteredException e) {
                }
            }
        }, 0, 10000);
    }

    @PreDestroy
    private void deregisterService(){
        agentClient.deregister(serviceId);
    }
}