/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build and run tests") {
    container(displayName = "Gradle build", image = "openjdk:11") {
        shellScript {
            content = """
            		./gradlew test
                    ./gradlew installDist
                    cp -r build $mountDir/share
                """
        }
    }
    
    docker {
        beforeBuildScript {
            content = """
            		cp -r $mountDir/share build
                    echo show dirs:
                    ls
                    ls build 
                    ls build/build/install
                """
        }
        build {
            context = "docker"
            file = "./docker/Dockerfile"
        }

        push("rem-coil.registry.jetbrains.space/p/popper/popper/myimage") {
            tags("0.0.\$JB_SPACE_EXECUTION_NUMBER", "latest")
        }
    }
}
