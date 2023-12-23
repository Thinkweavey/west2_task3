# 一、SQL简述
 ## 1.SQL的概述
Structure Query Language(结构化查询语言)简称SQL，它被美国国家标准局(ANSI)确定为关系型数据库语言的美国标准，后被国际化标准组织(ISO)采纳为关系数据库语言的国际标准。数据库管理系统可以通过SQL管理数据库；定义和操作数据，维护数据的完整性和安全性。

## 2.SQL的优点
1、简单易学，具有很强的操作性
2、绝大多数重要的数据库管理系统均支持SQL
3、高度非过程化；用SQL操作数据库时大部分的工作由DBMS自动完成

## 3.SQL的分类
1、DDL(Data Definition Language) 数据定义语言，用来操作数据库、表、列等； 常用语句：CREATE、 ALTER、DROP
2、DML(Data Manipulation Language) 数据操作语言，用来操作数据库中表里的数据；常用语句：INSERT、 UPDATE、 DELETE
3、DCL(Data Control Language) 数据控制语言，用来操作访问权限和安全级别； 常用语句：GRANT、DENY
4、DQL(Data Query Language) 数据查询语言，用来查询数据 常用语句：SELECT

# 二、数据库的三大范式
1、第一范式(1NF)是指数据库表的每一列都是不可分割的基本数据线；也就是说：每列的值具有原子性，不可再分割。
2、第二范式(2NF)是在第一范式(1NF)的基础上建立起来得，满足第二范式(2NF)必须先满足第一范式(1NF)。如果表是单主键，那么主键以外的列必须完全依赖于主键；如果表是复合主键，那么主键以外的列必须完全依赖于主键，不能仅依赖主键的一部分。
3、第三范式(3NF)是在第二范式的基础上建立起来的，即满足第三范式必须要先满足第二范式。第三范式(3NF)要求：表中的非主键列必须和主键直接相关而不能间接相关；也就是说：非主键列之间不能相关依赖。

# 三、数据库的数据类型
使用MySQL数据库存储数据时，不同的数据类型决定了 MySQL存储数据方式的不同。为此，MySQL数据库提供了多种数据类型，其中包括整数类型、浮点数类型、定点 数类型、日期和时间类型、字符串类型、二进制…等等数据类型。
## 1.整数类型
数据类型	| 字节数	| 无符号数的取值范围	| 有符号数的取值范围
---- | ---- | ---- | ----
TINYINT	| 1 |	0~255 |	-128~127
SMALLINT|	2|	0~65535	|-32768~32768
MEDIUMINT	|3|	0~16777215|	-8388608~8388608
INT	|4|	0~4294967295|	-2147483648~ 2147483648
BIGINT|	8|	0~18446744073709551615	|-9223372036854775808~9223372036854775808

## 2.浮点数类型和定点数类型
在MySQL数据库中使用浮点数和定点数来存储小数。浮点数的类型有两种：单精度浮点数类型（FLOAT)和双精度浮点数类型（DOUBLE)。而定点数类型只有一种即DECIMAL类型。下图列举了 MySQL中浮点数和定点数类型所对应的字节大小及其取值范围：
数据类型 | 字节数 | 有符号的取值范围 | 无符号的取值范围
---- | ---- | ---- | ----
FLOAT | 4 |-3.402823466E+38~-1.175494351E-38|0和1.175494351E-38~3.402823466E+38
DOUBLE | 8 |-1.7976931348623157E+308~2.2250738585072014E-308|0和2.2250738585072014E-308~1.7976931348623157E+308
DECIMAL（M,D）|	M+2|	-1.7976931348623157E+308~2.2250738585072014E-308|	0和2.2250738585072014E-308~1.7976931348623157E+308
注意点：从上图中可以看出：DECIMAL类型的取值范围与DOUBLE类型相同。但是，请注意：DECIMAL类型的有效取值范围是由M和D决定的。其中，M表示的是数据的长 度，D表示的是小数点后的长度。比如，将数据类型为DECIMAL(6,2)的数据6.5243 插入数据库后显示的结果为6.52	

