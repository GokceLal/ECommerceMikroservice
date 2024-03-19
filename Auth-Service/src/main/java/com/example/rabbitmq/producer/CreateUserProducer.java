package com.example.rabbitmq.producer;

import com.example.rabbitmq.model.CreateUserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserProducer { //Posta yolla
    private final RabbitTemplate rabbitTemplate;  //Zarf
    public void sendMessage(CreateUserModel model){ //mektup= model
        rabbitTemplate.convertAndSend("auth-exchange","auth-binding-key",model);
    }

}
