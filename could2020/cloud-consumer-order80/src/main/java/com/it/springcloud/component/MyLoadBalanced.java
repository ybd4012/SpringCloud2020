package com.it.springcloud.component;

import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLoadBalanced {
    @Autowired
    private DiscoveryClient discoveryClient;
    //定义初次访问为0
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public int getNext(){
        for(;;){
            //current 为期望值 next为实际值
            int current = atomicInteger.get();
            int next = current >= Integer.MAX_VALUE ? 0 : current + 1;
            if(atomicInteger.compareAndSet(current,next)){
                return next;
            }
        }
    }
    //RestTemplate 是从参数获取到所有可用实例 然后根据next%instances=index
    // 从传入的可用示例list中根据index取得可用的示例并调用
    public ServiceInstance getServer(List<ServiceInstance> instances){
         int  size =  instances.size();
         int next = getNext();
        System.out.println(next);
        ServiceInstance serviceInstance = instances.get(next%size);
        return serviceInstance;
    }

}
