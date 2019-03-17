package com.will.rabbitmqproducer;

import com.will.rabbitmqproducer.entity.Employee;
import com.will.rabbitmqproducer.producer.EmployeeJsonProducer;
import com.will.rabbitmqproducer.producer.HelloRabbitProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private EmployeeJsonProducer employeeJsonProducer;

	@Override
	public void run(String... args) throws Exception {
//		helloRabbitProducer.sendHello("Will" + Math.random());;
		for (int i = 0; i < 5 ; i++) {
			Employee e = new Employee("emp" + i, "Employee " + i , new Date());
			employeeJsonProducer.sendMessage(e);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);

	}

}
