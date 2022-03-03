package com.lmxdawn.him.api.db.dao.hbaseImpl;


import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserAttackUtil extends HbaseUtil {
//    用户注册成功往用户往此表（用户攻击力表）添加一条数据为用户的初始攻击能力
//    当用户购买装备时在，用户装备表添加数据，对应的用户ID添加装备id,
//    当用户购买装备成功时对用户攻击力表的数据进行修改相应的值
//    以及对应的信息,并且更改用户攻击信息表的数据信息，装备信息为，装备的名字，用户攻击伤害根据需要进行增加


    @Override
    public void insertData(String tbname, String userId, String family, String qualifier, String value) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.USER_ATTACK_TABLE);
            table = hc.getTable(tableName);
//            long ts=System.currentTimeMillis();

//            //行键用时间戳+。。。生成
//            String rowkey1=rowKey+ts;

            List rows = new ArrayList();
            Put put = new Put(Bytes.toBytes(userId));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            Put put1 = new Put(Bytes.toBytes(userId));
            put1.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put1.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            rows.add(put1);
            table.put(rows);
            System.out.println("添加成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteData(String tbname, String userId) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.USER_ATTACK_TABLE);
            table = hc.getTable(tableName);


            Delete del = new Delete(Bytes.toBytes(userId));
            table.delete(del);
            System.out.println("delete a data successful");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //修改
    public void UpdateUserAttcak(String tbname, String userId, String family, String qualifier, String value){
        deleteData(Contans.USER_ATTACK_TABLE,userId);
        insertData(Contans.USER_ATTACK_TABLE,userId,family,qualifier,value);
    }
}
