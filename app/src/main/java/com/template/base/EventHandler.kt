package com.template.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}