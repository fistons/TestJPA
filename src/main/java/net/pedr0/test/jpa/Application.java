package net.pedr0.test.jpa;

import net.pedr0.test.jpa.model.DocGroup;
import net.pedr0.test.jpa.model.Document;
import net.pedr0.test.jpa.model.DocumentAssociation;
import net.pedr0.test.jpa.model.DocumentAssociationPk;
import net.pedr0.test.jpa.model.Metadata;
import net.pedr0.test.jpa.repository.DocumentRepository;
import net.pedr0.test.jpa.repository.GroupRepository;
import net.pedr0.test.jpa.repository.MetadataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final DocumentRepository documentRepository;
    private final GroupRepository groupRepository;
    private final MetadataRepository metadataRepository;
    private final EntityManager em;

    @Autowired
    public Application(DocumentRepository documentRepository, GroupRepository groupRepository,
      MetadataRepository metadataRepository, EntityManager em) {
        this.documentRepository = documentRepository;
        this.groupRepository = groupRepository;
        this.metadataRepository = metadataRepository;
        this.em = em;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {

        LOGGER.info("Well, hello");

        Metadata metadata1 = Metadata.builder().name("meta1").description("description1").build();
        Metadata metadata2 = Metadata.builder().name("meta2").description("description2").build();
        List<Metadata> metadata = metadataRepository.save(Arrays.asList(metadata1, metadata2));

        Document document1 =
          Document.builder().name("document1").description("description1").build();
        Document document2 =
          Document.builder().name("document2").description("description2").build();
        List<Document> documents = documentRepository.save(Arrays.asList(document1, document2));

        DocGroup group =
          DocGroup.builder().metadatas(new HashSet<Metadata>(metadata)).name("group").build();

        DocumentAssociation association1 = DocumentAssociation.builder()
          .pk(DocumentAssociationPk.builder().document(documents.get(0)).group(group).build())
          .byDefault(false)
          .docOrder(1)
          .build();

        DocumentAssociation association2 = DocumentAssociation.builder()
          .pk(DocumentAssociationPk.builder().document(documents.get(1)).group(group).build())
          .byDefault(true)
          .docOrder(2)
          .build();

        group.setDocuments(new HashSet<DocumentAssociation>(Arrays.asList(association1, association2)));

        group = groupRepository.saveAndFlush(group);

//        em.detach(group);
//        group.setId(null);
//        group.setName("Group 2");
//        groupRepository.save(group);

        group = groupRepository.findOne(group.getId());
//        group.setDocuments(null);
        group = groupRepository.save(group);




    }
}
