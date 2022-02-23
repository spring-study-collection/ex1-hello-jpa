package hellojpa;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
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

            Member member1 = new Member();
            member1.setUsername("hello");
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello");
            em.persist(member2);

            em.flush();
            em.clear();

            //6. 프록시 초기화 관련 메서드
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //프록시
//
//            Hibernate.initialize(refMember); //강제 초기화
//            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember)); //초기화 여부 확인


            //5. 영속성 컨텍스트의 도움을 받을 수 없는 상태일 때, 프록시를 초기화하면 문제 발생
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //프록시
//
//            em.detach(refMember); //준영속
//            em.close();
//            em.clear();
//
//            refMember.getUsername();

            //4. 프록시로 조회되었으면 이후에 em.find()를 호출해도 프록시로 객체를 반환
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //프록시
//
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //프록시
//
//            System.out.println("a == a: " + (refMember == findMember));

            //3. 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 프록시 객체가 아닌 실제 엔티티 반환
//            Member findMember = em.find(Member.class, member1.getId());
//            System.out.println("findMember = " + findMember.getClass()); //Member
//
//            Member refMember = em.getReference(Member.class, member1.getId());
//            System.out.println("refMember = " + refMember.getClass()); //Member
//
//            System.out.println("a == a: " + (findMember == refMember));

            //2. 타입 비교 (== 말고 instance of 사용)
//            Member m1 = em.find(Member.class, member1.getId()); //DB를 통해서 실제 엔티티 객체 조회
//            Member m2 = em.getReference(Member.class, member2.getId()); //프록시 객체 조회
//
//            logic(m1, m2); //타입 비교

            //1. 프록시 객체 조회
//            Member findMember = em.getReference(Member.class, member1.getId());
//            System.out.println("before findMember = " + findMember.getClass()); //프록시
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getUsername());
//            System.out.println("findMember.username = " + findMember.getUsername());
//            System.out.println("after findMember = " + findMember.getClass()); //프록시



            //DB에 SQL 쿼리를 보내고 커밋
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member m1, Member m2) {
        //System.out.println("m1 == m2: " + (m1.getClass() == m2.getClass()));
        System.out.println("m1 == m2: " + (m1 instanceof Member));
        System.out.println("m1 == m2: " + (m2 instanceof Member));
    }
}
