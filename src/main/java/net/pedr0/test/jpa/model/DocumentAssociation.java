package net.pedr0.test.jpa.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class DocumentAssociation {

    @EmbeddedId
    private DocumentAssociationPk id;

    @ManyToOne
    private DocumentGroup documentGroup;
    @ManyToOne
    private Document document;

    @Column
    private boolean byDefault;
    @Column
    private int order;

}
