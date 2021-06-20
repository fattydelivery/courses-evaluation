# 运行 forum_analysis.sql
# 1. 修改 forum_analysis.sql 的存放路径为自己的目录
# 2. 修改 高频评论关键词统计 分析中的 wordcount_hive-1.0-SNAPSHOT.jar 和 ikanalyzer-2012_u6.jar 两个文件的存放路径为自己的目录
hive -f /opt/pkg/hive/data/forum_analysis.sql

# 运行 mysql 端
mysql -uroot -p -e "
use forum;

create table if not exists website_hitnumber( 
ym varchar(15) primary key, 
count bigint
);

create table if not exists trafic_period( 
ym varchar(15), 
hours varchar(5), 
count bigint
);

create table if not exists course_visits(
course_name varchar(100) primary key,
count bigint
);

create table if not exists high_wordcount(
words varchar(100) primary key,
count bigint
);

create table if not exists average_changes(
ym varchar(7),
course_name varchar(100) primary key,,
average decimal(10,2)
);

create table if not exists thumbup_changes (
ym varchar(7) primary key,
course_name varchar(100),
count bigint
);
"

# 运行 sqoop 端
# 导入 website_hitnumber
sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table website_hitnumber \
--export-dir '/user/hive/warehouse/forum.db/website_hitnumber/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key ym

sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table trafic_period \
--export-dir '/user/hive/warehouse/forum.db/trafic_period/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key ym

sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table course_visits \
--export-dir '/user/hive/warehouse/forum.db/course_visits/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key course_name

sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table high_wordcount \
--export-dir '/user/hive/warehouse/forum.db/high_wordcount/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key words

sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table average_changes \
--export-dir '/user/hive/warehouse/forum.db/average_changes/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key ym

sqoop export \
--connect jdbc:mysql://hadoop002:3306/forum \
--username root \
-password niit1234 \
--table thumbup_changes \
--export-dir '/user/hive/warehouse/forum.db/thumbup_changes/000000_0' \
--fields-terminated-by ',' \
--update-mode allowinsert \
--update-key ym
