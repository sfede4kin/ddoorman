package ru.ddoorman.client.model.enumeration;

public enum EventTypeEnum {

    OPEN ("open"),
    OPENED ("opened"),
    FAILED ("failed");

    private final String name;
    EventTypeEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "EventTypeEnum{" +
                "name='" + name + '\'' +
                '}';
    }
}
