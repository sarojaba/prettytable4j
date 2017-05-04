# PrettyTable4J

PrettyTable4J is a CLI based library for printing tables on the console.
This project is inspired from the Python prettytable module.

## Basic Usage

```java
PrettyTable pt = PrettyTable
    .fieldNames("name", "age", "city")
    .addRow("john", 22, "new york")
    .addRow("elizabeth", 43, "chicago")
    .addRow("bill", 31, "atlanta")
    .addRow("mary", 18, "los angeles");

System.out.println(pt.toString());
```

```
+-----------+-----+-------------+
| name      | age | city        |
+-----------+-----+-------------+
| john      | 22  | new york    |
| elizabeth | 43  | chicago     |
| bill      | 31  | atlanta     |
| mary      | 18  | los angeles |
+-----------+-----+-------------+
```

## Sorting Table by Column

```java
pt.sortTable("age")
System.out.println(pt.toString());
```

```
+-----------+-----+-------------+
| name      | age | city        |
+-----------+-----+-------------+
| mary      |  18 | los angeles |
| john      |  22 | new york    |
| bill      |  31 | atlanta     |
| elizabeth |  43 | chicago     |
+-----------+-----+-------------+
```

Also, PrettyTable4J support to sort in descending order.

```java
pt.sortTable("age", true);
System.out.println(pt.toString());
```

```
+-----------+-----+-------------+
| name      | age | city        |
+-----------+-----+-------------+
| elizabeth |  43 | chicago     |
| bill      |  31 | atlanta     |
| john      |  22 | new york    |
| mary      |  18 | los angeles |
+-----------+-----+-------------+
```

## Deleting Data

```java
pt.deleteRow(2).deleteRow(1);

System.out.println(pt.toString());
```

```
+------+-----+-------------+
| name | age | city        |
+------+-----+-------------+
| bill |  31 | atlanta     |
| mary |  18 | los angeles |
+------+-----+-------------+
```

## Decimal mark

```java
PrettyTable pt = PrettyTable
    .fieldNames("City name", "Area", "Population", "ann")
    .addRow("Adelaide",1295, 1158259, 600.5)
    .addRow("Brisbane",5905, 1857594, 1146.4)
    .addRow("Darwin", 112, 120900, 1714.7)
    .addRow("Hobart", 1357, 205556, 619.5)
    .addRow("Sydney", 2058, 4336374, 1214.8)
    .addRow("Melbourne", 1566, 3806092, 646.9)
    .addRow("Perth", 5386, 1554769, 869.4)
    .comma(true);

System.out.println(pt.toString())        
```

```
+-----------+-------+------------+---------+
| City name | Area  | Population | ann     |
+-----------+-------+------------+---------+
| Adelaide  | 1,295 |  1,158,259 |   600.5 |
| Brisbane  | 5,905 |  1,857,594 | 1,146.4 |
| Darwin    |   112 |    120,900 | 1,714.7 |
| Hobart    | 1,357 |    205,556 |   619.5 |
| Sydney    | 2,058 |  4,336,374 | 1,214.8 |
| Melbourne | 1,566 |  3,806,092 |   646.9 |
| Perth     | 5,386 |  1,554,769 |   869.4 |
+-----------+-------+------------+---------+
```

## Hide border

```java
PrettyTable pt = PrettyTable
    .fieldNames("name", "age", "city")
    .addRow("john", 22, "new york")
    .addRow("elizabeth", 43, "chicago")
    .addRow("bill", 31, "atlanta")
    .addRow("mary", 18, "los angeles")
    .border(false);

System.out.println(pt.toString());
```

```
name      age city       
john       22 new york   
elizabeth  43 chicago    
bill       31 atlanta    
mary       18 los angeles
```