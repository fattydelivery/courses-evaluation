use forum;

# --1. 每个月网站的点击频次--
drop table if exists website_hitnumber;
create table website_hitnumber row format delimited fields terminated by ','
as select t ym, count(distinct ip) count
from
(select ip, concat_ws('-', s[1], s[2]) t 
from 
(select ip, split(split(times,':')[0], '/') s 
from evaluation
)str
)tmp
group by t;

# --2. 网站流量的高峰时段--
drop table if exists trafic_period;
create table trafic_period row format delimited fields terminated by ',' 
as select t.exp ym, t.hours, count(1) count
from
 (select tmp.e exp,
case when hour <6 then '0-6'
when hour >=6 and hour <12 then '6-12'
else '12-24'
end as hours
from 
(select concat_ws('-', dmy[1], dmy[2]) e, hour 
from 
(select split(arr[0], '/') dmy, cast(arr[1] as int) hour 
from 
(select split(times,':') arr 
from evaluation
)tmp1
)tmp2
)tmp
) t
group by t.exp, t.hours
order by count desc;

# --3. 选修课程的访问量统计--
drop table if exists course_visits;
create table course_visits row format delimited fields terminated by ',' 
as select t.course_name, count(distinct tmp.rename) count from
(select id, rename
from times
lateral view explode(split(times, ',')) times as rename) tmp join t_course t
on tmp.id=t.course_id and t.type_name= '选修课'
group by t.course_name
order by count desc;

# --4. 高频评论关键词统计--
add jar /opt/dataJars/wordcount_hive-1.0-SNAPSHOT.jar;
add jar /opt/dataJars/ikanalyzer-2012_u6.jar;
create temporary function forum_wc as 'com.niit.Main';

drop table if exists high_wordcount;
create table high_wordcount row format delimited fields terminated by ',' 
as select words, count(1) count 
from
(select explode(split(forum_wc(comment_content), ' ')) as words 
from t_comment)tmp 
group by words 
order by count desc;

# --5. 课程月平均评分变化统计
drop table if exists average_changes;
create table average_changes row format delimited fields terminated by ','
as select date_format(rating_time,'yyyy-MM') ym , course_name, cast(avg(rating_score) as decimal(10,2)) avg
from t_course join t_rating on t_course.course_id=t_rating.course_id
group by date_format(rating_time,'yyyy-MM'), course_name
order by avg desc;

# --6. 课程点赞数的增长变化统计--
drop table if exists thumbup_changes;
create table thumbup_changes row format delimited fields terminated by ','
as select t1.ym, course_name, t2.count-t1.count count
from 
(select course_id, ym, cast(split(t_1.ym, '-')[1] as int) num, count 
from
(select course_id, date_format(like_time, 'yyyy-MM') ym, count(1) as count 
from 
t_like group by date_format(like_time, 'yyyy-MM'), course_id
)t_1
)t1
join (select course_id, ym, cast(split(t_2.ym, '-')[1] as int) num, count 
from
(select course_id, date_format(like_time, 'yyyy-MM') ym, count(1) as count 
from 
t_like group by date_format(like_time, 'yyyy-MM'), course_id
)t_2
)t2
on t1.course_id=t2.course_id and t1.num=t2.num+1
join t_course
on t_course.course_id=t1.course_id;
