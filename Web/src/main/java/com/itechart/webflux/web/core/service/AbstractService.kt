package com.itechart.webflux.web.core.service

import com.itechart.webflux.web.core.exceptions.ValidationNotPassedException
import com.itechart.webflux.web.core.model.entity.Entity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

abstract class AbstractService<E : Entity>(private var r: ReactiveMongoRepository<E, String>) {

    internal fun AbstractService(r: ReactiveMongoRepository<E, String>) {
        this.r = r
    }

    @Throws(ValidationNotPassedException::class)
    open fun save(e: E): Mono<E>? {
        return if (validate(e)) {
            r.save(e)
        } else null
    }

    @Throws(ValidationNotPassedException::class)
    open fun update(e: E): Mono<E>? {
        return if (validate(e) && e.id != null) {
            r.save(e)
        } else null
    }

    open fun findAll(): Flux<E> {
        return r.findAll()
    }

    @Throws(ValidationNotPassedException::class)
    open fun delete(e: E?): Mono<E>? {
        return if (e?.id != null) {
            r.delete(e)
            return Mono.just(e)
        } else Mono.empty<E>()
    }

    @Throws(ValidationNotPassedException::class)
    abstract fun validate(e: E): Boolean

    fun findById(id: String): Mono<E> {
        return r.findById(id)
    }

}