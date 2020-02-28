package com.mock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mock.model.Item;
import com.mock.service.ItemService;

/**
 * @author hemalakshumi.v
 *
 */
@RestController
public class ItemController {

	@Autowired
	ItemService itemService;
	
	
//	@GetMapping("/user-login")
//	public ModelAndView showItems() {
//		List<Item> itemList = itemService.getAllItems();
//		return new ModelAndView("userhome", "itemList", itemList);
//	}
//
//	@GetMapping("/admin-login")
//	public ModelAndView showItemsWithEditOption() {
//		List<Item> itemList = itemService.getAllItems();
//		return new ModelAndView("adminhome", "itemList", itemList);
//	}

	@GetMapping("/item-search")
	public ModelAndView itemSearch(@RequestParam("itemName") String itemName) {
		ModelAndView modelView = new ModelAndView();
		List<Item> itemList = itemService.searchItems(itemName);
		if (itemList.size() > 0){
			modelView.addObject( "itemList", itemList);
			modelView.setViewName("userhome");
		}
		else{
			modelView.setViewName("userhome");
			modelView.addObject("message", "No items found for the search");
		}
		return modelView;
	}

	@GetMapping("/add-item")
	public ModelAndView getItemForm(ModelAndView model) {
		Item item = new Item();
		model.addObject("item", item);
		model.setViewName("itemForm");
		return model;
	}

	@PostMapping("/itemInsertion")
	public ModelAndView saveItem(@Validated @ModelAttribute("item") Item item, BindingResult result) {
		if (!result.hasErrors()) {
			if (item.getItemId() == null) {
				itemService.addNewItem(item);
			} else {
				itemService.updateItem(item);
			}
			List<Item> itemList = itemService.getAllItems();
			return new ModelAndView("adminhome", "itemList", itemList);
		} else {
			return new ModelAndView("itemForm");
		}
	}

	@GetMapping("/itemEdit/{itemId}")
	public ModelAndView editItemForm(@PathVariable Long itemId) {
		Item item = itemService.searchByItemId(itemId);
		return new ModelAndView("itemForm", "item", item);
	}
}
