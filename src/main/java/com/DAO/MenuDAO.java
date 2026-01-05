package com.DAO;

import java.util.List;

import com.Model.Menu;

public interface MenuDAO {

    void addMenu(Menu m);           
    Menu getMenu(int menuId);       
    void updateMenu(Menu m);
    void deleteMenu(int menuId);
    List<Menu> getAllMenus();
    List<Menu> getAllMenusByRestaurant(int restaurantId);

}
