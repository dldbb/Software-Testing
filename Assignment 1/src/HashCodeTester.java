import static org.junit.Assert.*;
import org.junit.Test;

public class HashCodeTester {
    /*
     * P5: If two objects are equal according to the equals(Object)
     * method, then calling the hashCode method on each of
     * the two objects must produce the same integer result.
     */
// your test methods go here
    @Test public void t0() {
        Object o1 = new Object();
        Object o2 = new Object();
        assertFalse(o1.hashCode() == o2.hashCode());
    }

    @Test public void t1() {
        Object o1 = new Object();
        Object o2 = o1;
        assertTrue(o1.hashCode() == o2.hashCode());
    }

    @Test public void t2() {
        C c1 = new C(1);
        C c2 = new C(1);
        assertTrue(c1.hashCode() == c2.hashCode());
    }

    @Test public void t3() {
        C c1 = new C(1);
        C c2 = new C(0);
        assertFalse(c1.hashCode() == c2.hashCode());
    }

    @Test public void t4() {
        D d1 = new D(1, 2);
        D d2 = new D(1, 2);
        assertTrue(d1.hashCode() == d2.hashCode());
    }

    @Test public void t5() {
        D d1 = new D(1, 2);
        D d2 = new D(1, 0);
        assertFalse(d1.hashCode() == d2.hashCode());
    }

    @Test public void t6() {
        Object o = new Object();
        C c = new C(0);
        assertFalse(o.hashCode() == c.hashCode());
    }

    @Test public void t7() {
        Object o = new Object();
        D d = new D(1, 2);
        assertFalse(o.hashCode() == d.hashCode());
    }

    @Test public void t8() {
        C c = new C(0);
        D d = new D(0, 0);
        assertFalse(c.hashCode() == d.hashCode());
    }
}