CREATE DATABASE IF NOT EXISTS forum;

USE forum;

DROP TABLE IF EXISTS t_rating;
DROP TABLE IF EXISTS t_comment;
DROP TABLE IF EXISTS t_like;
DROP TABLE IF EXISTS t_course;

CREATE TABLE t_course (
    course_id VARCHAR(10) PRIMARY KEY,
    course_name VARCHAR(20),
    course_credit INT,
    course_credit_hour INT,
    teacher_name VARCHAR(20),
    type_name VARCHAR(20)
);

CREATE TABLE t_rating (
    rating_id INT PRIMARY KEY,
    course_id VARCHAR(10),
    rating_time VARCHAR(20),
    rating_score DOUBLE,
    FOREIGN KEY(course_id) REFERENCES t_course(course_id)
);

CREATE TABLE t_comment (
    comment_id INT PRIMARY KEY,
    course_id VARCHAR(10),
    comment_time VARCHAR(20),
    comment_content VARCHAR(500),
    FOREIGN KEY(course_id) REFERENCES t_course(course_id)
);

CREATE TABLE t_like (
    like_id INT PRIMARY KEY,
    course_id VARCHAR(10),
    like_time VARCHAR(20),
    FOREIGN KEY(course_id) REFERENCES t_course(course_id)
);

INSERT INTO t_course(course_id, course_name, course_credit, course_credit_hour, teacher_name, type_name)
    VALUES("B16290", "使用Sqoop进行数据交换", 2, 32, "Delucia", "专业课");

INSERT INTO t_course(course_id, course_name, course_credit, course_credit_hour, teacher_name, type_name)
    VALUES("Z16342", "数据仓库与数据挖掘", 4, 64, "杨厚群", "选修课");

INSERT INTO t_course(course_id, course_name, course_credit, course_credit_hour, teacher_name, type_name)
    VALUES("B16291", "Flume使用指南", 2, 32, "Delucia", "专业课");