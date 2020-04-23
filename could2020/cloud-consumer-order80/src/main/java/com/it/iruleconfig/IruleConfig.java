package com.it.iruleconfig;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IruleConfig {
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
