package net.pedr0.test.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
