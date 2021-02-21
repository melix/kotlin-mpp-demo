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
    macosX64("native-mac") {
        binaries {
            executable()
        }
    }
    linuxX64("native-linux") {
        binaries {
            executable()
        }
    }
    mingwX64("native-win") {
        binaries {
            executable()
        }
    }
}

