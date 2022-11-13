package com.petproject.portfolio.utils;

import java.util.Objects;
import java.util.function.Function;

public class Utils {
    public static <T, R> R maybe(T value, Function<T, R> apply) {
        return maybe(value, apply, null);
    }

    public static <T, R> R maybe(T value, Function<T, R> apply, R defaultValue) {
        return Objects.isNull(value) ? defaultValue : apply.apply(value);
    }
}
