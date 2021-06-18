package io.github.fattydelivery.courseevaluation.KafkaToHBase;

/**
 * @program:courses-evaluation
 * @description:
 * @auther:滕畅
 * @create:2021-06-07 15:53
 **/
import io.github.fattydelivery.courseevaluation.properties.PropertiesUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;

/**
 * *基础数据访问对象
 */
public abstract class BaseData {

    private static Connection connection=null;
    private static Admin admin=null;

    protected void start() throws IOException {
        getConnection();
        getAdmin();
    }

    protected void end() throws IOException {
        if(admin!=null){
            admin.close();
        }

        if(connection!=null){
            connection.close();
        }
    }

    protected synchronized Admin getAdmin() throws IOException {

        if(admin==null){
            admin= getConnection().getAdmin();
        }
        return admin;
    }

    //获取连接对象
    protected synchronized Connection getConnection() throws IOException {

        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", PropertiesUtil.getProperty("hbase.zookeeper.quorum"));

        Connection conn=ConnectionFactory.createConnection(conf);
        return conn;
    }

    //创建命名空间
    protected void createNamespace(String namespace) throws IOException {
        admin=getAdmin();

        try{
            admin.getNamespaceDescriptor(namespace);
        }catch (NamespaceNotFoundException e){

            NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();

            admin.createNamespace(namespaceDescriptor);
        }
    }

    //创建表，若表已经存在，删除后再创建新表
    protected void createTable(String name,String... families) throws Exception {

        admin=getAdmin();
        TableName tableName=TableName.valueOf(name);

        if(admin.tableExists(tableName)){
            deleteTable(name);
        }

        HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);

        for (String family : families) {
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(family);
            tableDescriptor.addFamily(columnDescriptor);
        }

        admin.createTable(tableDescriptor);
    }

    //删除表
    protected void deleteTable(String name) throws Exception {
        TableName tableName = TableName.valueOf(name);
        admin = getAdmin();
        admin.disableTable(tableName);
        admin.deleteTable(tableName) ;
    }

    protected void putData(String name, Put put ) throws Exception {
        //获取表对象
        Connection conn=getConnection();
        Table table=conn.getTable(TableName.valueOf(name));
        //增加数据
        table.put(put);
        //关闭表
        table.close();
    }
}
