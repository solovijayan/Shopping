package com.mock.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mock.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long> {

	List<Item> findByItemNameContains(String itemName);


	
}