## 3.字符串类型？？？
在MySQL中常用CHAR 和 VARCHAR 表示字符串。两者不同的是：VARCHAR存储可变长度的字符串。
当数据为CHAR(M)类型时，不管插入值的长度实际是多少它所占用的存储空间都是M个字节；而VARCHAR(M)所对应的数据所占用的字节数为实际长度加1
插入值 | CHAR（3） |存储需求|VARCHAR（3） |存储需求
---- | ---- | ---- | ---- |----
‘’|	‘’|	3个字节	|‘’|	1个字节
‘a’	|‘a’	|3个字节|	‘a’|	2个字节
‘ab’	|‘ab’	|3个字节	|‘ab’	|3个字节
‘abc’|	‘ab’	|3个字节	|‘abc’|	4个字节
‘abcd’|	‘ab’|	3个字节	|‘abc’|	4字节

## 4.字符串类型
文本类型用于表示大文本数据，例如，文章内容、评论、详情等，它的类型分为如下4种：
数据类型	|储存范围
---- | ---- 
TINYTEXT|	0~255字节
TEXT	|0~65535字节
MEDIUMTEXT|	0~16777215字节
LONGTEXT|	0~4294967295字节

## 5.日期与时间类型
MySQL提供的表示日期和时间的数据类型分别是 ：YEAR、DATE、TIME、DATETIME 和 TIMESTAMP。下图列举了日期和时间数据类型所对应的字节数、取值范围、日期格式以及零值：
数据类型|	字节数|	取值范围	|日期格式|	零值
---- | ---- | ---- | ---- | ----
YEAR|	1	|1901~2155	|YYYY|	0000
DATE|	4|	1000-01-01~9999-12-31|	YYYY-MM-DD	|0000-00-00
TIME|	3	|-838：59：59~ 838：59：59	|HH:MM:SS|	00:00:00
DATETIME|	8|	1000-01-01 00:00:00~9999-12-31 23:59:59|	YYYY-MM-DD HH:MM:SS|	0000-00-00 00:00:00
TIMESTAMP|	4	|1970-01-01 00:00:01~2038-01-19 03:14:07	|YYYY-MM-DD HH:MM:SS|	0000-00-00 00:00:00
### 5.1 YEAR类型
YEAR类型用于表示年份，在MySQL中，可以使用以下三种格式指定YEAR类型 的值。
1、使用4位字符串或数字表示，范围为’1901’—'2155’或1901—2155。例如，输入 ‘2019’或2019插入到数据库中的值均为2019。
2、使用两位字符串表示，范围为’00’—‘99’。其中，‘00’—'69’范围的值会被转换为 2000—2069范围的YEAR值，‘70’—'99’范围的值会被转换为1970—1999范围的YEAR 值。例如，输入’19’插入到数据库中的值为2019。
3、使用两位数字表示，范围为1—99。其中，1—69范围的值会被转换为2001— 2069范围的YEAR值，70—99范围的值会被转换为1970—1999范围的YEAR值。例 如，输入19插入到数据库中的值为2019。
请注意：当使用YEAR类型时，一定要区分’0’和0。因为字符串格式的’0’表示的YEAR值是2000而数字格式的0表示的YEAR值是0000。
### 5.2 TIME类型
TIME类型用于表示时间值，它的显示形式一般为HH:MM:SS，其中，HH表示小时， MM表示分,SS表示秒。在MySQL中，可以使用以下3种格式指定TIME类型的值。
1、以’D HH:MM:SS’字符串格式表示。其中，D表示日可取0—34之间的值, 插入数据时，小时的值等于(DX24+HH)。例如，输入’2 11:30:50’插入数据库中的日期为59:30:50。
2、以’HHMMSS’字符串格式或者HHMMSS数字格式表示。 例如，输入’115454’或115454,插入数据库中的日期为11:54:54
3、使用CURRENT_TIME或NOW()输入当前系统时间。
### 5.3 DATETIME类型
DATETIME类型用于表示日期和时间，它的显示形式为’YYYY-MM-DD HH: MM:SS’，其中，YYYY表示年，MM表示月，DD表示日，HH表示小时，MM表示分，SS 表示秒。在MySQL中，可以使用以下4种格式指定DATETIME类型的值。
以’YYYY-MM-DD HH:MM:SS’或者’YYYYMMDDHHMMSS’字符串格式表示的日期和时间，取值范围为’1000-01-01 00:00:00’—‘9999-12-3 23:59:59’。例如，输入’2019-01-22 09:01:23’或 ‘20140122_0_90123’插入数据库中的 DATETIME 值都为 2019-01-22 09:01:23。
1、以’YY-MM-DD HH:MM:SS’或者’YYMMDDHHMMSS’字符串格式表示的日期和时间，其中YY表示年，取值范围为’00’—‘99’。与DATE类型中的YY相同，‘00’— '69’范围的值会被转换为2000—2069范围的值，‘70’—'99’范围的值会被转换为1970—1999范围的值。
2、以YYYYMMDDHHMMSS或者YYMMDDHHMMSS数字格式表示的日期 和时间。例如，插入20190122090123或者190122090123,插入数据库中的DATETIME值都 为 2019-01-22 09:01:23。
3、使用NOW来输入当前系统的日期和时间。
### 5.4 TIMESTAMP类型
TIMESTAMP类型用于表示日期和时间，它的显示形式与DATETIME相同但取值范围比DATETIME小。在此，介绍几种TIMESTAMP类型与DATATIME类型不同的形式：
1、使用CURRENT_TIMESTAMP输入系统当前日期和时间。
2、输入NULL时系统会输入系统当前日期和时间。
3、无任何输入时系统会输入系统当前日期和时间。


