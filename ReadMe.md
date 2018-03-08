# JCustomOpc.dll

只找到 x86 版本：
1. 可自行编译 x64 版本；
2. 使用 x86 jvm 去编译和运行；

如果版本不匹配的话，会报错：
> Can't load IA 32-bit .dll on a AMD 64-bit platform

# 安装 x86 jdk

通过 exe 去安装 jdk 时，会自动安装该版本的 jre，这时决不能将安装目录更改为 jdk 对应的目录，否则会破坏已安装 jdk。导致 maven 配置
jdk 时报错：
> The selected directory is not a valid home for JDK 

# OpcServer 模拟器： MatrikonOPCSimulation.exe

安装模拟器，进行测试。

# WebServer

Spring5 + Embed Tomcat

只实现了两个最简单的接口，ping，read



