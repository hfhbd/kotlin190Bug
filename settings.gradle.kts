rootProject.name = "untitled"

plugins {
    id("com.gradle.enterprise") version "3.13.2"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}
