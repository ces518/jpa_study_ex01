package hello;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 23/12/2019
 * Time: 11:57 오후
 **/
@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
