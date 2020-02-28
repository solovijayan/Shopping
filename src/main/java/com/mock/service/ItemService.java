package com.mock.service;

import java.util.List;
import com.mock.model.Item;

public interface ItemService {

	/**
	 * This method is used to Get all items
	 * 
	 * @return itemList
	 */
	List<Item> getAllItems();

	/**
	 * This method is used to get all items details based on the given item name
	 * 
	 * @param itemName
	 * @return
	 */
	List<Item> searchItems(String itemName);

	/**
	 * This method is to search a particular item
	 * 
	 * @param itemId
	 * @return
	 */
	Item searchByItemId(Long itemId);

	/**
	 * This method is to search a particular item and edit the existing values
	 * 
	 * @param item
	 */
	void updateItem(Item item);

	/**
	 * 
	 * @param item
	 */

	void addNewItem(Item item);
}
