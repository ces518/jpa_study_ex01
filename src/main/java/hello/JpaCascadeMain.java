package hello;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 29/12/2019
 * Time: 11:10 오후
 **/
public class JpaCascadeMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // CASCADE 옵션을 지정하면 Parent를 저장할때 연관관계를 맺고 있는 대상에게도 영속성 상태를 전이한다.
            Parent parent = new Parent();
            parent.setName("PARENT");

            Child child = new Child();
            child.setName("child1");
            parent.addChild(child);

            child = new Child();
            child.setName("child2");
            parent.addChild(child);

            child = new Child();
            child.setName("child3");
            parent.addChild(child);

            em.persist(parent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        emf.close();
    }
}
