load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

KTOR_VERSION = "2.3.8"

KOTLINX_SERIALIZATION_VERSION = "1.6.0"

LOGBACK_VERSION = "1.5.0"

# --------------------------------------------------------------
# rules_jvm_external
# --------------------------------------------------------------
RULES_JVM_EXTERNAL_TAG = "6.0"

RULES_JVM_EXTERNAL_SHA = "85fd6bad58ac76cc3a27c8e051e4255ff9ccd8c92ba879670d195622e7c0a9b7"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/releases/download/%s/rules_jvm_external-%s.tar.gz" % (RULES_JVM_EXTERNAL_TAG, RULES_JVM_EXTERNAL_TAG),
)

load("@rules_jvm_external//:repositories.bzl", "rules_jvm_external_deps")

rules_jvm_external_deps()

load("@rules_jvm_external//:setup.bzl", "rules_jvm_external_setup")

rules_jvm_external_setup()

load("@rules_jvm_external//:defs.bzl", "maven_install")

maven_install(
    artifacts = [
        "ch.qos.logback:logback-classic:%s" % LOGBACK_VERSION,
        "io.ktor:ktor-server-core-jvm:%s" % KTOR_VERSION,
        "io.ktor:ktor-server-content-negotiation-jvm:%s" % KTOR_VERSION,
        "org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:%s" % KOTLINX_SERIALIZATION_VERSION,
        "io.ktor:ktor-serialization-kotlinx-json-jvm:%s" % KTOR_VERSION,
        "io.ktor:ktor-server-netty-jvm:%s" % KTOR_VERSION,
        # "junit:junit:5.10.2",
    ],
    fetch_sources = True,
    repositories = [
        "https://maven.google.com",
        "https://repo.maven.apache.org/maven2",
        "https://repo1.maven.org/maven2",
    ],
    strict_visibility = True,
)

# --------------------------------------------------------------
# rules_kotlin
# --------------------------------------------------------------
RULES_KOTLIN_VERSION = "1.9.0"

RULES_KOTLIN_SHA = "5766f1e599acf551aa56f49dab9ab9108269b03c557496c54acaf41f98e2b8d6"

http_archive(
    name = "rules_kotlin",
    sha256 = RULES_KOTLIN_SHA,
    urls = ["https://github.com/bazelbuild/rules_kotlin/releases/download/v%s/rules_kotlin-v%s.tar.gz" % (RULES_KOTLIN_VERSION, RULES_KOTLIN_VERSION)],
)

load("@rules_kotlin//kotlin:repositories.bzl", "kotlin_repositories")

kotlin_repositories()

load("@rules_kotlin//kotlin:core.bzl", "kt_register_toolchains")

kt_register_toolchains()
