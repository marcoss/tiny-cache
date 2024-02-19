load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary")

kt_jvm_binary(
    name = "tinycache",
    srcs = [
        "TinyCache.kt",
    ],
    main_class = "tinycache.TinyCacheKt",
    deps = [
        "@maven//:io_ktor_ktor_server_core_jvm",
        "@maven//:io_ktor_ktor_server_netty_jvm",
    ],
)