## 6.二进制类型
在MySQL中常用BLOB存储二进制类型的数据，例如：图片、PDF文档等。BLOB类型分为如下四种：
数据类型|	储存范围
---- | ---- 
TINYBLOB|	0~255字节
BLOB	|0~65535字节
MEDIUMBLOB	|0~16777215字节
LONGBLOB	|0~4294967295字节

# 四、数据库、数据表的基本操作
## 1.数据库的基本操作
创建一个叫db1的数据库MySQL命令：
 `create database 数据库名称;`
删除数据库MySQL命令：
`drop database db1;`
查询出MySQL中所有的数据库MySQL命令：
`show databases;`
将数据库的字符集修改为gbk MySQL命令：
`alter database db1 character set gbk;`
切换数据库 MySQL命令：
`use db1;`
查看当前使用的数据库 MySQL命令：
`select database();`
## 2.数据表的基本操作
数据库创建成功后可在该数据库中创建数据表(简称为表)存储数据。请注意：在操作数据表之前应使用“USE 数据库名;”指定操作是在哪个数据库中进行先关操作，否则会抛出“No database selected”错误。
### 2.1 创建数据表
` create table 表名(
         字段1 字段类型,
         字段2 字段类型,
         …
         字段n 字段类型
);
`
### 2.2 查看数据表
查看当前数据库中所有表 MySQL命令：
`show tables;`
查表的基本信息 MySQL命令：
`show create table student;`
查看表的字段信息 MySQL命令：
`desc student;`

### 2.3 修改数据表
修改表名 MySQL命令：
`alter table student rename to stu;`
修改字段名 MySQL命令：
`alter table stu change name sname varchar(10);`
修改字段数据类型 MySQL命令：
`alter table stu modify sname int;`
增加字段 MySQL命令：
`alter table stu add address varchar(50);`
删除字段 MySQL命令：
`alter table stu drop address;`

### 2.4 删除数据表
`drop table 表名;`


# 五、数据表的约束
约束条件  |  说明 
----  |   ---- 
PRIMARY KEY	|主键约束用于唯一标识对应的记录
FOREIGN KEY|	外键约束
NOT NULL	|非空约束
UNIQUE|	唯一性约束
DEFAULT|	默认值约束，用于设置字段的默认值
以上五种约束条件针对表中字段进行限制从而保证数据表中数据的正确性和唯一性。换句话说，表的约束实际上就是表中数据的限制条件。|	


