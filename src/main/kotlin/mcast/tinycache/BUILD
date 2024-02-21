load("@rules_kotlin//kotlin:core.bzl", "kt_compiler_plugin")
load("@rules_kotlin//kotlin:jvm.bzl", "kt_jvm_binary", "kt_jvm_library")

kt_compiler_plugin(
    name = "serialization_plugin",
    compile_phase = True,
    id = "org.jetbrains.kotlin.serialization",
    stubs_phase = True,
    deps = [
        "@rules_kotlin//kotlin/compiler:kotlinx-serialization-compiler-plugin",
    ],
)

kt_jvm_library(
    name = "tinycache_lib",
    srcs = glob([
        "*.kt",
    ]),
    plugins = [":serialization_plugin"],
    deps = [
        "@maven//:ch_qos_logback_logback_classic",
        "@maven//:io_ktor_ktor_serialization_kotlinx_json_jvm",
        "@maven//:io_ktor_ktor_server_content_negotiation_jvm",
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
