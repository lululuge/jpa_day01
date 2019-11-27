package com.luge.test;

import com.luge.domain.Customer;
import com.luge.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {
    /**
     * 保存功能
     */
    @Test
    public void testSave() {
//        // 1.加载配置文件，创建实体管理器工厂
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
//        // 2.通过实体管理器工厂获取实体管理器
//        EntityManager em = factory.createEntityManager();
        EntityManager em = JpaUtils.getEntityManager();
        // 3.获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        // 4.完成crud操作
        Customer customer = new Customer();
        customer.setCustName("腾讯");
        customer.setCustAddress("中国深圳");
        customer.setCustPhone("1000000");
        customer.setCustIndustry("互联网");
        em.persist(customer);
        // 5.提交事务
        tx.commit();
        // 6.释放资源
        em.close();
//        factory.close();
    }

    /**
     * 根据id查询客户
     */
    @Test
    public void testFind() {
        EntityManager em = JpaUtils.getEntityManager();
        Customer customer = em.find(Customer.class, 2l);
        System.out.println(customer);
        em.close();
    }

    /**
     * 删除客户
     */
    @Test
    public void testRemove() {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.getReference(Customer.class, 5l);
        em.remove(customer);
        tx.commit();
        em.close();
    }
}
