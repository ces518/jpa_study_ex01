package hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 28/11/2019
 * Time: 5:18 오후
 **/
@Entity
// USER 라는 테이블에 매핑을 해준다.
// @Table(name = "USER")
public class Member {

    @Id
    private Long id;

    // username 이라는 컬럼과 매핑해준다.
    // @Column(name = "username")
    private String name;

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
