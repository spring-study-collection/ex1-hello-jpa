package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //EntityManager를 통해 DB 작업
        EntityManager em = emf.createEntityManager();

        //JPA의 모든 데이터 변경은 트랜잭션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //객체를 생성한 상태(비영속)
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");

            //객체를 저장한 상태(영속)
//            em.persist(member1); //아직 DB에 저장된 것이 아님
//            em.persist(member2);

//            em.flush(); //강제로 flush 메커니즘 호출

//            Member findMember1 = em.find(Member.class, 150L);
//            Member findMember2 = em.find(Member.class, 150L);
//            System.out.println("result = " + (findMember1 == findMember2));

            //영속 엔티티 데이터 수정
//            Member findMember = em.find(Member.class, 150L);
//            findMember.setName("ZZZZZ");

            //영속성 컨텍스트에서 분리(준영속)
//            Member findMember = em.find(Member.class, 150L);
//            findMember.setName("AAAAA");

//            em.detach(findMember); //특정 엔티티만 준영속 상태로 전환
//            em.clear(); //영속성 컨텍스트를 완전히 초기화
//            em.close(); //영속성 컨텍스트를 종료

//            Member findMember2 = em.find(Member.class, 150L);

            //객체를 삭제한 상태(삭제)
//            em.remove(member);

            //커밋 후에 DB에 SQL 쿼리를 보냄
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
