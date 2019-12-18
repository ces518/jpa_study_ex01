package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 15/12/2019
 * Time: 8:59 오후
 **/
@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /**
     * mappedBy: 일대다에서 Member의 team과 연관관계로 매핑이 되어있다.
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>(); // new ArrayList<>(); 로 초기화해 add 시 nullpointer가 나지않도록 하는것이 관례이다

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMember (Member member) {
        member.setTeam(this);
        this.members.add(member);
    }
}
