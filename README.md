
Lucene v4 Index Provider for Neo4j
==================================

This project ports version 1.9.M01 of
[neo4j-lucene-index](https://github.com/neo4j/community/tree/master/lucene-index)
from Lucene 3.5 to Lucene 4. It has not been thoroughly tested yet, but it seems
to work well. Once `neo4j-lucene4-index` is cloned and installed (`mvn
install`), you can use it from maven with the following dependencies:

  ```
  <dependency>
    <groupId>org.neo4j</groupId>
    <artifactId>neo4j-community</artifactId>
    <version>1.9.M01</version>
    <exclusions>
      <exclusion>
        <groupId>org.neo4j</groupId>
        <artifactId>neo4j-lucene-index</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
  
  <dependency>
    <groupId>com.keatext</groupId>
    <artifactId>neo4j-lucene4-index</artifactId>
    <version>1.9.M01-SNAPSHOT</version>
  </dependency>
  ```
