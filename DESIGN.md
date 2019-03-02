![](https://gist.github.com/morriship/4fcd598cf98c4652f30b9bfa537a0e7e/raw/faad853144f766292fe5adc843889d042ec51c2b/design-sample.jpg)

Accroading to the requirement, this solution should satisfy both availability and scalability with high throughput. The explanation of the functionality of some components is listed below.


### Log Collecting Servers

Receive log data from client's machines and send those to Kafka servers. Work on basic verification and filtering to prevent from bots or attacks. It should store all the logs into local storage if it can't connect to the Kafka servers.

### Web & API Servers

Serves for the querying of matrics from clients.

### Kafka

The source of all the raw data. Also serves as a buffer by storing data for 5 to 7 days.

### Spark Streaming

Streaming processing(micro batch). Calculate real-time data and record the result to the OpenTSDB with minimum latency.

### Airflow

Use Airflow to manage all the batch process. Not only use spark and hadoop but also use any kind of processing tools and methods. It has two major responsibility.

1. Data management.
    - Move raw logs from Kafka HDFS. The data should be stored in time related path format such as `/rawlog/yyyy/mm/dd/hh`.
    - Reperforming logs for streaming processing by moving raw data from HDFS to Kafka.

1. Batch processing.
    - Work on some batch processing logics such as batch aggregation and data join.


### Cassandra

Cassandra is used for storing the result of the batch processing on this design, but it's really depending on the use case.

### HBase

HBase is the storage of the OpenTSDB. It is better to arrage an isolated cluster of both storage and processing resource for HBase for good stability.
