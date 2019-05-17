package com.itechart.webflux.web.util;

import com.itechart.webflux.web.core.model.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

    public static List<String> getIds(List<? extends Entity> entities) {
        List<String> ids = new ArrayList<>(entities.size());
        for (Entity entity : entities) {
            ids.add(entity.getId());
        }
        return ids;
    }

}
