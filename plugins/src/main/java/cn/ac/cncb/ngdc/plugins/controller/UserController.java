package cn.ac.cncb.ngdc.plugins.controller;
/**
 * Project Name: test-zsx
 * File Name: UserController
 * Package Name: com.zsx.controller
 * Date: 2023/9/13 11:21
 * Copyright (c) 2023 天翼数字生活科技有限公司 All Rights Reserved.
 */

import cn.ac.cncb.ngdc.plugins.mapper.CustomsMapper;
import cn.ac.cncb.ngdc.plugins.pojo.Customs;
import cn.ac.cncb.ngdc.plugins.pojo.CustomsDetail;
import cn.ac.cncb.ngdc.plugins.pojo.User;
import cn.ac.cncb.ngdc.plugins.searvice.IUserService;
//import com.alibaba.fastjson2.JSON;
//import com.zsx.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomsMapper customsMapper;

//    @Autowired
//    private IUserService userService;

//    /**
//     * 插入一条记录
//     * @param user
//     * @return
//     */
//    @PostMapping("/add")
//    public String addUser(@RequestBody User user){
//        userService.addUser(user);
//        return "success";
//    }
//

    @PostMapping(value = "/metaData")
    @ResponseBody
    public Map<String, Object> metaData(@RequestBody Customs customs) {
        Map<String, Object> map = new HashMap<>();
        String acc = customs.getAccession();
        map.put("result","成功");
        if(acc!=null){
            int a = this.customsMapper.selectAccessionCount(acc);
            if(a!=0){
                map.put("result","失败");
                map.put("result message","编号已经存在");
            } else {
                String title = customs.getTitle();
                if(title!=null){

                } else {
                    map.put("result","失败");
                    map.put("result message","Title不为空");
                }
                List<CustomsDetail> customsDetails = customs.getCustomsDetail();
                if(customsDetails!=null){
                    for(CustomsDetail cu:customsDetails){
                        cu.setAccession(acc);
                    }
                } else {
                    map.put("result","失败");
                    map.put("result message","详细内容不为空");
                }
            }
        } else {
            map.put("result","失败");
            map.put("result message","编号不为空");
        }
        if(!map.get("result").equals("失败")){
            int insertCus = this.customsMapper.saveCustoms(customs);
            List<CustomsDetail> customsDetail = customs.getCustomsDetail();
            for(CustomsDetail cu:customsDetail){
                int insertCusDetail = this.customsMapper.saveCustomsDetail(cu);
            }
            map.put("result","成功");
            map.put("result message","");
        }
        return map;
    }
//    /**
//     * 查询一条记录
//     * @param id
//     * @return
//     */
//    @PostMapping("/get/one")
//    public String getUser(Long id){
//        User user = userService.getUserById(id);
//        return user.toString();
//    }
//
//    /**
//     * 查询全部
//     * @return
//     */
//    @PostMapping("/get/list")
//    public String getUserAll(){
//        List<User> users = userService.getAllUser();
//        return JSON.toJSONString(users);
//    }
//
//    /**
//     * 通过手机号查询
//     * @param phone
//     * @return
//     */
//    @PostMapping("/get/phone")
//    public String getUserByPhone(String phone){
//        List<User> users = userService.getUserByPhone(phone);
//        return JSON.toJSONString(users);
//    }
//
//    /**
//     * 通过对象查询
//     * @param phone
//     * @return
//     */
//    @PostMapping("/get/user/phone")
//    public String getUserByUserPhone(String phone){
//        List<User> users = userService.getUser(phone);
//        return JSON.toJSONString(users);
//    }
//
//    /**
//     * 批量插入
//     * @return
//     */
//    @PostMapping("/add/list")
//    public String addUserList(){
//        List<User> users = userService.addUserList();
//        return JSON.toJSONString(users);
//    }
//
//    /**
//     * 插入 dao使用@Param注解
//     * @return
//     */
//    @PostMapping("/add/user/param")
//    public String addUserParam(){
//        int result = userService.addUserByParam();
//        return JSON.toJSONString(result);
//    }
}
