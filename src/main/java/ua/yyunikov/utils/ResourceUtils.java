package ua.yyunikov.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class ResourceUtils {

    private static final String PARAMS_PREFIX = "{?";
    private static final String PARAMS_SUFFIX = "}";

    private ResourceUtils() {
    }

    /**
     * Creates resource parameter string in format {?param1,param2,...,paramN}.
     *
     * @param params list of params for creation of string
     * @return created resource parameter string
     */
    public static String createParamsString(final String... params) {
        final String paramsStr = Arrays.stream(params)
                .collect(Collectors.joining(","));

        return PARAMS_PREFIX + paramsStr + PARAMS_SUFFIX;
    }
}
