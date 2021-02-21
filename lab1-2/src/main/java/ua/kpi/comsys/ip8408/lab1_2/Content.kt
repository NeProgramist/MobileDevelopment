package ua.kpi.comsys.ip8408.lab1_2

import kotlin.math.ceil

// Частина 1
fun main() {
    // Дано рядок у форматі "Student1 - Group1; Student2 - Group2; ..."
    val studentsStr = """
        Дмитренко Олександр - ІП-84; Матвійчук Андрій - ІВ-83; Лесик Сергій - ІО-82;
        Ткаченко Ярослав - ІВ-83; Аверкова Анастасія - ІО-83; Соловйов Даніїл - ІО-83;
        Рахуба Вероніка - ІО-81; Кочерук Давид - ІВ-83; Лихацька Юлія- ІВ-82;
        Головенець Руслан - ІВ-83; Ющенко Андрій - ІО-82; Мінченко Володимир - ІП-83;
        Мартинюк Назар - ІО-82; Базова Лідія - ІВ-81; Снігурець Олег - ІВ-81; 
        Роман Олександр - ІО-82; Дудка Максим - ІО-81; Кулініч Віталій - ІВ-81; 
        Жуков Михайло - ІП-83; Грабко Михайло - ІВ-81; Іванов Володимир - ІО-81; 
        Востриков Нікіта - ІО-82; Бондаренко Максим - ІВ-83; Скрипченко Володимир - ІВ-82; 
        Кобук Назар - ІО-81; Дровнін Павло - ІВ-83; Тарасенко Юлія - ІО-82; Дрозд Світлана - ІВ-81; 
        Фещенко Кирил - ІО-82; Крамар Віктор - ІО-83; Іванов Дмитро - ІВ-82
    """.trimIndent()

    // Завдання 1
    // Заповніть словник, де:
    // - ключ – назва групи
    // - значення – відсортований масив студентів, які відносяться до відповідної групи

    // Ваш код починається тут
    val studentsGroups: Map<String, List<String>> = studentsStr
        .split(";")
        .map {
            val pair = it.split(" ?- ".toRegex())
            pair[1] to pair[0]
        }
        .sortedBy { it.second }
        .groupByTo(sortedMapOf(), { it.first }, { it.second.trim() })
    // Ваш код закінчується тут

    println("Завдання 1")
    studentsGroups.forEach { println(it) }
    println("---------------------------")

    // Дано масив з максимально можливими оцінками
    val points = listOf(12, 12, 12, 12, 12, 12, 12, 16)

    // Завдання 2
    // Заповніть словник, де:
    // - ключ – назва групи
    // - значення – словник, де:
    //   - ключ – студент, який відносяться до відповідної групи
    //   - значення – масив з оцінками студента (заповніть масив випадковими значеннями, використовуючи функцію `randomValue(maxValue: Int) -> Int`)

    fun randomValue(maxValue: Int) = when((0..5).random()) {
        1 -> ceil(maxValue * 0.7)
        2 -> ceil(maxValue * 0.9)
        in 3..5 -> maxValue
        else -> 0
    }.toInt()

    // Ваш код починається тут
    val studentPoints: Map<String, Map<String, List<Int>>> = studentsStr
        .split(";")
        .map {
            val pair = it.split(" ?- ".toRegex())
            pair[1] to (pair[0].trim() to List(points.size) { i -> randomValue(points[i]) })
        }
        .groupByTo(sortedMapOf(), { it.first }, { it.second })
        .mapValuesTo(mutableMapOf()) { it.value.toMap() }
    // Ваш код закінчується тут

    println("Завдання 2")
    // Змінений вивід для кращого відображення
    studentPoints.forEach { p ->
        println(p.key)
        p.value.forEach { println(it) }
    }
    println("---------------------------")

    // Завдання 3
    // Заповніть словник, де:
    // - ключ – назва групи
    // - значення – словник, де:
    //   - ключ – студент, який відносяться до відповідної групи
    //   - значення – сума оцінок студента

    // Ваш код починається тут
    val sumPoints: Map<String, Map<String, Int>> = studentsStr
        .split(";")
        .map {
            val pair = it.split(" ?- ".toRegex())
            pair[1] to (pair[0].trim() to List(points.size) { i -> randomValue(points[i]) }.sum())
        }
        .groupByTo(sortedMapOf(), { it.first }, { it.second })
        .mapValuesTo(mutableMapOf()) { it.value.toMap() }
    // Ваш код закінчується тут

    println("Завдання 3")
    // Змінений вивід для кращого відображення
    sumPoints.forEach { p ->
        println(p.key)
        p.value.forEach { println(it) }
    }
    println("---------------------------")

    // Завдання 4
    // Заповніть словник, де:
    // - ключ – назва групи
    // - значення – середня оцінка всіх студентів групи

    // Ваш код починається тут
    val groupAvg: Map<String, Double> = studentsStr.split(";")
        .map {
            val pair = it.split(" ?- ".toRegex())
            pair[1] to (pair[0].trim() to List(points.size) { i -> randomValue(points[i]) }.sum())
        }
        .groupByTo(sortedMapOf(), { it.first }, { it.second })
        .mapValuesTo(mutableMapOf()) { p -> p.value.sumBy { it.second } / p.value.size.toDouble() }
    // Ваш код закінчується тут

    println("Завдання 4")
    groupAvg.forEach { println(it) }
    println("---------------------------")

    // Завдання 5
    // Заповніть словник, де:
    // - ключ – назва групи
    // - значення – масив студентів, які мають >= 60 балів

    // Ваш код починається тут
    val passedPerGroup: Map<String, List<String>> = sumPoints
        .mapValuesTo(mutableMapOf()) {
            it.value.filterValues { point -> point >= 60 }.keys.toList()
        }
    // Ваш код закінчується тут

    println("Завдання 5")
    passedPerGroup.forEach { println(it) }
    println("---------------------------")
}
