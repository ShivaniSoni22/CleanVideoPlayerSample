package com.sample.player.data.mapper.base

import com.sample.player.data.model.base.Model
import com.sample.player.domain.model.base.Entity

interface ModelMapper<in I: Model, out O: Entity>: Mapper<I, O>
