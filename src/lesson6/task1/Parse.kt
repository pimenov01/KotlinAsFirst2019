@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import ru.spbstu.wheels.NegativeInfinity
import kotlin.random.Random


/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */

fun dateStrToDigit(str: String): String {
    val fail = ""
    if (!(str.matches(Regex("""\d+\s[а-я]+\s\d+""")))) return fail
    val map = mapOf(
        "января" to "01",
        "февраля" to "02",
        "марта" to "03",
        "апреля" to "04",
        "мая" to "05",
        "июня" to "06",
        "июля" to "07",
        "августа" to "08",
        "сентября" to "09",
        "октября" to "10",
        "ноября" to "11",
        "декабря" to "12"
    )
    val a = str.split(" ")
    if (a.size != 3 || a[0].toInt() !in 1..31) return fail
    val day = a[0].toInt()
    val month = a[1]
    val year = a[2].toInt()
    if (map[month] == null) return fail
    if (day > daysInMonth(map[month]!!.toInt(), year)) return fail
    return "${twoDigitStr(day)}.${map[month]}.$year"
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val fail = ""
    if (!(digital.matches(Regex("""\d{1,2}\.\d{2}\.\d+""")))) return fail
    val a = digital.split(".")
    val day = a[0].toInt()
    var month = a[1]
    val year = a[2].toInt()
    if (day > daysInMonth(month.toInt(), year)) return fail
    month = when (month) {
        "01" -> "января"
        "02" -> "февраля"
        "03" -> "марта"
        "04" -> "апреля"
        "05" -> "мая"
        "06" -> "июня"
        "07" -> "июля"
        "08" -> "августа"
        "09" -> "сентября"
        "10" -> "октября"
        "11" -> "ноября"
        "12" -> "декабря"
        else -> return fail
    }
    return "$day $month $year"
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val possible = setOf('+', '(', ')', ' ', '-')
    val c = (phone.toSet() - possible - setOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'))
    if (c.isNotEmpty()) return ""
    val k1 = phone.filter { it in "()" }
    if (k1.isNotEmpty())
        if (!k1.matches(Regex("""\(\)"""))) return ""
    val k = phone.filter { it !in "- " }
    if (k.indexOf('(') - k.indexOf(')') == -1) return ""
    return k.filter { it !in "()" }
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (jumps.isEmpty()) return -1
    var answer = -1
    if (!jumps.matches(Regex("""((\d+|%|-)\s)*(\d+|%|-)"""))) return -1
    val c = jumps.filter { it !in "-%" }.split(" ")
    for (i in c.indices) {
        if (c[i] != "" && c[i].toInt() > answer) {
            answer = c[i].toInt()
        }
    }
    return answer
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var count = -1
    if (jumps.isEmpty() || !(jumps.matches(Regex("""((\d+\s)([%+\-])+\s)*(\d+\s)([%+\-])+""")))) return -1
    val attempts = jumps.filter { it !in "%-" }.split(" ")
    for (i in attempts.indices step 2) {
        if ((attempts[i].toInt() > count) && (attempts[i + 1] == "+"))
            count = attempts[i].toInt()
    }
    return count
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    require(expression.matches(Regex("""(\d+ *(\+|\-) *)*\d+""")))
    val rightExpression = expression.split(" ")
    var answer = rightExpression[0].toInt()
    if (rightExpression.size == 1) return answer
    for (i in 2..rightExpression.size step 2) {
        if (rightExpression[i - 1] == "+")
            answer += rightExpression[i].toInt()
        else
            answer -= rightExpression[i].toInt()
    }
    return answer
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val expression = str.toLowerCase().split(" ")
    var answer = 0
    for (i in 0 until expression.size - 1) {
        if (expression[i] == expression[i + 1])
            return answer
        else
            answer += expression[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    if (description.isEmpty()) return ""
    if (!(description.matches(Regex("""((\S+\s(\d+(.\d+)?));\s)*(\S+\s(\d+(.\d+)?))""")))) return ""
    val c = description.split("; ")
    val almostSomething = c[0].split(" ")
    var name = almostSomething[0]
    var maxPrice = almostSomething[1].toDouble()
    for (i in 1 until c.size) {
        val almostSomething2 = c[i].split(" ")
        val currentPrice = almostSomething2[1].toDouble()
        if (currentPrice > maxPrice) {
            maxPrice = currentPrice
            name = almostSomething2[0]
        }
    }
    return name
}


/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (!roman.matches(Regex("""[IMCLXVD]*"""))) return -1
    if (roman.isEmpty()) return -1
    var roman1 = roman
    var answer = 0
    val c = roman.toList()
    for (i in 0 until c.size - 1) {
        if (c[i] == 'I' && c[i + 1] == 'V') {
            answer += 4
            roman1 = roman1.replace("IV", "1")
        }
        if (c[i] == 'I' && c[i + 1] == 'X') {
            answer += 9
            roman1 = roman1.replace("IX", "1")
        }
        if (c[i] == 'X' && c[i + 1] == 'L') {
            answer += 40
            roman1 = roman1.replace("XL", "1")
        }
        if (c[i] == 'X' && c[i + 1] == 'C') {
            answer += 90
            roman1 = roman1.replace("XC", "1")
        }
        if (c[i] == 'C' && c[i + 1] == 'D') {
            answer += 400
            roman1 = roman1.replace("CD", "1")
        }
        if (c[i] == 'C' && c[i + 1] == 'M') {
            answer += 900
            roman1 = roman1.replace("CM", "1")
        }
    }
    val newC = (roman1.filter { it !in "1" }).toList()
    if (newC.isNotEmpty()) {
        for (i in newC.indices) {
            when (newC[i]) {
                'I' -> answer += 1
                'V' -> answer += 5
                'X' -> answer += 10
                'L' -> answer += 50
                'C' -> answer += 100
                'D' -> answer += 500
                'M' -> answer += 1000
            }
        }
    }
    return answer
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()

fun myFun(text: String): List<String> {

    if (!text.matches(Regex("""([А-я]+\s+\d+\s[а-я]+,\s)+[А-я]+\s+\d+\s[а-я]+"""))) return emptyList()

    val result = mutableListOf<String>()
    val regex = Regex(" ")
    val text1 = text.replace(Regex("""\s+"""), " ")
    val c = text1.split(", ")

    val map = mapOf(
        "января" to 1,
        "февраля" to 2,
        "марта" to 3,
        "апреля" to 4,
        "мая" to 5,
        "июня" to 6,
        "июля" to 7,
        "августа" to 8,
        "сентября" to 9,
        "октября" to 10,
        "ноября" to 11,
        "декабря" to 11

    )

    for (line in c) {

        val z = line.split(regex)
        val day = z[1].toInt()
        val month = z[2]

        if (month !in map.keys) result += line else
            if (day > map[month]?.let { daysInMonth(it, 4) }!!)
                result += line
    }

    return result


}



