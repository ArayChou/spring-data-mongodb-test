package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class QueryDslTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    private String fatherId;
    private String motherId;

    @Autowired
    private KidRepository repository;

    public void test() {

        // @DBRef annotated field works
        repository.findAll(QKid.kid.father.id.eq(this.fatherId)).forEach(System.out::println);

        //TODO To query @DocumentReference annotated field Kid.mother won't work
        repository.findAll(QKid.kid.mother.id.eq(this.motherId)).forEach(System.out::println);
    }

    @PostConstruct
    public void initializeData() {
        Father f = new Father();
        f.setName("Father");

        Mother m = new Mother();
        m.setName("Mother");

        Kid p = new Kid();
        p.setName("Patrick Star");

        Kid s = new Kid();
        s.setName("SpongeBob");

        f.setKid(new ArrayList<>(2));
        f.getKid().add(p);
        f.getKid().add(s);

        m.setKid(new ArrayList<>(2));
        m.getKid().add(p);
        m.getKid().add(s);

        p.setFather(f);
        p.setMother(m);

        s.setFather(f);
        s.setMother(m);

        mongoTemplate.save(f);
        mongoTemplate.save(m);
        mongoTemplate.save(p);
        mongoTemplate.save(s);

        this.fatherId = f.getId();
        this.motherId = m.getId();
    }
}
