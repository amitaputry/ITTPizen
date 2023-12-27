import java.net.URI

include(":common")

include(":feature-post")


include(":feature-chat")


include(":feature-connection")


include(":feature-home")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
//        maven {
//            url = URI.create("https://s01.oss.sonatype.org/content/repositories/snapshots")
//        }
        maven {
            url = URI.create("https://jitpack.io")
        }
        google()
        mavenCentral()
    }
}

rootProject.name = "ITTPizen"
include(":app")
include(":ui")
include(":feature-splash-screen")
include(":feature-onboarding-screen")
include(":feature-auth")
include(":navigation")
include(":domain")
include(":feature-job")
include(":feature-main")
