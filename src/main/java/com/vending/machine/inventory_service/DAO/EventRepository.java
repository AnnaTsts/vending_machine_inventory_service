package com.vending.machine.inventory_service.DAO;

import com.vending.machine.inventory_service.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {

}
