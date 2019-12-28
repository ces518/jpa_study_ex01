package hello;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 12/12/2019
 * Time: 10:20 오후
 **/
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;
    // 멤버의 입장에서 Many , Team의 입장에서 one 이기 때문에 ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
//    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> products = new ArrayList<>();

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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Locker getLocker() {
        return locker;
    }

    public void setLocker(Locker locker) {
        this.locker = locker;
    }

    /**
     * 연관관계 편의 메소드
     * @param team
     */
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
