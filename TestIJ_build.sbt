name := "TestIJ"

version := "0.1"

scalaVersion := "2.11.8"

resolvers += "Cloudera Repo" at "https://repository.cloudera.com/artifactory/cloudera-repos/"

val cdhVersion = "cdh5.11.1"
val sparkVersion = "2.3.0"
val hadoopVersion = s"2.6.0-$cdhVersion"
val sparkCdhVersion = s"$sparkVersion-$cdhVersion"
val spark2CdhVersion = "2.3.0.cloudera4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % spark2CdhVersion % "provided",
  """org.apache.spark""" %% "spark-sql" % spark2CdhVersion % "provided",
  "com.databricks" %% "spark-avro" % "4.0.0"
)
