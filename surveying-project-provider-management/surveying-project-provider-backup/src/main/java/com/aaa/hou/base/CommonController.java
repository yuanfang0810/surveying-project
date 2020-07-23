package com.aaa.hou.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/7/8 11:38
 * @Description
 **/
public abstract class CommonController<T> extends BaseController {

    /**
     * @author Seven Lee
     * @description
     *      钩子函数
     *      在新增之前去执行某些操作
     *
     *      下单操作:
     *          需求
     *              在购物车中当点击立即下单按钮的时候--->跳转下单页面(选择地址，选择优惠券...)
     *              把购物车中的这个商品删除
     *              deleteCart(List<Integer> id);--->是优先于insertOrder前置执行
     *
     *          insertOrder(Order oder);
     *
     * @param [map]
     * @date 2020/7/9
     * @return void
     * @throws
    **/
    protected void beforeAdd(Map map) {
        // TODO AddMethod Before to do something
    }

    /**
     * @author Seven Lee
     * @description
     *      钩子函数
     *      是在新增之后去执行
     *
     *      int result = insertOrder(Order order)
     *      if(result > 0) {
     *          insertOrderDetail(OrderDetail orderDetail);
     *      }
     *
     * @param [map]
     * @date 2020/7/9
     * @return void
     * @throws
    **/
    protected void afterAdd(Map map) {
        // TODO AddMethod After to do something
    }

    public abstract BaseService<T> getBaseService();

    /**
     * @author Seven Lee
     * @description
     *      通用的新增方法
     *      因为咱们目前市面上所有的公司实现的全是异步了
     *      也就是说前端向后端所传递的数据都是json格式
     *      之前在controller的方法中接收固定的实体类，是因为你知道前端给你传递的类型就是这个实体类
     *      但是既然要做通用，前端所传递的类型就不会固定了--->所以使用Map类型来统一接收
     * @param [map]
     * @date 2020/7/9
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    public ResultData add(@RequestBody Map map) {
        // 因为根据咱们的封装规则，在service中是需要传递泛型的，就意味着service需要接收固定的实体类
        // 但是controller是一个Map类型
        beforeAdd(map);
        // 1.Map转实体类
        T instance = getBaseService().newInstance(map);
        // 2.通用service
        Integer addResult = getBaseService().add(instance);
        if(addResult > 0) {
            afterAdd(map);
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @author Seven Lee
     * @description
     *      删除操作
     * @param [map]
     * @date 2020/7/9
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    public ResultData delete(@RequestBody Map map) {
        T instance = getBaseService().newInstance(map);
        Integer deleteResult = getBaseService().delete(instance);
        if(deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @author Seven Lee
     * @description
     *      批量删除
     * @param [ids]
     * @date 2020/7/9
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    public ResultData batchDelete(@RequestParam("ids[]") Integer[] ids) {
        Integer deleteResult = getBaseService().deleteByIds(Arrays.asList(ids));
        if(deleteResult > 0) {
            return super.operationSuccess();
        }
        return super.operationFailed();
    }

    /**
     * @author Seven Lee
     * @description
     *      更新操作
     * @param [map]
     * @date 2020/3/11
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
     **/
    public ResultData update(@RequestBody Map map){
        T t = getBaseService().newInstance(map);
        int updateResult = getBaseService().update(t);
        if(updateResult > 0){
            return  operationSuccess();
        }
        return operationFailed();
    }

    /**
     * @author Seven Lee
     * @description
     *      查询一条数据
     * @param [map]
     * @date 2020/3/11
     * @return java.lang.Object
     * @throws
     **/
    public ResultData getOne(@RequestBody Map map) {
        T t = getBaseService().newInstance(map);
        t = getBaseService().selectOne(t);
        if (null != t) {
            return operationSuccess(t);
        }
        return operationFailed();
    }

    /**
     * @author Seven Lee
     * @description
     *      条件查询多条结果
     * @param [map]
     * @date 2020/3/11
     * @return com.aaa.lee.repast.base.ResultData
     * @throws
     **/
    public ResultData getList(@RequestBody Map map){
        T t = getBaseService().newInstance(map);
        List<T> resultT = getBaseService().selectList(t);
        if(resultT.size() > 0) {
            return operationSuccess(resultT);
        }
        return operationFailed("未找到查询结果");
    }

    /**
     * @author Seven Lee
     * @description
     *      带条件的分页查询
     * @param [map, pageNo, pageSize]
     * @date 2020/3/11
     * @return java.lang.Object
     * @throws
     **/
    public ResultData getListByPage(@RequestBody Map map, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        T t = getBaseService().newInstance(map);
        PageInfo<T> pageInfo = getBaseService().selectListByPage(t,pageNo,pageSize);
        List<T> resultList = pageInfo.getList();
        if(resultList.size() > 0) {
            return operationSuccess(pageInfo);
        }
        return operationFailed("未找到查询结果");
    }

    /**
     * @author Seven Lee
     * @description
     *      不带条件的分页查询
     * @param [pageNo, pageSize]
     * @date 2020/3/11
     * @return java.lang.Object
     * @throws
     **/
    public ResultData getListByPage( @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        PageInfo<T> pageInfo =getBaseService().selectListByPage(null,pageNo,pageSize);
        List<T> resultList = pageInfo.getList();
        if(resultList.size() > 0) {
            return operationSuccess(pageInfo);
        }
        return operationFailed("未找到查询结果");
    }

    /**
     * @author Seven Lee
     * @description
     *      从本地当前线程中获取request对象
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpServletRequest
     * @throws
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if (requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当前客户端session对象(如果没有则创建一个新的session)
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpSession
     * @throws
     **/
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @author Seven Lee
     * @description
     *      获取当前客户端session对象(如果没有则直接返回null)
     * @param []
     * @date 2020/3/11
     * @return javax.servlet.http.HttpSession
     * @throws
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);
    }

}
