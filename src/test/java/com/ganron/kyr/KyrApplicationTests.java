package com.ganron.kyr;

import com.ganron.kyr.model.primary.PrimaryUser;
import com.ganron.kyr.model.secondary.SecondaryUser;
import com.ganron.kyr.repository.primary.PrimaryUserRepository;
import com.ganron.kyr.repository.secondary.SecondaryUserRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class KyrApplicationTests {

    @Autowired
    private PrimaryUserRepository primaryUserRepository;
    @Autowired
    private SecondaryUserRepository secondaryUserRepository;
    private static Logger logger = LoggerFactory.getLogger(KyrApplicationTests.class);

    /**
     * 多数据源保存测试
     */
    @Test
    public void saveTest() {
        primaryUserRepository.save(new PrimaryUser(null, "aaa", "123456", "aaa", 20, "aaa@jpa.com", "13598766131"));
        primaryUserRepository.save(new PrimaryUser(null, "bbb", "123123", "bbb", 22, "bbb@jpa.com", "13659836782"));
        primaryUserRepository.save(new PrimaryUser(null, "ccc", "111111", "ccc", 25, "ccc@jpa.com", "18965233695"));
        secondaryUserRepository.save(new SecondaryUser(null, "ddd", "2356890", "ccc", 18, "ddd@jpa.com", "13678922986"));
        secondaryUserRepository.save(new SecondaryUser(null, "eee", "124678", "bbb", 25, "eee@jpa.com", "13669876321"));
        secondaryUserRepository.save(new SecondaryUser(null, "fff", "112233567", "aaa", 22, "fff@jpa.com", "19862398732"));
    }

    /**
     * 多数据源查询测试
     */
    @Test
    public void findTest() {
        logger.info("查询主库user表测试开始");
        List<PrimaryUser> primaryUsers = primaryUserRepository.findAll();
        if (primaryUsers.isEmpty()) {
            logger.info("主库user表不存在数据");
        } else {
            primaryUsers.forEach(primaryUser -> logger.info(primaryUser.toString()));
        }
        logger.info("查询主库user表测试结束");
        logger.info("查询从库user表测试开始");
        List<SecondaryUser> secondaryUsers = secondaryUserRepository.findAll();
        if (secondaryUsers.isEmpty()) {
            logger.info("从库user表不存在数据");
        } else {
            secondaryUsers.forEach(secondaryUser -> logger.info(secondaryUser.toString()));
        }
        logger.info("查询从库user表测试结束");
    }

    /**
     * 多数据源更新测试
     */
    @Test
    public void updateTest() {
        Optional<PrimaryUser> userOptional = primaryUserRepository.findById(1L);
        if (!userOptional.isPresent()) {
            logger.info("用户不存在");
        } else {
            PrimaryUser user = userOptional.get();
            user.setUserName("ddd");
            user.setNickName("ddd");
            user.setEmail("ddd@jpa.com");
            PrimaryUser primaryUser =
            primaryUserRepository.save(user);
        }
        Optional<SecondaryUser> userOptional1 = secondaryUserRepository.findById(1L);
        if (!userOptional1.isPresent()) {
            logger.info("用户记录不存在");
        } else {
            PrimaryUser user1 = userOptional.get();
            user1.setUserName("aaa");
            user1.setNickName("aaa");
            user1.setEmail("ddd@jpa.com");
            PrimaryUser primaryUser = primaryUserRepository.save(user1);
        }
    }

    /**
     * 多数据源删除测试
     */
    @Test
    public void deleteTest() {
        //primaryUserRepository.deleteById(1L);
        //secondaryUserRepository.deleteById(3L);
        primaryUserRepository.deleteAllInBatch();
        secondaryUserRepository.deleteAllInBatch();
    }

}
