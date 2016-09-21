package com.mfg.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mfg.enty.Good;

@Repository
public interface GoodDao extends PagingAndSortingRepository<Good, Integer> {
	public Good findById(Integer id);
    public Good findByName(String name);
}
