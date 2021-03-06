package lesson6.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test

class Tests {
    @Test
    @Tag("Example")
    fun timeStrToSeconds() {
        assertEquals(36000, timeStrToSeconds("10:00:00"))
        assertEquals(41685, timeStrToSeconds("11:34:45"))
        assertEquals(86399, timeStrToSeconds("23:59:59"))
    }

    @Test
    @Tag("Example")
    fun twoDigitStr() {
        assertEquals("00", twoDigitStr(0))
        assertEquals("09", twoDigitStr(9))
        assertEquals("10", twoDigitStr(10))
        assertEquals("99", twoDigitStr(99))
    }

    @Test
    @Tag("Example")
    fun timeSecondsToStr() {
        assertEquals("10:00:00", timeSecondsToStr(36000))
        assertEquals("11:34:45", timeSecondsToStr(41685))
        assertEquals("23:59:59", timeSecondsToStr(86399))
    }

    @Test
    @Tag("Normal")
    fun dateStrToDigit() {
        assertEquals("15.07.2016", dateStrToDigit("15 июля 2016"))
        assertEquals("", dateStrToDigit("3 мартобря 1918"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("23"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("32 сентября 2011"))
        assertEquals("", dateStrToDigit("29 февраля 1993"))
        assertEquals("", dateStrToDigit("15 июля 2016 2017"))
        assertEquals("", dateStrToDigit("3 а 2011"))
        assertEquals("", dateStrToDigit("3 12 2011"))
        assertEquals("", dateStrToDigit("3 12 2011"))
        assertEquals("03.04.2011", dateStrToDigit("3 апреля 2011"))
        assertEquals("", dateStrToDigit("3 апреляя 2011"))
        assertEquals("", dateStrToDigit("3b апреля 2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
        assertEquals("", dateDigitToStr("ab.12.ef"))
        assertEquals("", dateDigitToStr("a2.cd.3f"))
        assertEquals("", dateDigitToStr("12.23.3,23"))
        assertEquals("", dateDigitToStr("12.23.3!@#$%^&*()23"))
        assertEquals("", dateDigitToStr("15.13.2016"))
        assertEquals("18.11.2018", dateStrToDigit("18 ноября 2018"))
        assertEquals("", dateStrToDigit("31 февраля 4321540"))
        assertEquals("", dateStrToDigit("30.02.4"))
        assertEquals("01.02.1", dateStrToDigit("01 февраля 1"))

    }

    @Test
    @Tag("Normal")
    fun dateDigitToStr() {
        assertEquals("15 июля 2016", dateDigitToStr("15.07.2016"))
        assertEquals("", dateDigitToStr("01.02.20.19"))
        assertEquals("", dateDigitToStr("28.00.2000"))
        assertEquals("3 апреля 2011", dateDigitToStr("03.04.2011"))
        assertEquals("", dateDigitToStr("ab.cd.ef"))
        assertEquals("", dateDigitToStr("32.09.2011"))
        assertEquals("", dateDigitToStr("29.02.1993"))
        assertEquals("", dateDigitToStr("30.02.4"))
        assertEquals("", dateDigitToStr("30.02.6325324"))
        assertEquals("", dateDigitToStr("31.04.1"))
    }

    @Test
    @Tag("Normal")
    fun flattenPhoneNumber() {
        assertEquals("+79211234567", flattenPhoneNumber("+7 (921) 123-45-67"))
        assertEquals("123456798", flattenPhoneNumber("12 --  34- 5 -- 67 -98"))
        assertEquals("+12345", flattenPhoneNumber("+12 (3) 4-5"))
        assertEquals("", flattenPhoneNumber("+12 () 4-5"))
        assertEquals("+425667", flattenPhoneNumber("+42 56 -- 67"))
        assertEquals("+42566789", flattenPhoneNumber("+42(56 -- 67)89"))
        assertEquals("", flattenPhoneNumber("ab-123"))
        assertEquals("", flattenPhoneNumber("134_+874"))
        assertEquals("+12845", flattenPhoneNumber("+12 (8) 4-5"))
        assertEquals("", flattenPhoneNumber("+12 (-) 4-5"))
        assertEquals("", flattenPhoneNumber("+7 )921) 123-45-67"))
        assertEquals("", flattenPhoneNumber("+7 )921( 123-45-67"))
        assertEquals("", flattenPhoneNumber("+7 ((921) 123-45-67"))
        assertEquals("", flattenPhoneNumber("+7 (921)) 123-45-67"))
        assertEquals("", flattenPhoneNumber("+7 (921) 123-45-67("))
    }

    @Test
    @Tag("Normal")
    fun bestLongJump() {
        assertEquals(717, bestLongJump("706 % - 717 - 703"))
        assertEquals(-1, bestLongJump("% - - % -"))
        assertEquals(754, bestLongJump("700 717 707 % 754"))
        assertEquals(-1, bestLongJump("700 + 700"))
        assertEquals(11, bestLongJump("- 11 - 9 % 5"))

    }

    @Test
    @Tag("Hard")
    fun bestHighJump() {
        assertEquals(-1, bestHighJump(""))
        assertEquals(-1, bestHighJump("-880 + 552 - 444 ) 900"))
        assertEquals(-1, bestHighJump("898&199%87*^*&@^"))
        assertEquals(226, bestHighJump("226 +"))
        assertEquals(-1, bestHighJump("???"))
        assertEquals(230, bestHighJump("220 + 224 %+ 228 %- 230 + 232 %%- 234 %"))
    }

    @Test
    @Tag("Hard")
    fun plusMinus() {
        assertEquals(0, plusMinus("0"))
        assertEquals(4, plusMinus("2 + 2"))
        assertEquals(6, plusMinus("2 + 31 - 40 + 13"))
        assertEquals(-1, plusMinus("0 - 1"))
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("+ 4") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - -2") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("44 - - 12") }
        assertThrows(IllegalArgumentException::class.java) { plusMinus("4 - + 12") }
    }

    @Test
    @Tag("Hard")
    fun firstDuplicateIndex() {
        assertEquals(-1, firstDuplicateIndex("Привет"))
        assertEquals(9, firstDuplicateIndex("Он пошёл в в школу"))
        assertEquals(40, firstDuplicateIndex("Яблоко упало на ветку с ветки оно упало на на землю"))
        assertEquals(9, firstDuplicateIndex("Мы пошли прямо Прямо располагался магазин"))
    }

    @Test
    @Tag("Hard")
    fun mostExpensive() {
        assertEquals("", mostExpensive(""))
        assertEquals("Курица", mostExpensive("Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9"))
        assertEquals("Вино", mostExpensive("Вино 255.0"))
        assertEquals("a", mostExpensive("a 0.0"))
        assertEquals("}", mostExpensive("} 0"))
        assertEquals(",", mostExpensive(", 0"))
        assertEquals("[", mostExpensive("[ 0; a 0"))
        assertEquals(",", mostExpensive(", 0; a 0"))
        assertEquals("a", mostExpensive("aa 0; a 0.01"))
    }

    @Test
    @Tag("Hard")
    fun fromRoman() {
        assertEquals(1, fromRoman("I"))
        assertEquals(4, fromRoman("IV"))
        assertEquals(3000, fromRoman("MMM"))
        assertEquals(1978, fromRoman("MCMLXXVIII"))
        assertEquals(694, fromRoman("DCXCIV"))
        assertEquals(49, fromRoman("XLIX"))
        assertEquals(5009, fromRoman("MMMMMIX"))
        assertEquals(80, fromRoman("LXXX"))
        assertEquals(753, fromRoman("DCCLIII"))
        assertEquals(499, fromRoman("CDXCIX"))
        assertEquals(-1, fromRoman("Z"))
        assertEquals(-1, fromRoman(""))
    }

    @Test
    @Tag("Impossible")
    fun computeDeviceCells() {
        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 1, 1, 1), computeDeviceCells(10, "+>+>+>+>+", 10000))
        assertEquals(listOf(-1, -1, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 10000))
        assertEquals(listOf(1, 1, 1, 1, 1, 0, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 10000))
        assertEquals(
            listOf(0, 8, 7, 6, 5, 4, 3, 2, 1, 0, 0),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 10000)
        )

        assertEquals(listOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0), computeDeviceCells(10, "+>+>+>+>+", 4))
        assertEquals(listOf(0, 0, -1, -1, -1, 0, 0, 0, 0, 0), computeDeviceCells(10, "<-<-<-<-<-", 6))
        assertEquals(listOf(1, 1, 1, 0, 0, -1, 0, 0, 0, 0), computeDeviceCells(10, "- <<<<< +[>+]", 17))
        assertEquals(
            listOf(0, 6, 5, 4, 3, 2, 1, 0, -1, -1, -2),
            computeDeviceCells(11, "<<<<< + >>>>>>>>>> --[<-] >+[>+] >++[--< <[<] >+[>+] >++]", 256)
        )
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "===", 3) }
        assertThrows(IllegalArgumentException::class.java) { computeDeviceCells(10, "+>+>[+>", 3) }
        assertThrows(IllegalStateException::class.java) { computeDeviceCells(20, ">>>>>>>>>>>>>", 12) }
    }

    @Test
    fun myFun() {
        assertEquals(
            listOf("Андрей 31 апреля", "Вася 56 июля"),
            myFun("Андрей 31 апреля, Маша 3 апреля, Коля 3 июля, Вася 56 июля")
        )
        assertEquals(listOf("Вася 56 июля"), myFun("Андрей 12 июля, Маша 3 апреля, Коля 3 июля, Вася 56 июля"))
        assertEquals(emptyList<String>(), myFun("Андрей 12 июля, Маша 3 апреля, Коля 3 июля, Вася 6 июля"))
        assertEquals(
            listOf("Андрей 31 апреля", "Маша 3 апреляя", "Коля 32 июля", "Вася 56 июля"),
            myFun("Андрей 31 апреля, Маша    3 апреляя, Коля 32 июля, Вася 56 июля")
        )

    }


}