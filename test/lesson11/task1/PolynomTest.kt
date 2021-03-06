package lesson11.task1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag

class PolynomTest {

    private fun assertApproxEquals(expected: Polynom, actual: Polynom, eps: Double) {
        assertEquals(expected.degree(), actual.degree())
        for (i in 0..expected.degree()) {
            assertEquals(expected.coeff(i), actual.coeff(i), eps)
        }
    }

    @Test
    @Tag("Easy")
    fun getValue() {
        val p = Polynom(1.0, 3.0, 2.0)
        assertEquals(42.0, p.getValue(5.0), 1e-10)
    }

    @Test
    @Tag("Easy")
    fun degree() {
        val p = Polynom(1.0, 1.0, 1.0)
        assertEquals(2, p.degree())
        val q = Polynom(0.0)
        assertEquals(0, q.degree())
        val r = Polynom(0.0, 1.0, 2.0)
        assertEquals(1, r.degree())
        val s = Polynom(0.0, 0.0, 0.0)
        assertEquals(0, s.degree())
        val t = Polynom(0.0, 0.0, 2.0)
        assertEquals(0, t.degree())
    }

    @Test
    @Tag("Easy")
    fun plus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -1.0, 2.0, 6.0)
        assertApproxEquals(r, p1 + p2, 1e-10)
        assertApproxEquals(r, p2 + p1, 1e-10)
        val p3 = Polynom(5.0, -2.0, 1.0, 8.0)
        val p4 = Polynom(0.0)
        val s = Polynom(5.0, -2.0, 1.0, 8.0)
        assertApproxEquals(s, p3 + p4, 1e-10)
        assertApproxEquals(s, p3 + p4, 1e-10)
    }

    @Test
    @Tag("Easy")
    operator fun unaryMinus() {
        val p = Polynom(1.0, -1.0, 2.0)
        val r = Polynom(-1.0, 1.0, -2.0)
        assertApproxEquals(r, -p, 1e-11)
        val a = Polynom(5.0, 6.0, -7.0)
        val b = Polynom(-5.0, -6.0, 7.0)
        assertApproxEquals(a, -b, 1e-11)
    }

    @Test
    @Tag("Easy")
    fun minus() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val p3 = Polynom(2.0, -8.0, -1.0)
        val r = Polynom(1.0, -3.0, -4.0, 2.0)
        assertApproxEquals(r, p1 - p2, 1e-10)
        assertApproxEquals(-r, p2 - p1, 1e-10)
        assertApproxEquals(Polynom(1.0, -4.0, 7.0, 5.0), p1 - p3, 1e-10)
    }

    @Test
    @Tag("Normal")
    fun times() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, 1.0, -5.0, -3.0, 10.0, 8.0)
        assertApproxEquals(r, p1 * p2, 1e-10)
        assertApproxEquals(r, p2 * p1, 1e-10)
        val p3 = Polynom(1.0, 1.0)
        val p4 = Polynom(1.0, 1.0)
        val s = Polynom(1.0, 2.0, 1.0)
        assertApproxEquals(s, p3 * p4, 1e-10)
        assertApproxEquals(s, p4 * p3, 1e-10)
    }

    @Test
    @Tag("Hard")
    fun div() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        assertApproxEquals(r, p1 / p2, 1e-10)
        assertApproxEquals(Polynom(1.0, 2.0), Polynom(2.0, 4.0) / Polynom(0.0, 2.0), 1e-10)
        assertApproxEquals(Polynom(1.0, -15.0), Polynom(1.0, 0.0, 0.0, -5.0) / Polynom(1.0, 15.0, -7.0), 1e-10)
        assertApproxEquals(Polynom(1.0, 2.0), Polynom(1.0, 2.0) / Polynom(1.0), 1e-10)
    }

    @Test
    @Tag("Hard")
    fun rem() {
        val p1 = Polynom(1.0, -2.0, -1.0, 4.0)
        val p2 = Polynom(1.0, 3.0, 2.0)
        val r = Polynom(1.0, -5.0)
        val q = Polynom(12.0, 14.0)
        assertApproxEquals(q, p1 % p2, 1e-10)
        assertApproxEquals(p1, p2 * r + q, 1e-10)
    }

    @Test
    @Tag("Easy")
    fun equals() {
        assertEquals(Polynom(1.0, 2.0, 3.0), Polynom(1.0, 2.0, 3.0))
        assertEquals(Polynom(0.0, 2.0, 3.0), Polynom(2.0, 3.0))
    }

    @Test
    @Tag("Normal")
    fun hashCodeTest() {
        assertEquals(Polynom(1.0, 2.0, 3.0).hashCode(), Polynom(1.0, 2.0, 3.0).hashCode())
        assertEquals(Polynom(0.0).hashCode(), Polynom(0.0).hashCode())
    }
}