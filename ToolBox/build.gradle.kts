import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.wuxianggujun"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                // https://mvnrepository.com/artifact/org.jetbrains.kotlinx/kotlinx-coroutines-swing
                //runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.6.4")
                // https://mvnrepository.com/artifact/org.jetbrains.compose.components/components-splitpane-desktop 
                //implementation("org.jetbrains.compose.components:components-splitpane-desktop:1.1.1")
                implementation("uk.co.caprica:vlcj:4.7.0")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "ToolBox"
            packageVersion = "1.0.0"
        }
    }
}
