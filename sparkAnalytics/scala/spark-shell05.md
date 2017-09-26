# run  spark commands interactively from the shell
Start the Spark Shell

    $ spark-shell
you should see spark-shell start up and present the scala> prompt
...
Type :help for more information.

scala> 


make sure your json is in your correct directory and/or have exported your json data.
    $ mongoexport --db finstream --collection findata --out findata.json

    scala> val findata = spark.read.json("findata.json") 
    findata: org.apache.spark.sql.DataFrame = [_id: struct<$oid: string>, days_high: string ... 6 more fields]
    
    scala> findata.printSchema()
    root
    |-- _id: struct (nullable = true)
    |    |-- $oid: string (nullable = true)
    |-- days_high: string (nullable = true)
    |-- days_low: string (nullable = true)
    |-- open: string (nullable = true)
    |-- price: string (nullable = true)
    |-- symbol: string (nullable = true)
    |-- ts: string (nullable = true)
    |-- volume: string (nullable = true)
    
    scala> findata.filter("symbol = 'YHOO'").show()  // Looks like a SQL Where Clause
    +--------------------+---------+--------+-----+-----+------+-------------------+-------+
    |                 _id|days_high|days_low| open|price|symbol|                 ts| volume|
    +--------------------+---------+--------+-----+-----+------+-------------------+-------+
    |[57b4e454a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:25:24|7679916|
    |[57b4e459a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:25:29|7679916|
    |[57b4e45ea8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:25:34|5999403|
    |[57b4e464a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:25:40|7679791|
    |[57b4e469a8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:25:44|5999403|
    |[57b4e47ba8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:25:51|7679916|
    |[57b4e481a8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:26:09|5999403|
    |[57b4e486a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:14|7679916|
    |[57b4e48da8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:26:20|5999403|
    |[57b4e492a8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:26:25|5999403|
    |[57b4e497a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:31|7679916|
    |[57b4e49da8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:36|7679791|
    |[57b4e4a3a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:43|7679791|
    |[57b4e4a8a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:48|7679791|
    |[57b4e4aea8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:26:53|7679791|
    |[57b4e4b3a8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:26:59|5999403|
    |[57b4e56fa8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:30:07|7679916|
    |[57b4e574a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:30:12|7679791|
    |[57b4e579a8496a0c...|    42.75|   42.39|42.39|42.70|  YHOO|2016-08-17T17:30:17|7679791|
    |[57b4e57fa8496a0c...|    42.75|   42.39|42.39|42.68|  YHOO|2016-08-17T17:30:22|5999403|
    +--------------------+---------+--------+-----+-----+------+-------------------+-------+
    only showing top 20 rows
    
    scala>findata.count()   // select count(1) from table_name.
    res2: Long = 1236
    
    scala> findata.filter("symbol = 'YHOO'").groupBy("days_high").count().show()  // sql aggregation
    +---------+-----+                                                               
    |days_high|count|
    +---------+-----+
    |    41.98|    5|
    |    42.75|   22|
    |    43.10|    1|
    |  41.9800|    8|
    |    42.24|  119|
    |    42.77|   24|
    +---------+-----+
    
    scala> findata.select("symbol","days_low", "days_high").orderBy("symbol").show()
    +------+--------+---------+
    |symbol|days_low|days_high|
    +------+--------+---------+
    |  AAPL|  108.34|   109.37|
    |  AAPL|  107.68|   108.75|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|  108.34|   109.37|
    |  AAPL|    null|     null|
    |  AAPL|  107.68|   108.75|
    |  AAPL|  107.68|   108.75|
    |  AAPL|  106.68|   107.80|
    +------+--------+---------+
    only showing top 20 rows
    
    scala> findata.createOrReplaceTempView("stockdata")   // create a sql table
    
    scala> val sqlFinDF = spark.sql("Select symbol, ts, days_high, days_low, open, price, volume FROM stockdata")
    sqlFinDF: org.apache.spark.sql.DataFrame = [symbol: string, ts: string ... 5 more fields]
    
    scala> sqlFinDF.show()
    +------+-------------------+---------+--------+------+------+--------+
    |symbol|                 ts|days_high|days_low|  open| price|  volume|
    +------+-------------------+---------+--------+------+------+--------+
    |  YHOO|2016-08-17T17:25:24|    42.75|   42.39| 42.39| 42.70| 7679916|
    |   HPE|2016-08-17T17:25:25|   22.190|  21.480|22.060|21.655| 8194778|
    |  AMZN|2016-08-17T17:25:26|   765.22|  759.20|764.41|764.63| 1886940|
    |  AAPL|2016-08-17T17:25:26|   109.37|  108.34|109.10|109.22|25329176|
    | GOOGL|2016-08-17T17:25:27|   805.63|  796.30|800.00|805.42| 1064597|
    |  TWTR|2016-08-17T17:25:28|    20.44|   19.90| 20.43| 20.17|22749967|
    |    FB|2016-08-17T17:25:29|   124.38|  122.85|123.66|124.37|13759802|
    |  YHOO|2016-08-17T17:25:29|    42.75|   42.39| 42.39| 42.70| 7679916|
    |   HPE|2016-08-17T17:25:30|   22.190|  21.480|22.060|21.655| 8194778|
    |  AMZN|2016-08-17T17:25:31|   765.22|  759.20|764.41|764.63| 1887047|
    |  AAPL|2016-08-17T17:25:31|   109.37|  108.34|109.10|109.22|25329176|
    | GOOGL|2016-08-17T17:25:32|   805.63|  796.30|800.00|803.47|  784774|
    |  TWTR|2016-08-17T17:25:33|   20.440|  19.900|20.430|20.155|20272510|
    |    FB|2016-08-17T17:25:34|   124.38|  122.85|123.66|124.37|13759802|
    |  YHOO|2016-08-17T17:25:34|    42.75|   42.39| 42.39| 42.68| 5999403|
    |   HPE|2016-08-17T17:25:35|    22.19|   21.48| 22.06| 21.64| 9769586|
    |  AMZN|2016-08-17T17:25:36|   765.22|  759.20|764.41|764.63| 1887047|
    |  AAPL|2016-08-17T17:25:36|   109.37|  108.34|109.10|109.22|25267644|
    | GOOGL|2016-08-17T17:25:37|   805.63|  796.30|800.00|803.47|  784774|
    |  TWTR|2016-08-17T17:25:38|    20.44|   19.90| 20.43| 20.17|22741516|
    +------+-------------------+---------+--------+------+------+--------+
    only showing top 20 rows
    
    scala> val aggDF = findata.groupBy("SYMBOL").agg(avg(findata("PRICE")), max(findata("PRICE")))
    aggDF: org.apache.spark.sql.DataFrame = [SYMBOL: string, avg(PRICE): double ... 1 more field]
    
    scala> aggDF.show()
    +------+------------------+----------+                                          
    |SYMBOL|        avg(PRICE)|max(PRICE)|
    +------+------------------+----------+
    |  AAPL|107.83467897727259|    109.22|
    |  YHOO| 42.09301675977656|     42.83|
    |    FB|123.92662068965515|    124.37|
    |   HPE|21.831312849162014|     22.16|
    | GOOGL| 793.6736000000003|    805.42|
    |  TWTR|18.547198857142845|     20.17|
    |  AMZN| 759.5419252808974|    764.63|
    +------+------------------+----------+
    
    ### show  a join
    val symbol = spark.read.json("symbol.json")
    val df = findata.join(symbol, findata("symbol") === symbol("symbol"))
    df.show()   
    
    scala>     df.show() 
    +--------------------+---------+--------+------+------+------+-------------------+--------+--------------------+------+
    |                 _id|days_high|days_low|  open| price|symbol|                 ts|  volume|                NAME|symbol|
    +--------------------+---------+--------+------+------+------+-------------------+--------+--------------------+------+
    |[57b4e454a8496a0c...|    42.75|   42.39| 42.39| 42.70|  YHOO|2016-08-17T17:25:24| 7679916|               Yahoo|  YHOO|
    |[57b4e455a8496a0c...|   22.190|  21.480|22.060|21.655|   HPE|2016-08-17T17:25:25| 8194778|Hewlet Packard En...|   HPE|
    |[57b4e456a8496a0c...|   765.22|  759.20|764.41|764.63|  AMZN|2016-08-17T17:25:26| 1886940|              AMAZON|  AMZN|
    |[57b4e457a8496a0c...|   805.63|  796.30|800.00|805.42| GOOGL|2016-08-17T17:25:27| 1064597|              Google| GOOGL|
    |[57b4e458a8496a0c...|    20.44|   19.90| 20.43| 20.17|  TWTR|2016-08-17T17:25:28|22749967|             Twitter|  TWTR|
    |[57b4e459a8496a0c...|   124.38|  122.85|123.66|124.37|    FB|2016-08-17T17:25:29|13759802|            Facebook|    FB|
    |[57b4e459a8496a0c...|    42.75|   42.39| 42.39| 42.70|  YHOO|2016-08-17T17:25:29| 7679916|               Yahoo|  YHOO|
    |[57b4e45aa8496a0c...|   22.190|  21.480|22.060|21.655|   HPE|2016-08-17T17:25:30| 8194778|Hewlet Packard En...|   HPE|
    |[57b4e45ba8496a0c...|   765.22|  759.20|764.41|764.63|  AMZN|2016-08-17T17:25:31| 1887047|              AMAZON|  AMZN|
    |[57b4e45ca8496a0c...|   805.63|  796.30|800.00|803.47| GOOGL|2016-08-17T17:25:32|  784774|              Google| GOOGL|
    |[57b4e45da8496a0c...|   20.440|  19.900|20.430|20.155|  TWTR|2016-08-17T17:25:33|20272510|             Twitter|  TWTR|
    |[57b4e45ea8496a0c...|   124.38|  122.85|123.66|124.37|    FB|2016-08-17T17:25:34|13759802|            Facebook|    FB|
    |[57b4e45ea8496a0c...|    42.75|   42.39| 42.39| 42.68|  YHOO|2016-08-17T17:25:34| 5999403|               Yahoo|  YHOO|
    |[57b4e45fa8496a0c...|    22.19|   21.48| 22.06| 21.64|   HPE|2016-08-17T17:25:35| 9769586|Hewlet Packard En...|   HPE|
    |[57b4e460a8496a0c...|   765.22|  759.20|764.41|764.63|  AMZN|2016-08-17T17:25:36| 1887047|              AMAZON|  AMZN|
    |[57b4e461a8496a0c...|   805.63|  796.30|800.00|803.47| GOOGL|2016-08-17T17:25:37|  784774|              Google| GOOGL|
    |[57b4e462a8496a0c...|    20.44|   19.90| 20.43| 20.17|  TWTR|2016-08-17T17:25:38|22741516|             Twitter|  TWTR|
    |[57b4e463a8496a0c...|   124.38|  122.85|123.66|124.37|    FB|2016-08-17T17:25:39|13759802|            Facebook|    FB|
    |[57b4e464a8496a0c...|    42.75|   42.39| 42.39| 42.70|  YHOO|2016-08-17T17:25:40| 7679791|               Yahoo|  YHOO|
    |[57b4e464a8496a0c...|    22.19|   21.48| 22.06| 21.64|   HPE|2016-08-17T17:25:40| 9769586|Hewlet Packard En...|   HPE|
    +--------------------+---------+--------+------+------+------+-------------------+--------+--------------------+------+

    // create sql views from df's
    findata.createOrReplaceTempView("stockdata")
    symbol.createOrReplaceTempView("stocksym")
    val sqlALLDF = spark.sql("SELECT * FROM stockdata")
    val sqlSELDF = spark.sql("Select symbol, ts, days_high, days_low, open, price, volume FROM stockdata")
    val symDF = spark.sql("Select distinct symbol from stockdata")
    val symbolDF = spark.sql("Select distinct name, symbol from stocksym")
    // select * from df1 a inner join df2 b on a.key = b.key inner join df3 c on a.key =c.key”)
    val joinSQLDF = spark.sql("Select * from stockdata a inner join stocksym b on a.symbol = b.symbol")
    // show results
    sqlALLDF.show()
    sqlSELDF.show()
    sqlFinDF.show()
    symbolDF.show()

    
    
### Have fun, Wow your friends!
