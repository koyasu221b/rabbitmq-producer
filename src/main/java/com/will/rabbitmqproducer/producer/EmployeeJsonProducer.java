package com.will.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.rabbitmqproducer.entity.Employee;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJsonProducer {


	@Autowired
	private RabbitTemplate rabbitTemplate;


	private ObjectMapper objectMapper = new ObjectMapper();

	public void sendMessage(Employee e) throws JsonProcessingException{
		String json = objectMapper.writeValueAsString(e);
		rabbitTemplate.convertAndSend("course.employee", json);
	}

}
