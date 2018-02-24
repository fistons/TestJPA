package net.pedr0.test.jpa;

import net.pedr0.test.jpa.model.DocGroup;
import net.pedr0.test.jpa.model.Document;
import net.pedr0.test.jpa.model.DocumentAssociation;
import net.pedr0.test.jpa.model.DocumentAssociationPk;
import net.pedr0.test.jpa.model.Metadata;
import net.pedr0.test.jpa.repository.DocumentRepository;
import net.pedr0.test.jpa.repository.GroupRepository;
import net.pedr0.test.jpa.repository.MetadataRepository;
import org.hibernate.internal.util.SerializationHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final DocumentRepository documentRepository;
    private final GroupRepository groupRepository;
    private final MetadataRepository metadataRepository;

    @Autowired
    public Application(DocumentRepository documentRepository, GroupRepository groupRepository,
      MetadataRepository metadataRepository) {
        this.documentRepository = documentRepository;
        this.groupRepository = groupRepository;
        this.metadataRepository = metadataRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {
        /* Retrieve the created group */
        DocGroup group = init();

        LOGGER.info("[BEFORE cloning] Original group metadata: {}", group.getMetadata()
          .stream()
          .map(Metadata::getName)
          .collect(Collectors.joining(",")));
        LOGGER.info("[BEFORE cloning] Original group documents: {}", group.getDocuments()
          .stream()
          .map(DocumentAssociation::getDocument)
          .map(Document::getName)
          .collect(Collectors.joining(",")));

        DocGroup cloned = cloneGroup(group);
        DocGroup original = groupRepository.findOne(group.getId());


        LOGGER.info("[AFTER cloning] Original group metadata: {}", original.getMetadata()
          .stream()
          .map(Metadata::getName)
          .collect(Collectors.joining(",")));
        LOGGER.info("[AFTER cloning] Original group documents: {}", original.getDocuments()
          .stream()
          .map(DocumentAssociation::getDocument)
          .map(Document::getName)
          .collect(Collectors.joining(",")));

        LOGGER.info("[AFTER cloning] Cloned group metadata: {}", cloned.getMetadata()
          .stream()
          .map(Metadata::getName)
          .collect(Collectors.joining(",")));
        LOGGER.info("[AFTER cloning] Cloned group documents: {}", cloned.getDocuments()
          .stream()
          .map(DocumentAssociation::getDocument)
          .map(Document::getName)
          .collect(Collectors.joining(",")));

        try {
            deleteDocuments(group.getId());
        } catch (StackOverflowError ex) {
            LOGGER.error("StackOverflowError, WTF?", ex);
        }
    }
    /**
     * Create initial metadata, documents and group.
     *
     * @return the created {@link DocGroup}
     */
    @Transactional
    public DocGroup init() {
        Metadata metadata1 = new Metadata("meta1", "description1");
        Metadata metadata2 = new Metadata("meta2", "description2");
        List<Metadata> metadata = metadataRepository.save(Arrays.asList(metadata1, metadata2));

        Document document1 = new Document("document1", "document1", null);
        Document document2 = new Document("document2", "document2", null);
        List<Document> documents = documentRepository.save(Arrays.asList(document1, document2));

        DocGroup group = new DocGroup("group", new HashSet<>(metadata));

        DocumentAssociation association1 =
          new DocumentAssociation(new DocumentAssociationPk(documents.get(0), group), false, 1);
        DocumentAssociation association2 =
          new DocumentAssociation(new DocumentAssociationPk(documents.get(1), group), true, 2);

        group.setDocuments(new HashSet<>(Arrays.asList(association1, association2)));

        return groupRepository.save(group);
    }

    /**
     * Clone a group.
     *
     * @param group the group to clone
     * @return the cloned group
     */
    @Transactional
    public DocGroup cloneGroup(DocGroup group) {
        DocGroup copy = (DocGroup) SerializationHelper.clone(group);
        copy.setId(null);
        copy.setName("Cloned group");
        return groupRepository.save(copy);
    }

    @Transactional
    public void deleteDocuments(Integer id) {
        DocGroup group = groupRepository.findOne(id);
        group.getDocuments().clear();
        groupRepository.save(group);
    }
}
