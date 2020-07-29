package com.vending.machine.inventory_service.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum EventType {
    GET_INFO(1, "get information"),
    BUY(2, "buy"),
    REMOVE(3, "remove product"),
    ADD(4, "add product"),
    CLEAR_SHELF(5, "clear shelf"),
    ADD_NEW_PRODUCT_TO_SHELF(6, "add new product to shelf");

    private final long eventTypeId;
    private final String eventTypeName;

    EventType(long eventTypeId, String eventTypeName) {
        this.eventTypeId = eventTypeId;
        this.eventTypeName = eventTypeName;
    }

    public long getEventTypeId(String eventTypeName) {
        Optional<EventType> type = Stream.of(EventType.values())
                .filter(eventType -> eventType.eventTypeName.equals(eventTypeName))
                .findFirst();
        return type.map(eventType -> eventType.eventTypeId).orElse(0L);
    }

    public String getEventTypeName(long productTypeId) {
        Optional<EventType> type = Stream.of(EventType.values())
                .filter(eventType -> eventType.eventTypeId == productTypeId)
                .findFirst();
        return type.map(productType -> productType.eventTypeName).orElse("");
    }
}
