package hello;

import javassist.tools.rmi.Sample;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

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


            /* 값타입 저장 예제 */
            SampleMember newMember = new SampleMember();
            newMember.setUsername("new member");
            member.setHomeAddress(new Address("city", "street", "1234"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistories().add(new AddressEntity(new Address("city1", "street1", "12341")));
            member.getAddressHistories().add(new AddressEntity(new Address("city2", "street2", "12342")));
            member.getAddressHistories().add(new AddressEntity(new Address("city3", "street3", "12343")));

            // 재밌는점은 값타입 컬렉션 을 persist 하지 않았는데 같이 저장되어 버림
            // 값타입 컬렉션 도 라이프사이클을 엔티티를 따라간다.
            em.persist(member);

            em.flush();
            em.clear();

            /**
             * 값 타입 조회
             */
            // 값 타입 컬렉션들은 기본적으로 지연 로딩이다.
            SampleMember findSampleMember = em.find(SampleMember.class, newMember.getId());

            /*
            List<Address> addressHistories = findSampleMember.getAddressHistories();
            for (Address addressHistory : addressHistories) {
                System.out.println("addressHistory = " + addressHistory);
            }

            Set<String> favoriteFoods = findSampleMember.getFavoriteFoods();
            for (String favoriteFood : favoriteFoods) {
                System.out.println("favoriteFood = " + favoriteFood);
            }
             */


            /**
             * 값 타입 수정
             */

            // 값타입은 불변객체이기 떄문에 부분수정은 불가능하다.
            Address homeAddress = findSampleMember.getHomeAddress();
            // 완전 새로운 값타입으로 생성해야한다.
            Address newAdddress = new Address("newCity", homeAddress.getStreet(), homeAddress.getZipCode());
            findSampleMember.setHomeAddress(newAdddress);

            // 치킨 -> 한식으로 변경하고 싶다.
            // 단순 값타입 이기떄문에 방법이 없다.
            // 기존값을 제거하고, 새롭게 등록을 해야한다.
            // 컬렉션의 값만 변경해도, JPA가 감지하고 SQL이 날아간다.
            findSampleMember.getFavoriteFoods().remove("치킨");
            findSampleMember.getFavoriteFoods().add("한식");

            // 기본적으로 컬렉션들은 대부분 대상을 찾을떄 equals를 찾는다.
            // equals를 제대로 구현해주지 않았다면 찾지 못하기때문에 제거되지 않는다..
            findSampleMember.getAddressHistories().remove(new AddressEntity(new Address("city1", "street1", "12341")));
            findSampleMember.getAddressHistories().add(new AddressEntity(new Address("newCity", "street1", "12341")));

            // * 재미있는점은 기존의 member에 해당하는 데이터를 싹지우고 새로 채워넣는다..
            // -> 기대한 상황은 1개만 삭제되는것을 기대함..

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.clear();
        }
        emf.close();
    }
}
