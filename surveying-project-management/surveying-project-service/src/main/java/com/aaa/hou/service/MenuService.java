package com.aaa.hou.service;


import com.aaa.hou.base.BaseService;
import com.aaa.hou.mapper.MenuMapper;
import com.aaa.hou.model.Menu;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author guohang
 * @Description
 * @Date 2020/6/3 18:36
 */
@Service
public class MenuService extends BaseService<Menu> {

    @Autowired
    private MenuMapper menuMapper;


    /**
    * @Description: 获取菜单信息
    * @Author: guohang
    * @Date: 2020/6/3 18:43
    * @Param: []
    * @return: java.util.List<com.aaa.qy108.model.Menu>
    */
    public List<Menu> selectAllMenus(){
        //菜单树
        List<Menu> menusList=new ArrayList<Menu>();
        //菜单的全部信息
        List<Menu> allMenusList= menuMapper.selectAll();
        if (null!=allMenusList&&allMenusList.size()>0) {
            //拿到一级菜单信息
            for (int i = 0; i <allMenusList.size() ; i++) {
                Menu menu = allMenusList.get(i);
                if (menu.getParentId()==0) {
                    //说明是一级菜单
                    menusList.add(menu);
                }
            }
            //为一级菜单设置子菜单
            for (Menu menu: menusList) {
                menu.setSubMenu(getSubMenu(menu.getMenuId(),allMenusList));
            }
        }
        return menusList;
    }


    private List<Menu> getSubMenu(Long menuId,List<Menu> allMenus){
        /**
         * @Description:
         *   获取上一级菜单的子菜单
         * @Param: [menuId, allMenus]
         * @Author: PC
         * @Return: java.util.List<com.aaa.hou.model.Menu>
         * @Date: 2020/7/16 21:51
         **/
        //子菜单
        List<Menu> subMenus=new ArrayList<Menu>();
        for (Menu menu:
                allMenus) {
            if (menu.getParentId().equals(menuId)){
                subMenus.add(menu);
            }
        }
        //子菜单的下一级
        //疑问：当递归进入，查找子菜单的子菜单，那么子菜单的数据现在在哪，是在下面循环代码中的subMenus中
        for (Menu menu:
                subMenus) {
            menu.setSubMenu(getSubMenu(menu.getMenuId(),allMenus));
        }
        if (subMenus.size()==0){
            return null;
        }
        return subMenus;
    }


//    public Boolean insertMenuOrButton(Menu menu){
    /**
     * @Description:
     *
     * 新增菜单或者按钮
     * @Param:
     * @Author: PC
     * @Return:
     * @Date: 2020/7/16 21:52
     **/
//        String createTime = DateUtil.now();
//        menu.setCreateTime(createTime);
//        try{
//            Integer add = super.add(menu);
//            if (add > 0){
//                return true;
//            }else {
//                return false;
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }


//    public Boolean updateMenuOrButton(Menu menu){
//        /**
//         * @Description:
//         * 根据主键修改菜单或者按钮的信息
//         * @Param: [menu]
//         * @Author: PC
//         * @Return: java.lang.Boolean
//         * @Date: 2020/7/16 21:53
//         **/
//        String time = DateUtil.now();
//        menu.setModifyTime(time);
//        try {
//            Integer update = super.update(menu);
//            if (update>0){
//                return true;
//            }else {
//                return false;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }


    /**
    * @Description: 通过主键id删除菜单或者按钮
    * @Author: guohang
    * @Date: 2020/6/3 18:42
    * @Param: [menuId]
    * @return: java.lang.Boolean
    */
    public Boolean deleteMenuOrButton(Long menuId){
        int i = menuMapper.deleteByPrimaryKey(menuId);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }




}



