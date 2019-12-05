package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 28/11/2019
 * Time: 4:57 오후
 **/
public class JpaMain {

    public static void main(String[] args) {
        // Persistence클래스가 EntityManagerFactory를 생성할때 unitName을 인자로 받는다.
        // Factory를 생성하는 순간 데이터베이스와의 연결도 완료된다.
        // 애플리케이션 로딩 시점에 딱 하나만 생성해야한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");


        // EntityManagerFactory로 부터 EntityManager를 생성함
        // 한 트랙잭션 단위마다 entityManager를 생성해주어야한다.
        EntityManager entityManager = emf.createEntityManager();


        // JPA 에서 모든 데이터 변경작업은 반드시 트랜잭션 내에서 해야한다.
        EntityTransaction tx = entityManager.getTransaction();
        // 트랜잭션 시작
        tx.begin();

        // DB 작업
        try {
            // 등록하기

            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("HELLO NAME2");
            entityManager.persist(member);
            */

            // 조회하기
            Member member = entityManager.find(Member.class, 1L);
            System.out.println(member.getId() + " " + member.getName());

            // 삭제하기
            /*
            entityManager.remove(member);
            */

            // 수정하기
            // update 쿼리가 자동적으로 날아간다
            member.setName("HELLO UPDATE");


            // JPQL 을 사용하여 쿼리를 생성해 사용할 수 있다.
            // Member 객체를 대상으로 쿼리를 한다.
            // 대상이 테이블이 아니라 객체이다.
            // 각 벤더의 방언에 맞춰 변형을 해준다.
            List<Member> findMembers = entityManager.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1) // 페이징에 용이하다.
                    .setMaxResults(5)
                    .getResultList();

            for (Member findMember : findMembers) {
                System.out.println(findMember.getName());
            }



            // 비영속 상태
            Member newMember = new Member();
            newMember.setId(100L);
            newMember.setName("HelloJPA");

            // 영속 상태
            // 영속성 컨텍스트를 통해 newMember 객체가 관리하는 상태
            // 이 시점에 저장 되지 않는다.
            entityManager.persist(newMember);


            // 회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
            entityManager.detach(member);

            // 객체를 삭제한 상태 (삭제)
            entityManager.remove(member);


            // 1차 캐시에서 조회
            Member findMember = entityManager.find(Member.class, 100L);
            findMember.setName("HELLOJPA2");
            // 커밋하는 시점에 변경 감지로 인해 업데이트가 된다.


            // 트랜잭션 종료
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            // 자원 종료
            // 내부적으로 DB 커넥션을 가지고 있기 때문에 반드시 닫아주어야 한다.
            entityManager.clear();
        }

        // 애플리케이션이 종료될때 EntityManagerFactory를 종료해야한다.
        emf.close();
    }
}
