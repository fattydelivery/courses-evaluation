package io.github.fattydelivery.courseevaluation.KafkaToHBase;
/**
 * @program:courses-evaluation
 * @description:
 * @auther:滕畅
 * @create:2021-06-07 15:53
 **/
import io.github.fattydelivery.courseevaluation.properties.PropertiesUtil;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *HBase数据访问对象
 */
public class HBaseData extends BaseData{

    //初始化
    public void init() throws Exception {
        start();

        createNamespace(PropertiesUtil.getProperty("hbase.namespace"));
        createTable(PropertiesUtil.getProperty("hbase.table.name"),PropertiesUtil.getProperty("hbase.table.name.cf"));

        end();
    }

    //插入数据
    public void insertData(String value) throws Exception {
        //将通话日志保存到HBase表中

        //1.获取通话日志数据
        String[] values=value.split(" ");
        String ip=values[0];
        String time = values[3].substring(1,values[3].length());
        System.out.println(ip+" "+time);

        //2.设置rowkey,列族，列名
        SimpleDateFormat dateRandom = new SimpleDateFormat("yyMMddHHmmss");
        Date date = new Date();
        String rowkey = ip+"_"+dateRandom.format(date);
        Put put=new Put(Bytes.toBytes(rowkey));

        byte[] family= Bytes.toBytes(PropertiesUtil.getProperty("hbase.table.name.cf"));

        put.addColumn(family,Bytes.toBytes(PropertiesUtil.getProperty("hbase.table.name.cf.col1")),Bytes.toBytes(ip));
        put.addColumn(family,Bytes.toBytes(PropertiesUtil.getProperty("hbase.table.name.cf.col2")),Bytes.toBytes(time));

        //3.保存数据
        putData(PropertiesUtil.getProperty("hbase.table.name"),put);

    }
}
