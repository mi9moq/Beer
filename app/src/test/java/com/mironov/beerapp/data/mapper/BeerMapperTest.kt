package com.mironov.beerapp.data.mapper

import com.mironov.beerapp.util.BeerData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BeerMapperTest {

    private val mapper = BeerMapper()

    private val db = BeerData.beerDbModel
    private val dto = BeerData.beerDto
    private val entity = BeerData.beer

    @Test
    fun `map dto EXPECT dbModel`() {

        val expected = db
        val actual = mapper.mapDtoToDb(dto)

        assertEquals(expected, actual)
    }

    @Test
    fun `map db EXPECT entity`() {

        val expected = entity
        val actual = mapper.mapDbToEntity(db)

        assertEquals(expected, actual)
    }

    @Test
    fun `map dto EXPECT entity`() {

        val expected = entity
        val actual = mapper.mapDtoToEntity(dto)

        assertEquals(expected, actual)
    }
}