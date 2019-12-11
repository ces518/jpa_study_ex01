package hello;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 28/11/2019
 * Time: 5:18 오후
 **/
@Entity
// USER 라는 테이블에 매핑을 해준다.
@Table(name = "USER")
public class Member {

    @Id
    private Long id;

    // DB에는 name으로 사용한다.
    @Column(name = "name")
    private String name;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    private LocalDate localDate;

    private LocalDateTime localDateTime;
}
