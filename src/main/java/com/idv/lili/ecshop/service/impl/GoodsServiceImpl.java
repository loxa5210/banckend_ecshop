package com.idv.lili.ecshop.service.impl;

import com.idv.lili.ecshop.constant.ReturnMessageConstant;
import com.idv.lili.ecshop.entity.Goods;
import com.idv.lili.ecshop.model.request.GoodsRequest;
import com.idv.lili.ecshop.model.request.QueryInfoRequest;
import com.idv.lili.ecshop.repository.GoodsRepository;
import com.idv.lili.ecshop.service.GoodsService;
import com.idv.lili.ecshop.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsRepository goodsRepository;

	@Autowired
	private UsersService usersService;

	@Override
	public Goods addGoods(GoodsRequest goodsRequest) {
		Goods goods = new Goods();

		BeanUtils.copyProperties(goodsRequest, goods);

		goods.setCreator(usersService.getCurrentUserInfo().getEmail());
		goods.setModifier(usersService.getCurrentUserInfo().getEmail());

		return goodsRepository.save(goods);
	}

	@Override
	public Goods updateGoods(long GoodsId, GoodsRequest goodsRequest) {
		Optional<Goods> GoodsOptional = goodsRepository.findById(GoodsId);

		if (GoodsOptional.isPresent()) {
			Goods goods = new Goods();
			BeanUtils.copyProperties(goodsRequest, goods);
			goods.setModifier(usersService.getCurrentUserInfo().getEmail());
			goods.setGoodsId(GoodsId);
			goods.setCreateDate(GoodsOptional.get().getCreateDate());
			goods.setCreator(GoodsOptional.get().getCreator());
			return goodsRepository.save(goods);
		}
		throw new NullPointerException(ReturnMessageConstant.GOODS_NOTFOUND);
	}

	@Override
	public Goods getGoods(long GoodsId) {
		Optional<Goods> GoodsOptional = goodsRepository.findById(GoodsId);
		if (GoodsOptional.isPresent()) {
			return GoodsOptional.get();

		}
		throw new NullPointerException(ReturnMessageConstant.GOODS_NOTFOUND);
	}

	@Override public Page<Goods> getGoodsList(QueryInfoRequest queryInfoRequest) {
		PageRequest pageRequest = PageRequest.of(queryInfoRequest.getPageNum() - 1
			, queryInfoRequest.getPageSize(), Sort.by("createDate").ascending());

		if (StringUtils.isBlank(queryInfoRequest.getQuery())) {
			return goodsRepository.findAll(pageRequest);

		} else {
			return goodsRepository.findByNameLike(queryInfoRequest.getQuery(), pageRequest);

		}

	}

	@Override public Boolean existsByCategoryId(Long categoryId) {
		return goodsRepository.existsByCategoryId(categoryId);
	}

}
