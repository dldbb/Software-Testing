import java.util.Objects;

public class C {
    int f;
    public C(int f) {
        this.f = f;
    }
    @Override
    public boolean equals(Object o) {
        // assume this method is implemented for you
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        C c = (C)o;
        return this.f == c.f;
    }
    @Override
    public int hashCode() {
        // assume this method is implemented for you
        return Objects.hash(this.f);
    }
}
