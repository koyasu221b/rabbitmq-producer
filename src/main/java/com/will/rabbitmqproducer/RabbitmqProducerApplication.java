package com.will.rabbitmqproducer;

import com.will.rabbitmqproducer.entity.Employee;
import com.will.rabbitmqproducer.entity.Picture;
import com.will.rabbitmqproducer.producer.EmployeeJsonProducer;
import com.will.rabbitmqproducer.producer.HelloRabbitProducer;
import com.will.rabbitmqproducer.producer.HumanResourceProducer;
import com.will.rabbitmqproducer.producer.PictureProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RabbitmqProducerApplication implements CommandLineRunner {

	@Autowired
	private PictureProducer pictureProducer;

	private final List<String> TYPES = new ArrayList<>(Arrays.asList("jpg", "png", "svg"));
	private final List<String> SOURCES = new ArrayList<>(Arrays.asList("mobile", "web"));

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10 ; i++) {
			Picture p = new Picture();
			p.setName("Picture" + i);
			p.setSize(ThreadLocalRandom.current().nextLong(1, 10000));
			p.setSource(SOURCES.get(i % SOURCES.size()));
			p.setType(TYPES.get(i % TYPES.size()));

			pictureProducer.sendMessage(p);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqProducerApplication.class, args);

	}

}
