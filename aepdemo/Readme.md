SDK使用说明请参考《AEP开放平台开发指引》中“SDK使用说明”章节

```
pom 依赖安装
mvn install:install-file -Dfile=ctg-ag-sdk-core-${SDK版本}.jar -DpomFile=ctg-ag-sdk-core-${SDK版本}.pom.xml

mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file -Dfile=ag-sdk-biz-${SDK版本}-SNAPSHOT.jar -DpomFile=ag-sdk-biz-${SDK版本}-SNAPSHOT.pom.xml
```

mvn install:install-file -Dfile=ctg-ag-sdk-core-2.5.0-20210302.082536-39.jar -DpomFile=ctg-ag-sdk-core-2.5.0-20210302.082536-39.pom.xml

mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file -Dfile=ag-sdk-biz-62152.tar.gz-20210610.115230-SNAPSHOT.jar -DpomFile=ag-sdk-biz-62152.tar.gz-20210610.115230-SNAPSHOT.pom.xml
