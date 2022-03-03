package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;
//
//import com.mf.game.engine.db.entry.Fort;
//import com.mf.game.engine.db.entry.Merchant_Fort;
//import com.mf.game.engine.util.JsonUtil;
//import com.mf.game.engine.util.RedisTools;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("fortRedis")
//public class FortRedis {
//
//    /**
//     * 获取堡垒
//     * key:FortId
//     */
//    public List getFort(String FortId){
//        if (FortId==null){
//            return null;
//        }
//        List<String> list= RedisTools.getList(FortId);
//        List<Fort> forts=new ArrayList<>();
//        if (list!=null){
//            for (String string:list){
//                Fort fort= JsonUtil.changeStringToFort(string);
//                forts.add(fort);
//
//            }
//
//        }else {
//            return null;
//        }
//
//        return forts;
//    }
//    /**
//     * 保存堡垒到redis
//     */
//    public boolean saveFortToRedis(String FortId,List<String> values){
//        Long list=RedisTools.setLList(FortId,values);
//        if (list==0){
//            return false;
//        }
//        return true;
//    }
//    /**
//     * 删除堡垒
//     */
//    public boolean delFortToRedis(String FortId,String value){
//        Long del=RedisTools.deleteValueOfList(FortId,2,value);
//        if (del==0){
//            return false;
//        }
//        return true;
//    }
//}
