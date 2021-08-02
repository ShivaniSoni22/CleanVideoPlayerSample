package com.sample.player.data.mapper.base

import com.sample.player.data.model.base.Model
import com.sample.player.domain.model.base.Entity
import javax.inject.Inject

open class ModelListMapper<in I : Model, out O : Entity> @Inject constructor(
    private val mapper: ModelMapper<I, O>
) : ListMapper<I, O> {

    override fun map(input: List<I>): List<O> =
        input.map {
            mapper.map(it)
        }

}
