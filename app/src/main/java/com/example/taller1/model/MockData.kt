package com.example.taller1.model

fun mockCharacter(): Character = Character(
    id = 123,
    name = "Rick Sanchez",
    status = "Alive",
    species = "Human",
    gender = "Male",
    image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
    origin = OriginRef(name = "Earth (C-137)"),
    location = LocationRef(name = "Citadel of Ricks")
)