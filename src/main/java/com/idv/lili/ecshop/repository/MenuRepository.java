package com.idv.lili.ecshop.repository;

import com.idv.lili.ecshop.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
	/**
	 * 找出母菜單
	 * @return List<Menu>
	 */
	List<Menu> findByIsChildIsFalse();
	/**
	 * 找出子菜單
	 * @return List<Menu>
	 */
	List<Menu> findByIsChildIsTrue();

}
