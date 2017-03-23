package com.action;

import cn.system.basic.global.baseAbstract.BaseAction;
import cn.tools.CommonSendMeg;
import com.dao.UserInfoDao;
import com.dao.impl.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

/**
 * Created by zhangying8 on 17/1/24.
 */
public class UserInfoAction extends BaseAction{

    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private JedisPool jedisPool;
    /**
     *
     * @author zhangying
     * @date 2016年6月29日 下午5:30:39 void
     */
    public void addUserInfo(){
        String userName = this.getFromRequestParameter("user_name");
        String tel = this.getFromRequestParameter("tel");
        String place = this.getFromRequestParameter("place");

        if(null == userName || "".equals(userName)){
            CommonSendMeg.writeMsg(Constant.getMessage("-1001"));
        }

        if(null == tel || "".equals(tel)){
            CommonSendMeg.writeMsg(Constant.getMessage("-1002"));
        }

        if(null == place || "".equals(place)){
            place = "1";
        }

        if(11 != tel.length()){
            CommonSendMeg.writeMsg(Constant.getMessage("-1003"));
        }

        if(userInfoDao.addUserInfo(userName, tel, place)){
            CommonSendMeg.writeMsg(Constant.getMessage("1"));
        }else{
            CommonSendMeg.writeMsg(Constant.getMessage("-1004"));
        }
    }

    /**
     *
     */
    public void addToMysql(){
        String userName = this.getFromRequestParameter("user_name");
        String tel = this.getFromRequestParameter("tel");
        String place = this.getFromRequestParameter("place");

        if(null == tel || "".equals(tel)){
            CommonSendMeg.writeMsg(Constant.getMessage("-1002"));
        }

        if(null == userName || "".equals(userName)){
            CommonSendMeg.writeMsg(Constant.getMessage("-1001"));
        }

        if(null == place || "".equals(place)){
            place = "1";
        }

        if(11 != tel.length()){
            CommonSendMeg.writeMsg(Constant.getMessage("-1003"));
        }

        if(userInfoDao.addToMysql(userName, tel, place)){
            CommonSendMeg.writeMsg(Constant.getMessage("1"));
        }else{
            CommonSendMeg.writeMsg(Constant.getMessage("-1004"));
        }
    }
}
