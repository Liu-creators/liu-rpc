package com.yupi.yurpc.server;

import io.vertx.core.Vertx;

/**
 * Vertx HTTP服务器
 */
public class VertxHttpServer implements HttpServer {

    /**
     * 启动服务器
     * @param port
     */
    @Override
    public void doStart(int port) {
        // 创建 Vertx.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 Http 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        server.requestHandler(new HttpServerHandler());

        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.out.println("Failed to start server: " + result.cause());
            }
        });
    }

    public static void main(String[] args) {
        new VertxHttpServer().doStart(8080);
    }
}
