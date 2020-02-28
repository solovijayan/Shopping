package com.mock.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mock.controller.UserController;
import com.mock.exception.ItemNotFoundException;
import com.mock.model.Item;
import com.mock.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
	private static final Logger log4j = LogManager.getLogger(UserController.class);

	@Autowired
	ItemRepository itemRepository;

	@Override
	public List<Item> getAllItems() {
		List<Item> itemList = new ArrayList<Item>();
		try {
			itemList = (List<Item>) itemRepository.findAll();
		} catch (Exception e) {
			log4j.debug(e.getMessage());
		}
		return itemList;
	}

	@Override
	public List<Item> searchItems(String itemName) {
		List<Item> itemList = new ArrayList<Item>();
//		try {
			itemList = (List<Item>) itemRepository.findByItemNameContains(itemName);
			if (itemList.size() == 0) {
//				throw new ItemNotFoundException("Item not found");
			}
//		} catch (Exception e) {
//			log4j.debug(e.getMessage());
//		}
		return itemList;
	}

	@Override
	public Item searchByItemId(Long itemId) {

		Item item = new Item();
//		try {
			item = itemRepository.findOne(itemId);
//		} catch (Exception e) {
//			log4j.debug(e.getMessage());
//		}
			if(item == null){
				throw new ItemNotFoundException("Item not found");
			}
		return item;
	}

	@Override
	public void updateItem(Item item) {
		try {
			if (item.getItemId() > 0) {
				Item itemObj = itemRepository.findOne(item.getItemId());
				if (itemObj != null) {
					itemObj.setItemName(item.getItemName());
					itemObj.setItemDescription(item.getItemDescription());
					itemObj.setItemPrice(item.getItemPrice());
					itemRepository.save(itemObj);
				}
			}
		} catch (Exception e) {
			log4j.debug(e.getMessage());
		}

	}

	@Override
	public void addNewItem(Item item) {
		itemRepository.save(item);

	}
}