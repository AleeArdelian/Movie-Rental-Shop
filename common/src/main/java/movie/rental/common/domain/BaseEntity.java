package movie.rental.common.domain;

import java.io.Serializable;

/**
 * @author radu.
 */
public class BaseEntity<ID> implements Serializable {

    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
