package com.itechart.webflux.web.core.repository;

import com.itechart.webflux.web.core.model.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;


public interface MessageRepository extends ReactiveMongoRepository<Message, String> {

    Flux<Message> findByReceiverAndSenderOrSenderAndReceiver(String receiver, String sender, String sender1, String receiver2,Pageable pageable);

}
