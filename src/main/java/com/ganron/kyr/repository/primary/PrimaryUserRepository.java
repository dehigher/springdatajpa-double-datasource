package com.ganron.kyr.repository.primary;

import com.ganron.kyr.model.primary.PrimaryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description PrimaryUserRepository接口用于操作主库用户表
 * @date 2020/1/7 19:34
 */
public interface PrimaryUserRepository extends JpaRepository<PrimaryUser, Long>, JpaSpecificationExecutor<PrimaryUser> {
}

