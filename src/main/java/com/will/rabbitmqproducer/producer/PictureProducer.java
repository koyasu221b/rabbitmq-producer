package com.will.rabbitmqproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.will.rabbitmqproducer.entity.Employee;
import com.will.rabbitmqproducer.entity.Picture;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureProducer {


	@Autowired
	private RabbitTemplate rabbitTemplate;


	private ObjectMapper objectMapper = new ObjectMapper();

	public void sendMessage(Picture p) throws JsonProcessingException{
		String json = objectMapper.writeValueAsString(p);
		rabbitTemplate.convertAndSend("x.picture", p.getType(), json);
	}

}