# 六、数据表插入数据

## 1. 为表中所有字段插入数据
`INSERT INTO 表名（字段名1,字段名2,...) VALUES (值 1,值 2,...);`
## 2. 为表中指定字段插入数据
`INSERT INTO 表名（字段名1,字段名2,...) VALUES (值 1,值 2,...);`
## 3. 同时插入多条记录
`INSERT INTO 表名 [(字段名1,字段名2,...)]VALUES (值 1,值 2,…),(值 1,值 2,…),...;`

# 七、更新数据

## 1. UPDATE基本语法
`UPDATE 表名 SET 字段名1=值1[,字段名2 =值2,…] [WHERE 条件表达式];`
## 2. UPDATE更新部分数据
`update student set age=20,gender='female' where name='tom';`
## 3. UPDATE更新全部数据
`update student set age=18;`

# 八、删除数据
## 1. DELETE基本语法
`DELETE FROM 表名 [WHERE 条件表达式];`
## 2. DELETE删除部分数据
`delete from student where age=14;`
## 3. DELETE删除全部数据
`delete from student;`

## 4. TRUNCATE和DETELE的区别
TRUNCATE和DETELE都能实现删除表中的所有数据的功能，但两者也是有区别的：
1、DELETE语句后可跟WHERE子句，可通过指定WHERE子句中的条件表达式只删除满足条件的部分记录；但是，TRUNCATE语句只能用于删除表中的所有记录。
2、使用TRUNCATE语句删除表中的数据后，再次向表中添加记录时自动增加字段的默认初始值重新由1开始；使用DELETE语句删除表中所有记录后，再次向表中添加记录时自动增加字段的值为删除时该字段的最大值加1
3、DELETE语句是DML语句，TRUNCATE语句通常被认为是DDL语句


# 九、MySQL数据表简单查询
## 1.简单查询概述
简单查询即不含where的select语句。
## 2.查询所有字段（方法不唯一只是举例）
`select * from student;`
## 3.查询指定字段（sid、sname）
`select sid,sname from student;`
## 4.常数的查询
`select sid,sname,'2021-03-02' from student;`
## 5.从查询结果中过滤重复数据
**在SELECT查询语句中DISTINCT关键字只能用在第一个所查列名之前。**
`select distinct gender from student;`

## 6.算术运算符（举例加运算符）
查询学生10年后的年龄 MySQL命令：
`select sname,age+10 from student;`

# 十、函数
## 1.聚合函数

**聚合函数使用规则：**
只有SELECT子句和HAVING子句、ORDER BY子句中能够使用聚合函数。例如，在WHERE子句中使用聚合函数是错误的。
### 1.1、count（）
统计表中数据的行数或者统计指定列其值不为NULL的数据个数
查询有多少该表中有多少人
`select count(*) from student;`
### 1.2、max（）
计算指定列的最大值，如果指定列是字符串类型则使用字符串排序运算
查询该学生表中年纪最大的学生
`select max(age) from student;`
### 1.3、min（）
### 1.4、sum（）
### 1.5、avg（）
---
### 2.1、时间函数
```SELECT NOW();
SELECT DAY (NOW());
SELECT DATE (NOW());
SELECT TIME (NOW());
SELECT YEAR (NOW());
SELECT MONTH (NOW());
SELECT CURRENT_DATE();
SELECT CURRENT_TIME();
SELECT CURRENT_TIMESTAMP();
SELECT ADDTIME('14:23:12','01:02:01');
SELECT DATE_ADD(NOW(),INTERVAL 1 DAY);
SELECT DATE_ADD(NOW(),INTERVAL 1 MONTH);
SELECT DATE_SUB(NOW(),INTERVAL 1 DAY);
SELECT DATE_SUB(NOW(),INTERVAL 1 MONTH);
SELECT DATEDIFF('2019-07-22','2019-05-05');
```
### 2.2、字符串函数
```--连接函数
SELECT CONCAT ()
--
SELECT INSTR ();
--统计长度
SELECT LENGTH();
```
### 2.3、数学函数
```-- 绝对值
SELECT ABS(-136);
-- 向下取整
SELECT FLOOR(3.14);
-- 向上取整
SELECT CEILING(3.14);
```
# 十一、条件查询

