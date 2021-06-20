#启动hive
bin/hive
#评论记录表导入
bin/sqoop import \
--connect jdbc:mysql://172.16.71.27:3306/forum \
--table t_comment \
--username root \
--password niit1234 \
--direct \
--target-dir /user/hive/warehouse/t_comment \
--fields-terminated-by ',' \
--delete-target-dir \
--hive-import \
--hive-overwrite \
--hive-database forum \
--create-hive-table \
--num-mappers 1 \
--split-by 'comment_id' \
-- --default-character-set=utf-8；
#课程信息表导入
bin/sqoop import \
--connect jdbc:mysql://172.16.71.27:3306/forum \
--table t_course \
--username root \
--password niit1234 \
--direct \
--target-dir /user/hive/warehouse/t_course \
--fields-terminated-by ',' \
--delete-target-dir \
--hive-import \
--hive-overwrite \
--hive-database forum \
--create-hive-table \
--num-mappers 1 \
--split-by 'course_id' \
-- --default-character-set=utf-8；
#点赞记录表导入
bin/sqoop import \
--connect jdbc:mysql://172.16.71.27:3306/forum \
--table t_like \
--username root \
--password niit1234 \
--direct \
--target-dir /user/hive/warehouse/t_like \
--fields-terminated-by ',' \
--delete-target-dir \
--hive-import \
--hive-overwrite \
--hive-database forum \
--create-hive-table \
--num-mappers 1 \
--split-by 'like_id' \
-- --default-character-set=utf-8；
#评分记录表导入
bin/sqoop import \
--connect jdbc:mysql://172.16.71.27:3306/forum \
--table t_rating \
--username root \
--password niit1234 \
--direct \
--target-dir /user/hive/warehouse/t_rating \
--fields-terminated-by ',' \
--delete-target-dir \
--hive-import \
--hive-overwrite \
--hive-database forum \
--create-hive-table \
--num-mappers 1 \
--split-by 'rating_id' \
-- --default-character-set=utf-8；
#刷新 导入并同步数据增量
bin/sqoop job \
--create your-sync-job \
-- import \
--connect jdbc:mysql://10.95.3.49:3306/forum \
--table t_comment \
--username root \
--password niit1234 \
--hive-import \
--incremental append \
--check-column id \
--last-value 1 \  \*每次更新数据增量都需要修改此增量值*\
-- --default-character-set=utf-8
