package com.ganron.kyr.repository.secondary;

import com.ganron.kyr.model.primary.PrimaryUser;
import com.ganron.kyr.model.secondary.SecondaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description PrimaryUserRepository接口用于操作主库用户表
 * @date 2020/1/7 19:34
 */
public interface SecondaryUserRepository extends JpaRepository<SecondaryUser, Long>, JpaSpecificationExecutor<PrimaryUser> {
}

