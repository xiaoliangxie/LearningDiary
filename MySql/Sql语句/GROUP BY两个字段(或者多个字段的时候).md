#### GROUP BY 两个字段（或者多个字段的时候）
---
> 当group by 一个字段的时候：
1. group by 一般和聚合函数一起使用才有意义,比如 count sum avg等,使用group by的两个要素:
2. 出现在select后面的字段 要么是是聚合函数中的,要么就是group by 中的.
3. 要筛选结果 可以先使用where 再用group by 或者先用group by 再用having

> 那么当group by 后面后两个字段的时候呢:
1. 先根据B字段进行分组，如果B能分，A 也能分 就分成同一组
>[参考文章](https://www.cnblogs.com/mc67/p/5050900.html) 