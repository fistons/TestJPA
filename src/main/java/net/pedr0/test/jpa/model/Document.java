package net.pedr0.test.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "pk.document")
    private Set<DocumentAssociation> groups;


    public Document() {
    }

    public Document(String name, String description, Set<DocumentAssociation> groups) {
        this();
        this.name = name;
        this.description = description;
        this.groups = groups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<DocumentAssociation> getGroups() {
        return groups;
    }

    public void setGroups(Set<DocumentAssociation> groups) {
        this.groups = groups;
    }
}
