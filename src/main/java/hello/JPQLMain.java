package hello;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 05/01/2020
 * Time: 9:31 오후
 **/
public class JPQLMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /** JPQL */
            /*

            List<Member> members = em.createQuery("select m from Member m where m.username like '%kim%'",
                    Member.class)
                    .getResultList();

            for (Member member : members) {
                System.out.println("member = " + member);
            }
             */

            /** Criteria */
            /*
            // 자바 표준에서 제공하는 기능이다.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            // 루트클래스 (조회를 시작할 클래스)
            Root<Member> m = query.from(Member.class);

            // 동적쿼리 생성
            CriteriaQuery<Member> cq = query.select(m)
                    .where(cb.equal(m.get("username"), "kim"));

            List<Member> members = em.createQuery(cq).getResultList();
            // 쉬운것 같으면서도 어렵다..

             */

            List members = em.createNativeQuery("select MEMBER_ID, city, street, zipcode from MEMBER").getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        emf.close();
    }
}
