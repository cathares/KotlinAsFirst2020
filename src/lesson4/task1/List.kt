@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.*

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.lowercase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.sumOf { it * it })

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =
    if (list.isEmpty()) 0.0
    else list.sum() / list.size

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mean = mean(list)
    for (i in 0 until list.size) {
        list[i] -= mean
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var res = 0
    for (i in a.indices) {
        res += a[i] * b[i]
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var res = 0
    if (p.isNotEmpty()) {
        for (i in p.indices) {
            res += p[i] * (x.toDouble().pow(i)).toInt()
        }
    }
    return res
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    if (list.isNotEmpty()) {
        for (i in list.indices) {
            list[i] += sum
            sum += list[i] - sum
        }
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun factorize(n: Int): List<Int> {
    val res = mutableListOf<Int>()
    var x = n
    var c = 2
    while (c * c <= x) {
        if (x % c == 0) {
            res.add(c)
            x /= c
        } else c += 1
    }
    if (x > 1) res.add(x)
    return res
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*")

}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var x = n
    val list = mutableListOf<Int>()
    if (x == 0) list.add(0)
    while (x > 0) {
        list.add(x % base)
        x /= base
    }
    list.reverse()
    return list
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {

    val list = convert(n, base)
    val res = buildString {
        for (elem in list) {
            when (elem) {
                in 0..9 -> append(elem)
                else -> append('a' + (elem - 10))
            }
        }
    }
    return res
}


/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */

fun decimal(digits: List<Int>, base: Int): Int {
    var res = 0
    var reversedDigits = digits.reversed()
    for ((c, elem) in reversedDigits.withIndex()) {
        res += base.toDouble().pow(c).toInt() * elem
    }
    return res
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val units = listOf<String>("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val decades = listOf<String>("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val hundreds = listOf<String>("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val thousands = listOf<String>("M", "MM", "MMM")
    var num = n
    var c = 0
    val res = buildString {
        while (num != 0) {
            c++
            val t = num % 10
            num /= 10
            if (t == 0) continue
            when (c) {
                1 -> insert(0, units[t - 1])
                2 -> insert(0, decades[t - 1])
                3 -> insert(0, hundreds[t - 1])
                4 -> insert(0, thousands[t - 1])
            }
        }
    }
    return res
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var c = 0
    var num = n

    val units = listOf<String>(
        "один", "два", "три", "четыре", "пять", "шесть", "семь",
        "восемь", "девять"
    )
    val firstDecade = listOf<String>(
        "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    )
    val decades = listOf<String>(
        "двадцать ", "тридцать ", "сорок ", "пятьдесят ", "шестьдесят ",
        "семьдесят ", "восемьдесят ", "девяносто "
    )
    val hundreds = listOf<String>(
        "сто ", "двести ", "триста ", "четыреста ", "пятьсот ", "шестьсот ",
        "семьсот ", "восемьсот ", "девятьсот "
    )
    val firstThousands = listOf<String>(
        "тысяч ", "одна тысяча ", "две тысячи ", "три тысячи ",
        "четыре тысячи ", "пять тысяч ", "шесть тысяч ", "семь тысяч ",
        "восемь тысяч ", "девять тысяч "
    )
    var res = buildString {
        while (num != 0) {
            c++
            val t = num % 10
            num /= 10
            when (c) {
                1 -> {
                    if (num % 10 == 1) {
                        insert(0, firstDecade[t])
                        c++
                        num /= 10
                        continue
                    }
                    when (t) {
                        0 -> continue
                        else -> insert(0, units[t - 1])
                    }
                }
                2 -> {
                    when (t) {
                        0 -> continue
                        else -> insert(0, decades[t - 2])
                    }
                }
                3 -> {
                    when (t) {
                        0 -> continue
                        else -> insert(0, hundreds[t - 1])
                    }
                }
                4 -> {
                    if (num == 0 && t == 1) {
                        insert(0, "одна тысяча ")
                        break
                    }
                    if (num % 10 == 1) {
                        insert(0, firstDecade[t] + " тысяч ")
                        c++
                        num /= 10
                        continue
                    }
                    insert(0, firstThousands[t])
                }
                5 -> {
                    when (t) {
                        0 -> continue
                        else -> insert(0, decades[t - 2])
                    }
                }
                6 -> insert(0, hundreds[t - 1])
            }
        }
    }
    res = res.trim()
    return res
}