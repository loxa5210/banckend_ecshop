package com.idv.lili.ecshop.repository;

import com.idv.lili.ecshop.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {

	Page<Goods> findByNameLike(String name, PageRequest pageRequest);

	Boolean existsByCategoryId(Long categoryId);
}
