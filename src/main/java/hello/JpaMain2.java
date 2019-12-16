package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 15/12/2019
 * Time: 9:04 오후
 **/
public class JpaMain2 {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 테이블에 맞춰 설계시 문제점 */
            Team team = new Team();
            team.setName("TEAM1");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // 영속 상태가 되면 무조건 ID값이 생성됨
            // 하지만 객체지향 스러운 코드가 아니다.
            // 외래키 식별자를 직접 다룬다..
//            member.setTeamId(team.getId());
            /**
             * 연관관계 매핑후
             * teamId가 아닌 team의 참조를 연결한다.
             * */
            member.setTeam(team);

            em.persist(member);


            // 조회시에도 이슈가 있음
            Member findMember = em.find(Member.class, member.getId());
            // 소속된 팀을 알고싶을떄 ??
//            Long teamId = findMember.getTeamId();
            // teamId를 기반으로 조회해야함
//            Team findTeam = em.find(Team.class, teamId);
            /**
             * 연관관계 매핑후
             * 바로 꺼내어 사용할 수 있다.
             */
            Team findMemberTeam = findMember.getTeam();
            List<Member> members = findMemberTeam.getMembers();
            members.forEach(member1 -> System.out.println(member1.getUsername()));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        emf.close();
    }
}
