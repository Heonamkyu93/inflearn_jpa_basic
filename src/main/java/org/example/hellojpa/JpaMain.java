package org.example.hellojpa;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {     // xml에 있는 설정한 유닛이름
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

     //   Member member = new Member();
     //   member.setId(2L);
     //   member.setName("HelloB");

        try {
     //       entityManager.persist(member);

            Member findMember = entityManager.find(Member.class,1L);
            findMember.setName("HelloJpa");   //수정   아이디로 찾고 값 변경하면 그냥 수정된다 반드시 트랜잭션 안에서 실행
                                    
                                                                        // JPQL 은 엔티티 객체를 대상으로 쿼리를 날린다 테이블이 아님
            List<Member> query = entityManager.createQuery("select m from Member as m", Member.class).getResultList();
            for (Member member : query) {
                System.out.println("member.getName() = " + member.getName());
            }
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
        } finally {
            entityManager.close();
        }
        emf.close();


    }
}
