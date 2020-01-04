package hello;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 01/01/2020
 * Time: 11:00 오후
 **/
@Entity
public class SampleMember {

    @Id @GeneratedValue
    @Column(name = "SAMPLE_MEMBER_ID")
    private Long id;

    private String username;

    // @Embeddable 과 @Embedded 를 하나를 생략할 수 있지만 둘다 명시하는것을 추천한다.
    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    /* 값 타입을 매핑하는 애노테이션 */
    @ElementCollection
    /* 값 타입을 저장할 테이블을 매핑하는 애노테이션 */
    @CollectionTable(name = "FAVORITE_FOOD",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") // 값 타입을 저장할 컬럼명을 변경하고 싶을때 예외적으로 사용할 수 있다. (값이 하나이기떄문에 가능함, 임베디드 타입에서는 불가능)
    private Set<String> favoriteFoods = new HashSet<>();

    /*
    // 값타입 매핑
    @ElementCollection
    @CollectionTable(name = "ADDRESS",
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    private List<Address> addressHistories = new ArrayList<>();
    */

    // 값타입 엔티티 매핑
    // 일대다 단방향 매핑을 사용한다.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressHistories = new ArrayList<>();

    /*
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "WORK_CITY")),
            @AttributeOverride(name = "street", column = @Column(name = "WORK_STREET")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "WORK_ZIPCODE"))
    })
    private Address workAddress;
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressEntity> getAddressHistories() {
        return addressHistories;
    }

    public void setAddressHistories(List<AddressEntity> addressHistories) {
        this.addressHistories = addressHistories;
    }
}
