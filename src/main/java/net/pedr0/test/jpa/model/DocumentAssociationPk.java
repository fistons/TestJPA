package net.pedr0.test.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentAssociationPk implements Serializable {

    @ManyToOne
    private Document document;
    @ManyToOne
    private DocGroup group;
}
