pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "PestPatrol"
include(":app")

// Core
include(":core:network")
include(":core:common")
include(":core:feature_api")

// Auth
include(":features:auth:auth_data")
include(":features:auth:auth_domain")
include(":features:auth:auth_presentation")

// Home
include(":features:home:home_data")
include(":features:home:home_domain")
include(":features:home:home_presentation")
