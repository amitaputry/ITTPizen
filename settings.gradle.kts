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
