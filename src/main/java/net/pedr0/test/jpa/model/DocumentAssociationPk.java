package net.pedr0.test.jpa.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class DocumentAssociationPk implements Serializable {

    @ManyToOne
    private Document document;
    @ManyToOne
    private DocGroup group;

    public DocumentAssociationPk() {
    }

    public DocumentAssociationPk(Document document, DocGroup group) {
        this();
        this.document = document;
        this.group = group;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public DocGroup getGroup() {
        return group;
    }

    public void setGroup(DocGroup group) {
        this.group = group;
    }
}
