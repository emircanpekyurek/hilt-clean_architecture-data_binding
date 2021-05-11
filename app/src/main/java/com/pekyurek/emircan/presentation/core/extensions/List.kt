package com.pekyurek.emircan.presentation.core.extensions


fun <T> List<T>.nextItemOrFirst(item: T) = this[(this.indexOf(item) + 1) % this.size]
