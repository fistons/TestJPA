package net.pedr0.test.jpa;

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

import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final DocumentRepository documentRepository;
    private final GroupRepository groupRepository;
    private final MetadataRepository metadataRepository;

    @Autowired
    public Application(DocumentRepository documentRepository,
      GroupRepository groupRepository,
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

        LOGGER.info("Well, hello");

        Metadata metadata1 = Metadata.builder().name("meta1").value("value1").build();
        Metadata metadata2 = Metadata.builder().name("meta2").value("value2").build();

        metadataRepository.save(Arrays.asList(metadata1, metadata2));


    }
}
