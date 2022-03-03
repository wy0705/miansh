package com.lmxdawn.him.api.db.dao.hbaseImpl;

import com.lmxdawn.him.api.db.entry.Merchant;
import com.lmxdawn.him.api.db.hbase.HbaseUtil;
import com.lmxdawn.him.api.utils.Contans;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MerchantUtil extends HbaseUtil {

    //增加商户信息，增加广告内容
//    商户注册，商户登录
//添加广告内容
    public Merchant add(String name, String password, String ad) throws IOException {
        Merchant merchant=new Merchant();
        long id=HbaseUtil.getId(Contans.MERCHANT_TABLE,"info","Mer_id","Merid");
        System.out.println(id);
        Table table=HbaseUtil.getConn().getTable(TableName.valueOf(Contans.MERCHANT_TABLE));
        return merchant;
    }
    @Override
    public void insertData(String tbname, String MC_id, String family, String qualifier, String value) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.MERCHANT_TABLE);
            table = hc.getTable(tableName);
//            long ts=System.currentTimeMillis();

//            //行键用时间戳+。。。生成
//            String rowkey1=rowKey+ts;

            List rows = new ArrayList();
            Put put = new Put(Bytes.toBytes(MC_id));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            Put put1 = new Put(Bytes.toBytes(MC_id));
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

//    @Override
//    public void queryAll(String tbname) {
//        Table table = null;
//        try {
//            Connection hc = ConnectionFactory.createConnection(config);
//            TableName tableName = TableName.valueOf(Contans.MERCHANT_TABLE);
//            table = hc.getTable(tableName);
//
//            Scan scan = new Scan();
////            scan.setCacheBlocks(true);
////            scan.setStartRow("a01".getBytes());
////            scan.setStopRow("a20".getBytes());
////			scan.setFilter(filter);
//            ResultScanner scanner = table.getScanner(scan);
//            for(Result rs:scanner) {
//                String rowkey = Bytes.toString(rs.getRow());
//                System.out.println("row key :"+rowkey);
//                Cell[] cells  = rs.rawCells();
//                for(Cell cell : cells) {
//                    System.out.println(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength())+"::"+Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength())+"::"+
//                            Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
//                }
//                System.out.println("-----------------------------------------");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                table.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
