package ua.yyunikov.controller;

public interface ResourcePath {

    interface Events {
        interface Rel {
            String TS = "ts";
        }

        String SELF = "/events";
        String TYPE = "/type";
        String SEARCH = "/search";
        String TS = "/" + Rel.TS;
        String SEARCH_TS = SEARCH + TS;
    }
}
