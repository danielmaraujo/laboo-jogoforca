package dominio;

import java.util.Objects;

public class ObjetoDominioImpl implements ObjetoDominio{
    private long id;

    public ObjetoDominioImpl(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjetoDominioImpl that = (ObjetoDominioImpl) o;
        return id == that.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }
}
