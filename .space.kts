job("Build and publish docker") {

    startOn {
        /*
        gitPush {
            repository = "web"
            branchFilter {
                +"release"
            }
        }
        */
        gitPush {
            repository = "services"
            branchFilter {
                +"release"
            }
        }
    }

    /*
    git("web") {
        refSpec = "release"
        container(displayName = "Flutter Web Build", image = "cirrusci/flutter:2.8.1") {
            shellScript {
                content = """
                	cd /mnt/space/work/web
            		flutter build web
                    cp -r ./build/web $mountDir/share
                """
            }
        }
    }
    cp -r -f $mountDir/share/web src/main/resources
	*/

    container(displayName = "Gradle test and build", image = "gradle:6.8-jdk11") {
        shellScript {
            content = """       		
            		./gradlew test
                    ./gradlew installDist
                    cp -r ./build/install/popper $mountDir/share
                    ls -R $mountDir/share
                """
        }
    }

    docker {
        beforeBuildScript {
            content = "cp -r $mountDir/share/popper ./"
        }
        build {
            context = "docker"
            file = "./docker/Dockerfile"
        }

        push("rem-coil.registry.jetbrains.space/p/popper/popper/main-service") {
            tags("0.0.\$JB_SPACE_EXECUTION_NUMBER", "latest")
        }
    }
}