## 1.使用关系运算符查询
关系运算符  |   说明 
----  |   ----  
=|	等于
<>|	不等于
!=	|不等于
<	|小于
<=	|小于等于
>	|大于
>=	|大于等于
## 2.使用IN关键字查询

查询sid为S_1002和S_1003的学生信息 MySQL命令：
`select * from student where sid in ('S_1002','S_1003');`
查询sid为S_1001以外的学生的信息 MySQL命令：
`select * from student where sid not in ('S_1001');`

## 3.使用BETWEEN AND关键字查询

## 4.使用空值查询

## 5.使用AND关键字查询
在MySQL中可使用AND关键字可以连接两个或者多个查询条件。
## 6.使用OR关键字查询
`select * from student where age>15 or gender='male';`
## 7.使用LIKE关键字查询
MySQL中可使用LIKE关键字可以判断两个字符串是否相匹配
### 7.1 普通字符串
`select * from student where sname like 'wang';`
### 7.2 含有%通配的字符串
%用于匹配任意长度的字符串。例如，字符串“a%”匹配以字符a开始任意长度的字符串
`select * from student where sname like 'li%';`
### 7.3 含有_通配的字符串
下划线通配符只匹配单个字符，如果要匹配多个字符，需要连续使用多个下划线通配符。例如，字符串“ab_”匹配以字符串“ab”开始长度为3的字符串，如abc、abp等等；字符串“a__d”匹配在字符“a”和“d”之间包含两个字符的字符串，如"abcd"、"atud"等等。

## 8.使用LIMIT限制查询结果的数量
当执行查询数据时可能会返回很多条记录，而用户需要的数据可能只是其中的一条或者几条
查询学生表中年纪最小的3位同学 MySQL命令：
`select * from student order by age asc limit 3;`


## 9.使用GROUP BY进行分组查询
通过GROUP BY将原来的表拆分成了几张小表。
### 9.1 GROUP BY和聚合函数一起使用
统计各部门员工个数 MySQL命令：
`select count(*), departmentnumber from employee group by departmentnumber;`
### 9.2 GROUP BY和聚合函数以及HAVING一起使用
`select sum(salary),departmentnumber from employee group by departmentnumber having sum(salary)>8000;`

## 10.使用ORDER BY对查询结果排序

```SELECT 字段名1,字段名2,…
FROM 表名
ORDER BY 字段名1 [ASC 丨 DESC],字段名2 [ASC | DESC];
```

# 十二、别名设置

```SELECT * FROM 表名 [AS] 表的别名 WHERE .... ;

SELECT 字段名1 [AS] 别名1 , 字段名2 [AS] 别名2 , ... FROM 表名 WHERE ... ;
```


# 十三、表的关联关系

***多对一***
多对一(亦称为一对多)是数据表中最常见的一种关系。例如：员工与部门之间的关系，一个部门可以有多个员工；而一个员工不能属于多个部门只属于某个部门。在多对一的表关系 中，应将外键建在多的一方否则会造成数据的冗余。
***多对多***
多对多是数据表中常见的一种关系。例如：学生与老师之间的关系，一个学生可以有多个老师而且一个老师有多个学生。通常情况下，为了实现这种关系需要定义一张中间表(亦称为连接表)该表会存在两个外键分别参照老师表和学生表。
***一对一***
在开发过程中，一对一的关联关系在数据库中并不常见；因为以这种方式存储的信息通常会放在同一张表中。
## 1.关联查询
`select * from student where classid=(select cid from class where cname='Java');`

## 2.关于关联关系的删除数据
请从班级表中删除Java班级。在此，请注意：班级表和学生表之间存在关联关系；要删除Java班级，应该先删除学生表中与该班相关联的学生。否则，假若先删除Java班那么学生表中的cid就失去了关联
删除Java班 MySQL命令：
```delete from student where classid=(select cid from class where cname='Java');
delete from class where cname='Java';
```

