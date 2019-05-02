package com.itechart.webflux.core.service;

import com.itechart.webflux.core.exceptions.ValidationNotPassedException;
import com.itechart.webflux.core.model.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractService<E extends Entity, R extends ReactiveCrudRepository> {

    private R r;

    AbstractService(R r) {
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

    public abstract boolean validate(E e) throws ValidationNotPassedException;

    public Mono<E> findById(Long id) {
        return r.findById(id);
    }
}
