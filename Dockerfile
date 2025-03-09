# 使用JDK 17的官方镜像（推荐slim版本以减小体积）
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 创建日志目录（需与logback配置中的路径一致）
RUN mkdir -p /app/logs/archive

# 复制JAR文件到镜像中（假设JAR文件名为app.jar）
COPY target/tblog-demo-boot-0.0.1-SNAPSHOT.jar tblog-demo-boot-0.0.1.jar

# 暴露端口（根据实际端口修改）
EXPOSE 8888

# 启动应用（JDK 17无需特殊参数）
ENTRYPOINT ["java", "-jar", "tblog-demo-boot-0.0.1.jar"]