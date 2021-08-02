package com.sample.player.data.mapper.base

interface Mapper<in I, out O> {
    fun map(input: I): O
}