# 十四、多表连接查询
## 1.交叉连接查询
交叉连接返回的结果是被连接的两个表中所有数据行的笛卡儿积；比如：集合A={a,b}，集合B={0,1,2}，则集合A和B的笛卡尔积为{(a,0),(a,1),(a,2),(b,0),(b,1),(b,2)}。所以，交叉连接也被称为笛卡尔连接，其语法格式如下：
```SELECT * FROM 表1 CROSS JOIN 表2;
```

## 2.内连接查询
内连接(Inner Join)又称简单连接或自然连接，是一种非常常见的连接查询。内连接使用比较运算符对两个表中的数据进行比较并列出与连接条件匹配的数据行，组合成新的 记录。也就是说在内连接查询中只有满足条件的记录才能出现在查询结果中。其语法格式如下：
```SELECT 查询字段1,查询字段2, ... FROM 表1 [INNER] JOIN 表2 ON 表1.关系字段=表2.关系字段
```
## 3.外连接查询

在使用内连接查询时我们发现：返回的结果只包含符合查询条件和连接条件的数据。但是，有时还需要在返回查询结果中不仅包含符合条件的数据，而且还包括左表、右表或两个表中的所有数据，此时我们就需要使用外连接查询。外连接又分为左(外)连接和右(外)连接。其语法格式如下：

```SELECT 查询字段1,查询字段2, ... FROM 表1 LEFT | RIGHT [OUTER] JOIN 表2 ON 表1.关系字段=表2.关系字段 WHERE 条件
```

***1、LEFT [OUTER] JOIN 左(外)连接：返回包括左表中的所有记录和右表中符合连接条件的记录。***
***2、RIGHT [OUTER] JOIN 右(外)连接：返回包括右表中的所有记录和左表中符合连接条件的记录。***

### 3.1 左（外）连接查询 
左(外)连接的结果包括LEFT JOIN子句中指定的左表的所有记录，以及所有满足连接条件的记录。如果左表的某条记录在右表中不存在则在右表中显示为空。

### 3.2 右（外）连接查询
右(外)连接的结果包括RIGHT JOIN子句中指定的右表的所有记录，以及所有满足连接条件的记录。如果右表的某条记录在左表中没有匹配，则左表将返回空值。


# 十五、子查询
子查询是指一个查询语句嵌套在另一个查询语句内部的查询；该查询语句可以嵌套在一个 SELECT、SELECT…INTO、INSERT…INTO等语句中。在执行查询时，首先会执行子查询中的语句，再将返回的结果作为外层查询的过滤条件。在子査询中通常可以使用比较运算符和IN、EXISTS、ANY、ALL等关键字。
## 1.带比较运算符的子查询

```select * from class where cid=(select classid from student where sname='张三');
```


## 2.带EXISTS关键字的子查询
EXISTS关键字后面的参数可以是任意一个子查询， 它不产生任何数据只返回TRUE或FALSE。当返回值为TRUE时外层查询才会执行
```假如王五同学在学生表中则从班级表查询所有班级信息 MySQL命令：
select * from class where exists (select * from student where sname='王五');
```
## 3.带ANY关键字的子查询
ANY关键字表示满足其中任意一个条件就返回一个结果作为外层查询条件。

 ```查询比任一学生所属班级号还大的班级编号 MySQL命令：
select * from class where cid > any (select classid from student);
```
## 4.带ALL关键字的子查询
ALL关键字与ANY有点类似，只不过带ALL关键字的子査询返回的结果需同时满足所有内层査询条件。
```查询比所有学生所属班级号还大的班级编号 MySQL命令：
select * from class where cid > all (select classid from student);
```



# 总结
## 重要（从关键字分析）：
## 查询语句的书写顺序和执行顺序
select ===> from ===> where ===> group by ===> having ===> order by ===> limit
## 查询语句的执行顺序
from ===> where ===> group by ===> having ===> select ===> order by ===> limi

















