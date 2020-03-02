@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1


/**
 * Класс "полином с вещественными коэффициентами".
 *
 * Общая сложность задания -- сложная.
 * Объект класса -- полином от одной переменной (x) вида 7x^4+3x^3-6x^2+x-8.
 * Количество слагаемых неограничено.
 *
 * Полиномы можно складывать -- (x^2+3x+2) + (x^3-2x^2-x+4) = x^3-x^2+2x+6,
 * вычитать -- (x^3-2x^2-x+4) - (x^2+3x+2) = x^3-3x^2-4x+2,
 * умножать -- (x^2+3x+2) * (x^3-2x^2-x+4) = x^5+x^4-5x^3-3x^2+10x+8,
 * делить с остатком -- (x^3-2x^2-x+4) / (x^2+3x+2) = x-5, остаток 12x+16
 * вычислять значение при заданном x: при x=5 (x^2+3x+2) = 42.
 *
 * В конструктор полинома передаются его коэффициенты, начиная со старшего.
 * Нули в середине и в конце пропускаться не должны, например: x^3+2x+1 --> Polynom(1.0, 2.0, 0.0, 1.0)
 * Старшие коэффициенты, равные нулю, игнорировать, например Polynom(0.0, 0.0, 5.0, 3.0) соответствует 5x+3
 */
class Polynom(vararg coeffs: Double) {

    private val trueCoeffs = coeffs.reversed().dropLastWhile { it == 0.0 }.toMutableList()

    constructor(list: List<Double>) : this(*list.toDoubleArray())

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = trueCoeffs[i]

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var ans = 0.0
        var a = 1.0
        for (i in trueCoeffs) {
            ans += i * a
            a *= x
        }
        return ans
    }

    /**
     * Степень (максимальная степень x при ненулевом слагаемом, например 2 для x^2+x+1).
     *
     * Степень полинома с нулевыми коэффициентами считать равной 0.
     * Слагаемые с нулевыми коэффициентами игнорировать, т.е.
     * степень 0x^2+0x+2 также равна 0.
     */
    fun degree(): Int {
        for (i in trueCoeffs.indices.reversed()) {
            if (trueCoeffs[i] != 0.0) return i
        }
        return 0
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {

        val maxPolynom: MutableList<Double>
        val minPolynom: MutableList<Double>

        if (this.degree() > other.degree()) {
            maxPolynom = this.trueCoeffs
            minPolynom = other.trueCoeffs
        } else {
            maxPolynom = other.trueCoeffs
            minPolynom = this.trueCoeffs
        }

        val coeffs = MutableList(maxPolynom.size) { 0.0 }
        while (maxPolynom.size != minPolynom.size) minPolynom += 0.0

        coeffs.indices.forEach { coeffs[it] = maxPolynom[it] + minPolynom[it] }

        return Polynom(coeffs.reversed())
    }


    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom = Polynom(trueCoeffs.reversed().map { -it })

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom = this + other.unaryMinus()

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom {
        val answer = MutableList(this.trueCoeffs.size + other.trueCoeffs.size) { 0.0 }
        for (i in this.trueCoeffs.indices) {
            for (j in other.trueCoeffs.indices)
                answer[i + j] += trueCoeffs[i] * other.trueCoeffs[j]
        }
        return Polynom(answer.reversed())
    }

    /**
     * Функция, которая сразу делит многочлен на многочлен и ищет остаток
     * от деления.
     * Возвращает два многочлена в виде пары, далее в соответствующих
     * функциях выбирается нужная часть.
     */
    private fun divOrRem(other: Polynom): Pair<Polynom, Polynom> {
        var divisible = this
        val answerList = mutableListOf<Double>()
        var i = divisible.trueCoeffs.size - other.trueCoeffs.size
        var rem = Polynom()

        while (i >= 0) {
            val partOfAnswer = divisible.trueCoeffs[i + other.trueCoeffs.size - 1] / other.trueCoeffs.last()
            answerList += partOfAnswer
            val newList = List(i + 1) { 0.0 }.toMutableList()
            newList[newList.size - 1] = partOfAnswer
            val currentPolynom = Polynom(newList.reversed()) * other
            divisible -= currentPolynom
            rem = divisible
            i--
        }
        return Pair(Polynom(answerList), rem)
    }

    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom = divOrRem(other).first

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = divOrRem(other).second

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = other is Polynom && this.trueCoeffs == other.trueCoeffs

    override fun toString(): String = trueCoeffs.joinToString()

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = this.trueCoeffs.hashCode()
}
