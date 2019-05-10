package com.itechart.webflux.web.core.service;

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.web.core.model.entity.Entity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractService<E extends Entity> {

    private ReactiveMongoRepository r;

    AbstractService(ReactiveMongoRepository r) {
        this.r = r;
    }

    public Mono<E> save(E e) throws ValidationNotPassedException {
        if (validate(e)) {
            return r.save(e);
        }
        return null;
    }

    public Mono<E> update(E e) throws ValidationNotPassedException {
        if (validate(e) && e.getId() != null) {
            return r.save(e);
        }
        return null;
    }

    public Flux<E> findAll() {
        return r.findAll();
    }

    public Mono delete(E e) throws ValidationNotPassedException {
        if (e != null && e.getId() != null) {
            return r.delete(e);
        }
        return Mono.empty();
    }

    public abstract boolean validate(E e) throws ValidationNotPassedException;

    public Mono<E> findById(String id) {
        return r.findById(id);
    }
}
