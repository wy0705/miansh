package com.lmxdawn.him.api.utils;


import com.lmxdawn.him.api.db.entry.*;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 形如： {"id" : idValue, "name" : nameValue,
     * "aBean" : {"aBeanId" : aBeanIdValue, ...}}
     *
     * @param object
     * @param clazz
     * @return
     */
    private static Logger log4j = Logger.getLogger(JsonUtil.class);
    public static Object convertJsonToObject(String jsonString, Class clazz) {
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toBean(jsonObject, clazz);
    }

    /**
     * 从一个JSON 对象字符格式中得到一个java对象
     * 其中beansList是一类的集合，形如： {"id" : idValue, "name" :
     * nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}, beansList:[{}, {},
     * ...]}
     * @param jsonString
     * @param clazz
     * @param map 集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" :
     *            Bean.class)
     * @return
     * @throws Exception
     */
    public static Object convertJsonToObject(String jsonString, Class clazz,
                                             Map map) throws Exception {
        JSONObject jsonObject = null;
        try {
            setDataFormat2JAVA();
            jsonObject = JSONObject.fromObject(jsonString);
        } catch (Exception e) {
            throw new Exception("将一个JSON字符串转化为java异常！",e);
        }
        return JSONObject.toBean(jsonObject, clazz, map);
    }

    /**
     * 把数据对象转换成json字符串
     * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
     * 数组对象形如：[{}, {}, {}, ...] map对象形如：{key1 : {"id" : idValue, "name" :
     * nameValue, ...}, key2 : {}, ...}
     *
     * @param object
     * @return
     */
    public static String getJSONString(Object object) throws Exception {
        String jsonString = null;
        JsonConfig jsonConfig = new JsonConfig();
        if (object != null) {
            if (object instanceof Collection || object instanceof Object[]) {
                jsonString = JSONArray.fromObject(object, jsonConfig)
                        .toString();
            } else {
                jsonString = JSONObject.fromObject(object, jsonConfig)
                        .toString();
            }
        }
        return jsonString == null ? "{}" : jsonString;
    }

    private static void setDataFormat2JAVA() {

        // 设定日期转换格式
        JSONUtils.getMorpherRegistry().registerMorpher(
                new DateMorpher(new String[] { "yyyy-MM-dd",
                        "yyyy-MM-dd HH:mm:ss" }));
    }

    /**
     * 拼接JSON字符串，有些特殊字符需要替换掉
     * 如果未替换这些特殊字符生成的JSON不会被正确解析
     * @param str
     * @return
//     */
//    public static String ConvertStringToJson(String str) {
//
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < str.length(); i++) {
//            char c = str.toCharArray()[i];
//            switch (c) {
//                case '\"': sb.append("\\\""); break;
//                case '\\': sb.append("\\\\"); break;
//                case '/': sb.append("\\/"); break;
//                case '\b': sb.append("\\b"); break;
//                case '\f': sb.append("\\f"); break;
//                case '\n': sb.append("\\n"); break;
//                case '\r': sb.append("\\r"); break;
//                case '\t': sb.append("\\t"); break;
//                default: sb.append(c); break;
//            }
//        }
//        return sb.toString();
//    }
//    public static String toString(Object obj){
//        StringBuilder sb = null;
//        try {
//            Class<?> c = obj.getClass();
//            Field[] fields = c.getDeclaredFields();
//
//            sb = new StringBuilder();
//            sb.append(obj.getClass().getName());
//            sb.append(" {");
//
//            int i = 1;
//            for(Field fd : fields){
//                fd.setAccessible(true);
//                sb.append(fd.getName());
//                sb.append(":");
//                sb.append(fd.get(obj));
//
//                if(i != fields.length){
//                    sb.append(", ");
//                }
//                i++;
//            }
//            sb.append("}");
//        } catch (Exception e) {
//            log4j.error(e.getMessage(),e);
//        }
//        return sb.toString();
//    }
//    public static String changeNoteToJson(Note note) {
//        JSONObject jsonObject = JSONObject.fromObject(note);
//        return note.getClass().getCanonicalName() + "ゎ∴♂㊣ф≒ж☆♀∴ぁ"
//                + jsonObject.toString();
//    }
//    public static Note changeJsonToNote(String noteJson) {
//        if(noteJson != null && !"".equals(noteJson)){
//            String[] params = noteJson.split("ゎ∴♂㊣ф≒ж☆♀∴ぁ");
//            if(params != null && params.length == 2){
//                String className = "";
//                String realJson = "";
//                Note note = null;
//                try {
//                    if (params[0] == null || "".equals(params[0])) {
//                        throw new Exception("beanJson中未找到实体类名");
//                    } else {
//                        className = params[0];
//                        realJson = params[1];
//                        note= (Note)Class.forName(className).newInstance();
//                        JSONObject fromObject = JSONObject.fromObject(realJson);
//                        note = (Note)JSONObject.toBean(fromObject, Note.class);
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return note;
//            }else{
//                return null;
//            }
//        }else{
//            return null;
//        }
//    }
//    public static String changeNoteBookToJson(NoteBook noteBook) {
//        JSONObject jsonObject = JSONObject.fromObject(noteBook);
//        return noteBook.getClass().getCanonicalName() + "ゎ∴♂㊣ф≒ж☆♀∴ぁ"
//                + jsonObject.toString();
//    }
//    public static NoteBook changeJsonToNoteBook(String noteBookJson) {
//        if(noteBookJson != null && !"".equals(noteBookJson)){
//            String[] params = noteBookJson.split("ゎ∴♂㊣ф≒ж☆♀∴ぁ");
//            if(params != null && params.length == 2){
//                String className = "";
//                String realJson = "";
//                NoteBook noteBook = null;
//                try {
//                    if (params[0] == null || "".equals(params[0])) {
//                        throw new Exception("beanJson中未找到实体类名");
//                    } else {
//                        className = params[0];
//                        realJson = params[1];
//                        noteBook= (NoteBook)Class.forName(className).newInstance();
//                        JSONObject fromObject = JSONObject.fromObject(realJson);
//                        noteBook = (NoteBook)JSONObject.toBean(fromObject, NoteBook.class);
//
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return noteBook;
//            }else{
//                return null;
//            }
//        }else{
//            return null;
//        }
//    }
//    public static String changeNoteListToJson(List<Note> noteList) {
//        JSONArray jsonarray = JSONArray.fromObject(noteList);// list转json
//        return jsonarray.toString();
//    }
//    public static List<Note> changeJsonToNoteList(String noteListJson) {
//        //String json="[{'name':'huangbiao','age':15},{'name':'liumei','age':14}]";
//        List<Note> noteList = new ArrayList<Note>();// 笔记信息list
//        JSONArray jsonarray = JSONArray.fromObject(noteListJson);
//        List list = (List)JSONArray.toCollection(jsonarray, Note.class);
//        Iterator it = list.iterator();
//        while(it.hasNext()){
//            Note note = (Note)it.next();
//            noteList.add(note);
//        }
//        return noteList;
//    }
//    public static String changeNoteBookListToJson(List<NoteBook> noteBookList) {
//        JSONArray jsonarray = JSONArray.fromObject(noteBookList);// list转json
//        return jsonarray.toString();
//    }
//    public static List<NoteBook> changeJsonToNoteBookList(String noteBookListJson) {
//        //String json="[{'name':'huangbiao','age':15},{'name':'liumei','age':14}]";
//        List<NoteBook> noteList = new ArrayList<NoteBook>();// 笔记信息list
//        JSONArray jsonarray = JSONArray.fromObject(noteBookListJson);
//        List list = (List)JSONArray.toCollection(jsonarray, NoteBook.class);
//        Iterator it = list.iterator();
//        while(it.hasNext()){
//            NoteBook noteBook = (NoteBook)it.next();
//            noteList.add(noteBook);
//        }
//        return noteList;
//    }

    /**
     * 把string转变为json新式
     * @param str
     * @return
     */
    public static User changerStringToUser(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            User user=new User();

            user.setUserId(str.split("\\"+"|")[0]);
            user.setName(str.split("\\"+"|")[1]);
            user.setPhone(str.split("\\"+"|")[2]);
            user.setPassword(str.split("\\"+"|")[3]);

            return user;
        }
    }
    public static Merchant_Fort changeStringToMFort(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            Merchant_Fort mf = new Merchant_Fort();
            mf.setRowKey(str.split("\\"+"|")[0]);
            mf.setMc_id(str.split("\\"+"|")[1]);
            mf.setMc_name(str.split("\\"+"|")[2]);
            mf.setFort_id(str.split("\\"+"|")[3]);
            mf.setFort_name(str.split("\\"+"|")[4]);

            return mf;
        }
    }

    public static Fort changeStringToFort(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            Fort fort=new Fort();
//            fort.setFortId(str.split("\\"+"|")[0]);
            fort.setFort_Name(str.split("\\"+"|")[1]);
            fort.setFort_Address(str.split("\\"+"|")[2]);

            return fort;
        }
    }

    public static Equip changeStringToEquip(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            Equip equip=new Equip();
//            equip.setEquip_Id(str.split("\\"+"|")[0]);
            equip.setEquip_Name(str.split("\\"+"|")[1]);
            equip.setEquip_Info(str.split("\\"+"|")[2]);
            equip.setEquip_Energy(str.split("\\"+"|")[3]);
            return equip;
        }
    }
    public static User_Attack changerStringToUA(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            User_Attack ua=new User_Attack();
            ua.setUserId(str.split("\\"+"|")[0]);
            ua.setAttack(str.split("\\"+"|")[1]);
           return ua;
        }
    }
