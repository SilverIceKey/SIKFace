plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
}

android {
    namespace = "com.sik.facecore"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}


publishing {
    publications {
        register<MavenPublication>("release") {
            afterEvaluate {
                from(components["release"])
                groupId = project.findProperty("GROUP_ID") as String
                artifactId = "facecore"
                version = project.findProperty("VERSION") as String
            }

            pom {
                name.set("SIKFace")
                description.set("Face recogination for Android")
                url.set("https://github.com/SilverIceKey/SIKFace")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("zheqian")
                        name.set("折千")
                        email.set("z516798599@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/SilverIceKey/SIKFace.git")
                    developerConnection.set("scm:git:ssh://github.com/SilverIceKey/SIKFace.git")
                    url.set("https://github.com/SilverIceKey/SIKFace")
                }
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}