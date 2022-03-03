package com.lmxdawn.him.api.db.dao.redisImpl;//package com.mf.game.engine.db.dao.redisImpl;
//
//import com.mf.game.engine.db.entry.Equip;
//import com.mf.game.engine.util.JsonUtil;
//import com.mf.game.engine.util.RedisTools;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service("equipRedis")
//public class EquipRedis {
//    /**
//     * 获取装备
//     * @param Equip_Id
//     * @return
//     */
//    public List getEquip(String Equip_Id){
//        if (Equip_Id==null){
//            return null;
//        }
//        List<String> list= RedisTools.getList(Equip_Id);
//        List<Equip> equips=new ArrayList<>();
//        if (list!=null){
//            for (String string:list){
//                Equip equip= JsonUtil.changeStringToEquip(string);
//                equips.add(equip);
//            }
//        }else {
//            return null;
//        }
//        return equips;
//    }
//    /**
//     * 保存装备到redis
//     */
//    public boolean saveEquipToRedis(String Equip_Id,String value){
//        RedisTools.deleteValueOfList(Equip_Id,2,value);
//        Long size=RedisTools.appendLeftList(Equip_Id,value);
//            if (size==0){
//                return false;
//            }
//            return true;
//    }
//    /**
//     * 删除装备
//     */
//    public boolean delEquipToRedis(String Equip_Id,String value){
//        Long del=RedisTools.deleteValueOfList(Equip_Id,3,value);
//        if (del==0){
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * 更新堡垒
//     */
//    public boolean updateEquipToRedis(String Equip_Id,String newValue,String oldValue){
//        Long del=RedisTools.deleteValueOfList(Equip_Id,3,oldValue);
//        Long add=RedisTools.appendRightList(Equip_Id,newValue);
//        if (add==0){
//            return false;
//        }
//        return true;
//    }
//}
