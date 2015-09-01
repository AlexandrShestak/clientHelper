package com.shestakam.db;

import java.util.List;

/**
 * Created by alexandr on 17.7.15.
 */
public interface GenericDao<Entity> {
     Long save(Entity entity);
     Entity get(Long id);
     List<Entity> getAll();
     void delete(Long id);
     void update(Entity entity);
}

