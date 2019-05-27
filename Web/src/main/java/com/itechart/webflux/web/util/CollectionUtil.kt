package com.itechart.webflux.web.util

import com.itechart.webflux.web.core.model.entity.Entity
import java.util.ArrayList

class CollectionUtil {

    fun getIds(entities: Iterable<Entity>): List<String> {
        val ids = ArrayList<String>()
        for (entity in entities) {
            entity.id?.let { ids.add(it) }
        }
        return ids
    }

}