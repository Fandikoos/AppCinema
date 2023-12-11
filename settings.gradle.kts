import org.gradle.internal.impldep.org.jsoup.safety.Safelist.basic

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

        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                // Do not change the username below.
                // This should always be `mapbox` (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = "sk.eyJ1IjoiZGFuaWZhbmxlZ2Fzb3Y4IiwiYSI6ImNscHNqdWdtdTA0a3Mya21sdHhtZjV5aTgifQ.y3aXBLWYah7sRdfz1wDJ8g"
            }
        }
    }
}

rootProject.name = "AppCinema"
include(":app")
 