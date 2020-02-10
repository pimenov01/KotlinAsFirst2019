@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1
import kotlin.math.pow
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

    private val trueCoeffs = coeffs.toList().reversed()

    /**
     * Геттер: вернуть значение коэффициента при x^i
     */
    fun coeff(i: Int): Double = trueCoeffs[i]

    /**
     * Расчёт значения при заданном x
     */
    fun getValue(x: Double): Double {
        var ans = 0.0
        for (i in trueCoeffs.indices) {
            ans += x.pow(i) * trueCoeffs[i]
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
        val thisCoeffs = trueCoeffs.reversed()
        val size = thisCoeffs.size - 1
        for (i in thisCoeffs.indices) {
            if (thisCoeffs[i] != 0.0) return size - i
        }
        return 0
    }

    /**
     * Сложение
     */
    operator fun plus(other: Polynom): Polynom {

        val maxPolynom: List<Double>
        var minPolynom: List<Double>

        if (this.degree() > other.degree()) {
            maxPolynom = this.trueCoeffs
            minPolynom = other.trueCoeffs
        } else {
            maxPolynom = other.trueCoeffs
            minPolynom = this.trueCoeffs
        }

        val coeffs = MutableList(maxPolynom.size) { 0.0 }
        while (maxPolynom.size != minPolynom.size) minPolynom += 0.0

        for (i in maxPolynom.indices) coeffs[i] += maxPolynom[i] + minPolynom[i]

        return Polynom(*coeffs.reversed().toDoubleArray())
    }

    /**
     * Смена знака (при всех слагаемых)
     */
    operator fun unaryMinus(): Polynom {
        val unaryCoeffs = trueCoeffs.reversed().toMutableList()
        for (i in unaryCoeffs.indices) {
            unaryCoeffs[i] = -unaryCoeffs[i]
        }
        return Polynom(*unaryCoeffs.toDoubleArray())
    }

    /**
     * Вычитание
     */
    operator fun minus(other: Polynom): Polynom {
        val maxPolynom: List<Double>
        var minPolynom: List<Double>

        if (this.degree() > other.degree()) {
            maxPolynom = this.trueCoeffs
            minPolynom = other.trueCoeffs
        } else {
            maxPolynom = other.trueCoeffs
            minPolynom = this.trueCoeffs
        }

        val coeffs = MutableList(maxPolynom.size) { 0.0 }
        while (maxPolynom.size != minPolynom.size) minPolynom += 0.0


        for (i in maxPolynom.indices)
            if (this.trueCoeffs.size > other.trueCoeffs.size)
                coeffs[i] += maxPolynom[i] - minPolynom[i]
            else
                coeffs[i] += minPolynom[i] - maxPolynom[i]


        return Polynom(*coeffs.reversed().toDoubleArray())
    }

    /**
     * Умножение
     */
    operator fun times(other: Polynom): Polynom = TODO()

    /*var answer = Polynom()
        for (i in this.trueCoeffs.indices) {
            for (j in other.trueCoeffs.indices)
                answer += Polynom(this.trueCoeffs[i] * other.trueCoeffs[j]).plus(answer)

        }
        return answer*/


    /**
     * Деление
     *
     * Про операции деления и взятия остатка см. статью Википедии
     * "Деление многочленов столбиком". Основные свойства:
     *
     * Если A / B = C и A % B = D, то A = B * C + D и степень D меньше степени B
     */
    operator fun div(other: Polynom): Polynom = TODO()

    /**
     * Взятие остатка
     */
    operator fun rem(other: Polynom): Polynom = TODO()

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean = TODO()

    /**
     * Получение хеш-кода
     */
    override fun hashCode(): Int = TODO()
}
