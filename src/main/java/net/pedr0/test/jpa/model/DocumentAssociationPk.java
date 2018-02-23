package net.pedr0.test.jpa.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class DocumentAssociationPk implements Serializable {

    @ManyToOne
    private Document document;
    @ManyToOne
    private DocGroup group;
}
