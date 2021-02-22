plugins {
    kotlin("multiplatform") version "1.4.30"
}

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation(kotlin("stdlib"))
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable()
        }
    }

//    macosX64("native-mac") {
//        binaries {
//            executable()
//        }
//    }
//    linuxX64("native-linux") {
//        binaries {
//            executable()
//        }
//    }
//    mingwX64("native-win") {
//        binaries {
//            executable()
//        }
//    }
}

