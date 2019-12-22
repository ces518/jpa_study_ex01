package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 22/12/2019
 * Time: 3:33 오후
 **/
@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member member;
}
