load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary", "kt_jvm_library")

kt_jvm_library(
    name = "tinycache_lib",
    srcs = glob([
        "*.kt",
    ]),
    deps = [
        "@maven//:io_ktor_ktor_server_core_jvm",
        "@maven//:io_ktor_ktor_server_netty_jvm",
    ],
)

kt_jvm_binary(
    name = "tinycache",
    main_class = "tinycache.AppKt",
    runtime_deps = [
        ":tinycache_lib",
    ],
)
