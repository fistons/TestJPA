package net.pedr0.test.jpa.model;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
public class DocumentAssociation {

    @EmbeddedId
    private DocumentAssociationPk pk;

    private boolean byDefault;
    private int docOrder;

    public Document getDocument() {
        return this.pk.getDocument();
    }

    public DocGroup getGroup() {
        return this.pk.getGroup();
    }

}
