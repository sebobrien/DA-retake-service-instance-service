package com.seb.serviceinstanceservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceInstanceController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/service-instance-service/{applicationName}")
	public List<ServiceInstance> GetServiceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}

	@RequestMapping("/service-instance-service/list")
	public List<List<ServiceInstance>> GetServiceInstancesList() {
		List<List<ServiceInstance>> result = new ArrayList<>();
		for (String serviceId : this.discoveryClient.getServices()) {
			result.add(this.discoveryClient.getInstances(serviceId));
		}
		return result;
	}

}
