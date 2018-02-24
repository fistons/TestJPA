package net.pedr0.test.jpa.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.io.Serializable;
import java.util.Set;

@Entity
public class DocGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Metadata> metadata;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.group", cascade = CascadeType.ALL)
    @OrderBy("docOrder")
    private Set<DocumentAssociation> documents;

    public DocGroup() {
    }

    public DocGroup(String name, Set<Metadata> metadata) {
        this();
        this.name = name;
        this.metadata = metadata;
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

    public Set<Metadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(Set<Metadata> metadata) {
        this.metadata = metadata;
    }

    public Set<DocumentAssociation> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<DocumentAssociation> documents) {
        this.documents = documents;
    }
}
