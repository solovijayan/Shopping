package com.mock.Shopping;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import com.mock.controller.ItemController;
import com.mock.model.Item;
import com.mock.repository.ItemRepository;
import com.mock.service.ItemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	ItemController itemController;
	ItemServiceImpl itemService;
	@Mock
	ItemRepository itemRepository;
	Field field;
	private List<Item> list;
	private List<Item> list1;
	private Item item;
	@Mock
	private BindingResult result;

	@Before
	public void setUp() throws Exception {
		itemController = new ItemController();
		itemService = new ItemServiceImpl();
		field = ItemController.class.getDeclaredField("itemService");
		field.setAccessible(true);
		field.set(itemController, itemService);
		itemService.itemRepository = itemRepository;
		list = new ArrayList<Item>();
		list1 = new ArrayList<Item>();
		item = new Item();
		list.add(item);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testItemSearch_if() {
		Mockito.when(itemRepository.findByItemNameContains("itemName")).thenReturn(list);
		assertEquals("userhome", itemController.itemSearch("itemName").getViewName());
	}

	@Test
	public void testItemSearch_else() {
		Mockito.when(itemRepository.findByItemNameContains("itemName")).thenReturn(list1);
		assertEquals("userhome", itemController.itemSearch("itemName").getViewName());
	}

	@Test
	public void testGetItemForm() {
		ModelAndView model = new ModelAndView();
		assertEquals("itemForm", itemController.getItemForm(model).getViewName());
	}

	@Test
	public void testSaveItem_if() {
		Mockito.when(result.hasErrors()).thenReturn(false);
		item.setItemId(null);
		assertEquals("adminhome", itemController.saveItem(item, result).getViewName());
	}

	@Test
	public void testSaveItem_else() {

		Mockito.when(result.hasErrors()).thenReturn(false);
		item.setItemId(1L);
		assertEquals("adminhome", itemController.saveItem(item, result).getViewName());
	}

	@Test
	public void testSaveItem_hasErrors() {
		Mockito.when(result.hasErrors()).thenReturn(true);
		assertEquals("itemForm", itemController.saveItem(item, result).getViewName());
	}

	@Test
	public void testEditItemForm() {
		item.setItemDescription("hello");
		item.setItemId(new Long(10));
		item.setItemName("hello");
		Mockito.when(itemRepository.findOne(Matchers.anyLong())).thenReturn(item);
		assertEquals("itemForm", itemController.editItemForm(1l).getViewName());
	}

}
