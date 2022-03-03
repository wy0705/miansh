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

public class MerchantFortUtil extends HbaseUtil {
//    商户购买成功堡垒时添加一条数据
//    商户可以转卖堡垒，往redis中存入一条数据，转入成功删除一条数据
//    商户可以修改此堡垒的广告内容
//    查看堡垒显示的广告内容

    @Override
    public void insertData(String tbname, String MFId, String family, String qualifier, String value) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.MERCHANT_FORT_TABLE);
            table = hc.getTable(tableName);

            List rows = new ArrayList();
            Put put = new Put(Bytes.toBytes(MFId));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier),
                    Bytes.toBytes(value));
            Put put1 = new Put(Bytes.toBytes(MFId));
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
    public void deleteData(String tbname, String MFId) {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.MERCHANT_FORT_TABLE);
            table = hc.getTable(tableName);


            Delete del = new Delete(Bytes.toBytes(MFId));
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


    public void Data(String tbname, String MFId) throws IOException {
        Table table = null;
        try {
            Connection hc = ConnectionFactory.createConnection(config);
            TableName tableName = TableName.valueOf(Contans.MERCHANT_FORT_TABLE);
            table = hc.getTable(tableName);
            Get get = new Get(MFId.getBytes());
            //利用get对象信息获取对应的结果result
            Result result = table.get(get);
            //获取rowkey
            byte[] row = result.getRow();
            //获取cell
            byte[] name = result.getValue("cf1".getBytes(), "name".getBytes());
            System.out.println(Bytes.toString(name));
            //row的类型是字节数组，所以应该借助Bytes转换成字符串输出
            System.out.println(Bytes.toString(row));
        }catch (IOException e){
            e.printStackTrace();
        }finally {
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
//            TableName tableName = TableName.valueOf(Contans.MERCHANT_FORT_TABLE);
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

    public void Update(String tbname, String MFId, String family, String qualifier, String value){
        deleteData(Contans.MERCHANT_FORT_TABLE,MFId);
        insertData(Contans.MERCHANT_FORT_TABLE,MFId,family,qualifier,value);
    }


}
