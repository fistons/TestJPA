package net.pedr0.test.jpa.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class DocumentAssociationPk implements Serializable{

    private Integer docGroupId;
    private Integer docId;
}
