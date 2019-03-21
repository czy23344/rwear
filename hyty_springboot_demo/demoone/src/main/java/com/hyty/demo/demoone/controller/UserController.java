package com.hyty.demo.demoone.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hyty.demo.demoone.entiy.UserEntiy;
import com.hyty.demo.demoone.entiy.UserMinute;
import com.hyty.demo.demoone.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czy on 2019/3/7.
 * usercontroller
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    UserService userService;

    /**
     * 查询所有用户
     * @return json
     */
    @RequestMapping(value = "/selecthead", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Object selecthead() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<UserEntiy> list = userService.selectAll();
            map.put("result", true);
            map.put("value", list);
            map.put("msg", "查询成功");
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "查询失败");
            return JSON.toJSONString(map);
        }
    }
    /**
     * 根据ID 查询表头
     * @return json
     */
    @RequestMapping(value = "/selectid", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public Object selectid(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserEntiy userEntiy = userService.selectuser(id);
            map.put("result", true);
            map.put("value", userEntiy);
            map.put("msg", "查询成功");
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "查询失败");
            return JSON.toJSONString(map);
        }
    }
    /**
     * 查询用户表体
     * @return json
     */
    @RequestMapping(value = "/selectbody", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public Object selectbody(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<UserMinute> list = userService.selectwhereId(id);
            map.put("result", true);
            map.put("value", list);
            map.put("msg", "查询成功");
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "查询失败");
            return JSON.toJSONString(map);
        }
    }

    /**
     * 保存/修改用户
     * @return json
     */
    @RequestMapping(value = "/insertuser", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Object saveuser(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        try {
            UserEntiy userEntiy = JSONObject.parseObject(String.valueOf(jsonObject),UserEntiy.class);
            UserEntiy userEntiy1 = userService.insertuser(userEntiy);
            map.put("result", true);
            map.put("value", userEntiy1);
            map.put("msg", "保存成功");
            return JSON.toJSONString(map);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "保存失败");
            return JSON.toJSONString(map);
        }
    }


    /**
     * 保存用户表体
     * @return json
     */
    @RequestMapping(value = "/insertuserbody", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
    public Object saveuserbody(UserMinute userMinute) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean userminute = userService.insertuserminute(userMinute);
            if(userminute){
                map.put("result", true);
                map.put("msg", "保存成功");
                return JSON.toJSONString(map);
            }else {
                map.put("result", false);
                map.put("msg", "保存失败，检查表头ID是否表内存在");
                return JSON.toJSONString(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "保存失败，异常处理");
            return JSON.toJSONString(map);
        }
    }

    /**
     * 删除用户
     * @return json
     */
    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public Object deleteuser(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean userEntiy = userService.deleteUserEntiy(id);
            if(userEntiy){
                map.put("result", true);
                map.put("msg", "删除成功");
                return JSON.toJSONString(map);
            }else {
                map.put("result", false);
                map.put("msg", "删除失败，检查表头ID是否表内存在");
                return JSON.toJSONString(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "删除失败，异常处理");
            return JSON.toJSONString(map);
        }
    }


    /**
     * 删除用户表体
     * @return json
     */
    @RequestMapping(value = "/deleteuserbody", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public Object deleteuserbody(String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean userminute = userService.deleteUserMinute(id);
            if(userminute){
                map.put("result", true);
                map.put("msg", "删除成功");
                return JSON.toJSONString(map);
            }else {
                map.put("result", false);
                map.put("msg", "删除失败，检查ID是否表内存在");
                return JSON.toJSONString(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", false);
            map.put("msg", "删除失败，异常处理");
            return JSON.toJSONString(map);
        }
    }


}
