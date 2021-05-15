package ua.kpi.comsys.ip8408.feature_filmlist.data.mappers

import ua.kpi.comsys.ip8408.feature_filmlist.core.domain.model.Film
import ua.kpi.comsys.ip8408.feature_filmlist.data.local.db.FilmDetailedEntity
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

fun FilmEntity.toFilm() = Film(
    title = title,
    year = year,
    imdbId = imdbId,
    type = type,
    poster = poster,
)

fun Pair<FilmEntity, FilmDetailedEntity>.toFilm() = Film(
    title = first.title,
    year = first.year,
    imdbId = first.imdbId,
    type = first.type,
    poster = first.poster,
    rated = second.rated,
    released = second.released,
    duration = second.duration,
    genres = second.genres,
    director = second.director,
    writer = second.writer,
    actors = second.actors,
    plot = second.plot,
    language = second.language,
    country = second.country,
    awards = second.awards,
    imdbRating = second.imdbRating,
    imdbVotes = second.imdbVotes,
    production = second.production,
)
