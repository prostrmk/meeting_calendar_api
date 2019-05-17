package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.dto.UserDto;
import com.itechart.webflux.web.core.model.entity.Message;
import com.itechart.webflux.web.core.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class MessageService extends AbstractService<Message> {

    private MessageRepository repository;

    @Autowired
    public MessageService(MessageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public Mono<Message> save(Message message) throws ValidationNotPassedException {
        if (message != null) {
            message.setDate(new Date());
            message.setExists(true);
        }
        return super.save(message);
    }

    public Flux<Message> findMessages(String receiver, String sender, Pageable pageable) {
        return repository.findByReceiverAndSenderOrSenderAndReceiver(receiver, sender, sender, receiver, pageable);
    }

    @Override
    public boolean validate(Message message) {
        return message != null && message.getReceiver() != null &&
                message.getSender() != null && message.getText() != null;
    }

    @Override
    public Mono delete(Message message) throws ValidationNotPassedException {
        message.setExists(false);
        return update(message);
    }

//    public Flux<UserDto> findConversations(String userId) {
//        return repository.findConversations(userId);
//    }
}
