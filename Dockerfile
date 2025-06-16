FROM eclipse-temurin:17-jre-alpine

# 1. 预创建所有目录（root阶段）
RUN mkdir -p /app/logs/gc && \
    chown -R 1000:1000 /app && \
    chmod -R 777 /app

# 2. 移除时区设置（节省10MB+）
ENV TZ=Asia/Shanghai

# 3. 使用更小的基础镜像
# FROM bellsoft/liberica-openjre-alpine:17.0.8-7  # 比eclipse镜像小20MB

WORKDIR /app

# 4. 使用普通用户UID运行（不创建用户）
USER 1000

COPY --chown=1000:1000 target/tblog-demo-boot-*.jar app.jar

# 5. 激进内存参数
ENV JAVA_OPTS="-XX:+UseSerialGC \
               -Xmx64m \
               -XX:MaxMetaspaceSize=128m \
               -XX:CompressedClassSpaceSize=32m \
               -XX:+DisableExplicitGC \
               -XX:+HeapDumpOnOutOfMemoryError \
               -XX:HeapDumpPath=/app/logs/gc/heapdump.hprof"

EXPOSE 8888

# 6. 禁用JMX和agent
ENTRYPOINT exec java $JAVA_OPTS -jar -Dspring.jmx.enabled=false app.jar