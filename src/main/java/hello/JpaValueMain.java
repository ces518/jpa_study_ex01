package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 02/01/2020
 * Time: 11:36 오후
 **/
public class JpaValueMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 1. 값 타입은 엔티티간에 공유가 된다. */
            Address address = new Address("city", "street", "10000");
            SampleMember member = new SampleMember();
            member.setUsername("Member1");
            member.setHomeAddress(address);
            em.persist(member);

            /* 5. 사이드 이펙트를 방지하기 위해 값타입은 copy해서 사용해야 한다.*/
            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipCode());

            SampleMember member2 = new SampleMember();
            member2.setUsername("Member2");
            member2.setHomeAddress(copyAddress);
            em.persist(member2);

            // 2. 첫번째 회원의 City만 NewCity로 변경하고자 한다.
            // member.getHomeAddress().setCity("NewCity");
            // 3. 하지만 업데이트 쿼리가 2번 나간다.
            // 4. 참조를 공유하기 때문에 2번째 회원까지 수정이 된다..

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        emf.close();
    }
}
