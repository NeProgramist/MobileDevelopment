package ua.kpi.comsys.ip8408.feature_filmlist.data.mappers

import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmBasic
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmEntity
import ua.kpi.comsys.ip8408.feature_filmlist.data.model.FilmDetailedResponse

fun FilmDetailedResponse.toFilm() = Film(
    title = title,
    year = year,
    imdbId = imdbId,
    type = type,
    poster = poster,
    rated = rated,
    released = released,
    duration = duration,
    genres = genres,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    production = production,
)

fun FilmBasic.toFilm() = Film(
    title = title,
    year = year,
    imdbId = imdbId,
    type = type,
    poster = poster,
)

fun FilmEntity.toFilm() = Film(
    title = title,
    year = year,
    imdbId = imdbId,
    type = type,
    poster = poster,
    rated = rated,
    released = released,
    duration = duration,
    genres = genres,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    production = production,
)

fun Film.toFilmEntity() = FilmEntity(
    title = title,
    year = year,
    type = type,
    poster = poster,
    imdbId = imdbId,
    rated = rated,
    released = released,
    duration = duration,
    genres = genres,
    director = director,
    writer = writer,
    actors = actors,
    plot = plot,
    language = language,
    country = country,
    awards = awards,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes,
    production = production,
)
