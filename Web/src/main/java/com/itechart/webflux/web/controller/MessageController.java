package com.itechart.webflux.web.controller;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.entity.Message;
import com.itechart.webflux.web.core.model.entity.User;
import com.itechart.webflux.web.core.service.MessageService;
import com.itechart.webflux.web.core.service.UserService;
import com.itechart.webflux.web.exceptions.NoAccessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/api/message")
public class MessageController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Flux<Message> getMessages(String person, int size, int page) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User block = userService.findUserByUsername(name).block();
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        return messageService.findMessages(person, block.getId(), PageRequest.of(page - 1, size, sort));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<Message> saveMessage(Message message) throws ValidationNotPassedException {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User sender = userService.findUserByUsername(name).block();
        message.setSender(sender.getId());
        return messageService.save(message);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public Mono<Message> editMessage(Message message) throws ValidationNotPassedException {
        return messageService.update(message);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deleteMessage(String id) {
        messageService.findById(id).subscribe(message -> {
            if (message != null) {
                userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).subscribe(user -> {
                    if (user != null) {
                        if (message.getSender().equals(user.getId())) {
                            try {
                                messageService.delete(message).subscribe(System.out::println);
                            } catch (ValidationNotPassedException e) {
                                log.warn("Error during deleting message: ", e);
                            }
                        } else {
                            throw new NoAccessException("You can't delete this message");
                        }
                    }
                });
            }
        });
    }

}
