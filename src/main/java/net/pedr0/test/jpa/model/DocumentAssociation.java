package net.pedr0.test.jpa.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class DocumentAssociation implements Serializable {

    @EmbeddedId
    private DocumentAssociationPk pk;

    private boolean byDefault;
    private int docOrder;


    public DocumentAssociation() {
    }

    public DocumentAssociation(DocumentAssociationPk pk, boolean byDefault, int docOrder) {
        this();
        this.pk = pk;
        this.byDefault = byDefault;
        this.docOrder = docOrder;
    }

    public Document getDocument() {
        return this.pk.getDocument();
    }

    public DocGroup getGroup() {
        return this.pk.getGroup();
    }


    public DocumentAssociationPk getPk() {
        return pk;
    }

    public void setPk(DocumentAssociationPk pk) {
        this.pk = pk;
    }

    public boolean isByDefault() {
        return byDefault;
    }

    public void setByDefault(boolean byDefault) {
        this.byDefault = byDefault;
    }

    public int getDocOrder() {
        return docOrder;
    }

    public void setDocOrder(int docOrder) {
        this.docOrder = docOrder;
    }
}
