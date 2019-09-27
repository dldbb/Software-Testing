import static org.junit.Assert.*;
import org.junit.Test;

public class SLListRepOkTester {//{}
    @Test public void t0() {
        SLList l = new SLList();
        assertTrue(l.repOk());
    }

    @Test public void t1() {//n
        SLList l = new SLList();
        SLList.Node n = new SLList.Node();
        l.header = n;
        assertTrue(l.repOk());
    }

    @Test public void t2() {//n-n
        SLList l = new SLList();
        SLList.Node n = new SLList.Node();
        n.next = n;
        l.header = n;
        assertFalse(l.repOk());
    }

    @Test public void t3() {//n1-n2
        SLList l = new SLList();
        SLList.Node n1 = new SLList.Node();
        SLList.Node n2 = new SLList.Node();
        n1.next = n2;
        l.header = n1;
        assertTrue(l.repOk());
    }

    @Test public void t4() {//n1-n2-n1
        SLList l = new SLList();
        SLList.Node n1 = new SLList.Node();
        SLList.Node n2 = new SLList.Node();
        n1.next = n2;
        n2.next = n1;
        l.header = n1;
        assertFalse(l.repOk());
    }

    @Test public void t5() {//n1-n2-n2
        SLList l = new SLList();
        SLList.Node n1 = new SLList.Node();
        SLList.Node n2 = new SLList.Node();
        n1.next = n2;
        n2.next = n2;
        l.header = n1;
        assertFalse(l.repOk());
    }
}
