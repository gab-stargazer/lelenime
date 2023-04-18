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

rootProject.name = "Lelenime"
include(":app")
include(":core:common")
include(":core:network")
include(":core:database")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":feature:explore")
include(":feature:collection")
include(":feature:detail")
include(":feature:more")
include(":benchmark")
