package ru.vkr.service;

public abstract class AbstractService<T, R> {
    public abstract T process(R request);
}
