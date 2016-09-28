package ua.yyunikov.controller;

public interface ResourcePath {

    interface Events {
        String SELF = "/events";
        String COUNT = "count";
        String COUNT_RESOURCE = SELF + "/" + COUNT;
    }
}
