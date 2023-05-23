import org.jetbrains.kotlin.gradle.plugin.mpp.*

plugins {
    kotlin("multiplatform") version "1.8.21" // "1.9.0-Beta"
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)

    fun KotlinNativeTarget.config(os: String) {
        binaries.sharedLib()

        compilations.named("main") {
            cinterops {
                register("jni") {
                    val javaHome: Provider<Directory> = providers.systemProperty("java.home").flatMap {
                        layout.dir(provider { File(it) })
                    }
                    includeDirs(
                        javaHome.map { it.dir("include") },
                        javaHome.map { it.dir("include/$os") },
                    )
                    defFile(file("src/nativeInterop/cinterop/jni.def"))
                }
            }
        }
    }

    linuxX64 {
        config("linux")
    }
}
