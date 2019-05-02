package com.itechart.webflux.core.service;

import com.itechart.webflux.core.model.Entity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public abstract class AbstractService<E extends Entity, R extends ReactiveCrudRepository> {

    protected R r;

    public AbstractService(R r) {
        this.r = r;
    }

    public Mono save(E e) {
        if (validate(e)) {
            return r.save(e);
        }
        return null;
    }

    public Flux<E> findAll() {
        return r.findAll();
    }

    abstract boolean validate(E e);

}
