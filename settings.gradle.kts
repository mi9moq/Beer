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
    dependencyResolutionManagement {
        versionCatalogs {
            create("libs") {
                from(files("libs.toml"))
            }
        }
    }
}

rootProject.name = "BeerApp"
include(":app")
 