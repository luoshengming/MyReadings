package com.didispace.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Registration registration; // 服务注册

	@Autowired
	private DiscoveryClient client;

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() throws Exception {
        ServiceInstance instance = serviceInstance();

		// 测试超时触发断路器
//		int sleepTime = new Random().nextInt(3000);
//		logger.info("sleepTime:" + sleepTime);
//		Thread.sleep(sleepTime);

		logger.info("/hello, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello World";
	}

	@RequestMapping(value = "/hello1", method = RequestMethod.GET)
	public String hello(@RequestParam String name) {
        ServiceInstance instance = serviceInstance();
		logger.info("/hello1, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello " + name;
	}

	@RequestMapping(value = "/hello2", method = RequestMethod.GET)
	public User hello(@RequestHeader String name, @RequestHeader Integer age) {
        ServiceInstance instance = serviceInstance();
		logger.info("/hello2, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return new User(name, age);
	}

	@RequestMapping(value = "/hello3", method = RequestMethod.POST)
	public String hello(@RequestBody User user) {
        ServiceInstance instance = serviceInstance();
		logger.info("/hello3, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
		return "Hello "+ user.getName() + ", " + user.getAge();
	}

    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = client.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            for (ServiceInstance itm : list) {
                if (itm.getPort() == 2001)
                    return itm;
            }
        }
        return null;
    }

}