package net.pedr0.test.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.Set;

@Data
@Entity
public class DocGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;

    @ManyToMany
    private Set<Metadata> metadatas;

    @OneToMany(mappedBy = "pk.group")
    @OrderBy("docOrder")
    private Set<DocumentAssociation> documents;

}