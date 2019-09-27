import java.util.Objects;

public class D extends C {
    int g;
    public D(int f, int g) {
        super(f);
        this.g = g;
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
        D d = (D)o;
        return (this.f == d.f && this.g== d.g);
    }
    @Override
    public int hashCode() {
        // assume this method is implemented for you
        return Objects.hash(f, g);
    }
}
