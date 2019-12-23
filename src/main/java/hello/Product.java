package hello;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 23/12/2019
 * Time: 11:52 오후
 **/
@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product")
    private List<MemberProduct> members;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
