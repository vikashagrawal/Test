import org.apache.spark.sql.SparkSession

object Main {
  val spark = SparkSession.builder().config("spark.default.parallelism", 2)
    .appName("Test")
    .enableHiveSupport().getOrCreate()
  def main(args: Array[String]): Unit = {
    val data = Seq(("James ", "", "Smith", "36636", "M", 3000),
      ("Michael ", "Rose", "", "40288", "M", 4000),
      ("Robert ", "", "Williams", "42114", "M", 4000),
      ("Maria ", "Anne", "Jones", "39192", "F", 4000),
      ("Jen", "Mary", "Brown", "", "F", -1)
    )

    val columns = Seq("firstname", "middlename", "lastname", "dob", "gender", "salary")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns: _*)
    df.show()
    println(df.printSchema())
    //df.write.parquet("/user/g535024/people.parquet")
    println("file created")
    val parqDF = spark.read.parquet("/user/g535024/people.parquet")
    println("file read")
    parqDF.createOrReplaceTempView("ParquetTable")
    //spark.sql("select * from ParquetTable where salary >= 4000").explain()
    val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")
    parkSQL.show()
    parkSQL.printSchema()
    println(parqDF.count())
    parqDF.write.format("com.databricks.spark.avro").save("/user/g535024/people111.avro")
    println("avro created")
  }

}
