# 一、使用

![](https://meethigher.top/blog/2022/annotation-convert/image-20221023231625919.png)

[源码地址](https://github.com/meethigher/object-converter)

```sh
# 仅打包
mvn clean install
# 打包并附带源码
mvn clean source:jar install
```

或者直接使用即可，已发布到Maven中央仓库，仅14KB。

```xml
<dependency>
    <groupId>top.meethigher</groupId>
    <artifactId>object-converter</artifactId>
    <version>1.1</version>
</dependency>
```

# 二、参考致谢

[动态代理 - 廖雪峰的官方网站](https://www.liaoxuefeng.com/wiki/1252599548343744/1264804593397984)

[mybatis/mybatis-3: MyBatis SQL mapper framework for Java](https://github.com/mybatis/mybatis-3)

[killme2008/aviatorscript: A high performance scripting language hosted on the JVM.](https://github.com/killme2008/aviatorscript)

[FK7075/lucky: 重构后的Lucky，尝试将Lucky中的各个功能模块独立出来成为单独的模块](https://github.com/FK7075/lucky)
