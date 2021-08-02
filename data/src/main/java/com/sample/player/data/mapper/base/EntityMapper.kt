package com.sample.player.data.mapper.base

import com.sample.player.data.model.base.Model
import com.sample.player.domain.model.base.Entity

interface EntityMapper<in I: Entity, out O: Model>: Mapper<I, O>
