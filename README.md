# Safewind Website Backend

## 检查单

有些信息重复出现在版本库多处，更新时需要对照检查单同时更新所有处。

### 服务器端口号

- `Safewind-starter` 配置中的端口号
- `./script/just_package.dockerfile` 中的开放端口号
- `Dockerfile` 中的开放端口号

### 数据库变更

- 数据库结构变更须同时变更 `./script/sql/basic_schema.sql`
- 数据库初始数据变更同时须变更 `./script/sql/basic_data.sql`

### 版本号变更

- `pom.xml` 中的 `reversion` 属性
- `VERSION` 文件

### 本地环境变更

- `compose.yaml` 中 `safewind-backend` 服务的定义
- `./script/local_env.sh` 中的环境变量定义

## 构建

### 打包

该项目依赖于 `Redis` 和 `MySQL`，你需要通过环境变量配置这些设施：

- `REDIS_HOST` Redis服务器地址
- `REDIS_PORT` Redis服务器端口
- `REDIS_PASSWORD` Redis服务器密码，默认用户
- `MYSQL_URL` MySQL服务器地址。数据库safe-wind。
- `MYSQL_USERNAME` MySQL用户名
- `MYSQL_PASSWORD` MySQL密码

假设您已 `.env` 文件的方式，以每行一条 `VARIABLE_NAME=VALUE` 的格式给出了这些值。你可以这样打包。

```bash
export $(xargs <./.env)
mvn install
mvn package spring-boot:repackage
# 或者，只运行，你可以使用以下命令
mvn spring-boot:run
````

### Docker 镜像

可以通过 Docker 在本地构建打包运行该项目：

```bash
docker compose up
```

但是因为 Docker 镜像中缺少 `maven` 本地仓库，你可能想要在本地编译打包，然后构建 Docker 镜像：

```bash
# 构建
./script/build_image_just_pack.sh
# 执行
docker compose up
```

### 数据库

- MySQL数据库，可以使用 `./script/sql/basic_schema.sql` 脚本初始化。