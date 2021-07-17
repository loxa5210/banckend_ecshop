package com.idv.lili.ecshop.repository;

import com.idv.lili.ecshop.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	/**
	 * 使用信箱找出某使用者
	 * @param email 信箱
	 * @return Users
	 */
	Users findTopByEmailIs(String email);
	/**
	 *
	 * @param email 信箱
	 * @param username 使用者名稱
	 * @param pageRequest 分頁物件
	 * @return Users
	 */
	Page<Users> findByEmailLikeOrUsernameLike(String email, String username, PageRequest pageRequest);

	/**
	 * 確認該電子郵件是否存在
	 * @param email 信箱
	 * @return Users
	 */
	Users existsByEmail(String email);
}
