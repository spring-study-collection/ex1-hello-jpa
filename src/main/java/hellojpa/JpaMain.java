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

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setTeam(teamB);
            em.persist(member2);

            em.flush();
            em.clear();

            //2. 즉시로딩은 JPQL에서 N+1 문제를 일으킴
//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();
            //SQL: select * from Member
            //SQL: select * from Team where TEAM_ID = xxx -> team, teamB 2번 실행

            //1. LAZY를 사용해서 프록시로 조회
//            Member m = em.find(Member.class, member1.getId());
//
//            System.out.println("m = " + m.getTeam().getClass()); //proxy
//
//            System.out.println("====================");
//            m.getTeam().getName(); //프록시 객체 초기화, SQL 쿼리 실행
//            System.out.println("====================");

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

}
