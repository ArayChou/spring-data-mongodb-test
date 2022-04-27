package test;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document
public class Father {
    @Id
    private String id;
    private String name;

    @ReadOnlyProperty
    @DocumentReference(lazy = true, lookup = "{'father.$id':?#{#self._id} }")
    private List<Kid> kid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Kid> getKid() {
        return kid;
    }

    public void setKid(List<Kid> kid) {
        this.kid = kid;
    }
}
