package hello;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 25/12/2019
 * Time: 11:06 오후
 **/
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) // 기본 전략은 단일테이블 전략
// JOINED 는 조인테이블 전략 모델로 생성
@DiscriminatorColumn // DTYPE
public abstract class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
