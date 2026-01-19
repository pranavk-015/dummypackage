plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    `maven-publish`
}

android {
    namespace = "com.example.dummy"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
        targetSdk = 36
        version = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        buildConfig = false
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
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("maven") {
                groupId = "io.tutelar"  // your verified namespace
                artifactId = "android"
                version = "1.0.0"

                // The AAR artifact
                artifact("$buildDir/outputs/aar/${project.name}-release.aar")

                // Sources jar
                artifact(tasks.register<Jar>("sourcesJar") {
                    archiveClassifier.set("sources")
                    from(android.sourceSets["main"].java.srcDirs)
                })

                // Optional: javadoc jar
                artifact(tasks.register<Jar>("javadocJar") {
                    archiveClassifier.set("javadoc")
                    // add Javadoc output if needed
                })

                pom {
                    name.set("MyLibrary")
                    description.set("A description of my Android library")
                    url.set("https://github.com/pranav/mylibrary")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("pranav")
                            name.set("Pranav K")
                            email.set("pranav@example.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:git://github.com/pranav/mylibrary.git")
                        developerConnection.set("scm:git:ssh://github.com/pranav/mylibrary.git")
                        url.set("https://github.com/pranav/mylibrary")
                    }
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