//    //全国排名表
//    public static Rank changeStringToRank(String str){
//        if(str == null || str.equals("")){
//            return null;
//        }else{
//            Rank rank=new Rank();
//            rank(str.split("\\"+"|")[0]);
//            rank.setEquip_Name(str.split("\\"+"|")[1]);
//            rank.setEquip_Info(str.split("\\"+"|")[2]);
//            rank.setEquip_Energy(str.split("\\"+"|")[3]);
//            return equip;
//        }
//    }

    public static User_Equip changeStringToUE(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            User_Equip ue=new User_Equip();
            ue.setUEId(str.split("\\"+"|")[0]);
            ue.setUserid(str.split("\\"+"|")[1]);
            ue.setEquip_id(str.split("\\"+"|")[2]);
            ue.setEquip_Name(str.split("\\"+"|")[3]);
            ue.setEquip_Info(str.split("\\"+"|")[4]);
            ue.setEquip_Energy(str.split("\\"+"|")[5]);
            return ue;
        }
    }

//    public static List<Note> changeStringToListNote(String str){
//        if(str == null || str.equals("")){
//            return null;
//        }else{
//            // List<String> strList =
//            List<Note> noteList = new ArrayList<Note>();
//            JSONArray jsonarray = JSONArray.fromObject(str);
//            List<String> list = (List)JSONArray.toList(jsonarray,String.class);
//            for (String s : list) {
//                Note note = new Note();
//                note.setRowKey(s.split("\\"+Constants.STRING_SEPARATOR)[0]);
//                note.setName(s.split("\\"+Constants.STRING_SEPARATOR)[1]);
//                note.setCreateTime(s.split("\\"+Constants.STRING_SEPARATOR)[2]);
//                note.setStatus(s.split("\\"+Constants.STRING_SEPARATOR)[3]);
//                noteList.add(note);
//            }
//            return noteList;
//        }
//    }
    public static List<String> changeStringToListString(String str){
        if(str == null || str.equals("")){
            return null;
        }else{
            JSONArray jsonarray = JSONArray.fromObject(str);
            List<String> list = (List)JSONArray.toList(jsonarray,String.class);
            return list;
        }
    }
}
