import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Objects;

public class EqualsTester {
    /*
     * P1: For any non-null reference value x, x.equals(null) should return false.
     */
    @Test public void t0() {
        assertFalse(new Object().equals(null));
    }

    @Test public void t1() {
        assertFalse(new C(0).equals(null));
    }

    @Test public void t2() {
        assertFalse(new D(0, 1).equals(null));
    }

    /*
     * P2: It is reflexive: for any non-null reference value x, x.equals(x)
     * should return true.
     */
    @Test public void t3() {
        Object obj = new Object();
        assertTrue(obj.equals(obj));
    }

    @Test public void t4() {
        C c = new C(0);
        assertTrue(c.equals(c));
    }

    @Test public void t5() {
        D d = new D(0, 1);
        assertTrue(d.equals(d));
    }

    /*
     * P3: It is symmetric: for any non-null reference values x and y, x.equals(y)
     * should return true if and only if y.equals(x) returns true.
     */
    @Test public void t6() {
        Object o1 = new Object();
        Object o2 = new Object();
        assertFalse(o1.equals(o2) || o2.equals(o1));
    }

    @Test public void t7() {
        Object o1 = new Object();
        Object o2 = o1;
        assertTrue(o1.equals(o2) && o2.equals(o1));
    }

    @Test public void t8() {
        C c1 = new C(1);
        C c2 = new C(1);
        assertTrue(c1.equals(c2) && c2.equals(c1));
    }

    @Test public void t9() {
        C c1 = new C(1);
        C c2 = new C(0);
        assertFalse(c1.equals(c2) || c2.equals(c1));
    }

    @Test public void t10() {
        D d1 = new D(1, 2);
        D d2 = new D(1, 2);
        assertTrue(d1.equals(d2) && d2.equals(d1));
    }

    @Test public void t11() {
        D d1 = new D(1, 2);
        D d2 = new D(1, 0);
        assertFalse(d1.equals(d2) || d2.equals(d1));
    }

    @Test public void t12() {
        Object o = new Object();
        C c = new C(0);
        assertFalse(o.equals(c) || c.equals(o));
    }

    @Test public void t13() {
        Object o = new Object();
        D d = new D(1, 2);
        assertFalse(o.equals(d) || d.equals(o));
    }

    @Test public void t14() {
        C c = new C(0);
        D d = new D(0, 0);
        assertFalse(c.equals(d) || d.equals(c));
    }



// your test methods for P3 go here
    /*
     * P4: It is transitive: for any non-null reference values x, y, and z,
     * if x.equals(y) returns true and y.equals(z) returns true, then
     * x.equals(z) should return true.
     */
    // you do not need to write tests for P4
}
