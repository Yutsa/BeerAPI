#!/usr/bin/env groovy


// Ne garder que 5 builds et 5 artefacts
properties([buildDiscarder(logRotator( artifactNumToKeepStr: '5', numToKeepStr: '5'))])


node {

	// On définit le nom de l'image docker qui sera buildée.
	def imageName = "beerapi"

	stage('checkout'){
		checkout scm
	}

	// si jenkins est dans un docker, il faut rajouter -u root
    docker.image('openjdk:8').inside('-e MAVEN_OPTS="-Duser.home=./"') {
		stage('check tools') {
             parallel(
                 java: {
                    sh "java -version"
                },
                maven: {
                    sh "chmod +x mvnw"
                    sh "./mvnw -version"
                }
            )
        }


		stage('clean') {
			sh "./mvnw clean"
		}

		stage('backend tests') {
            try {
                sh "./mvnw test"
            } catch(err) {
                throw err
            } finally {
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }

        stage('Quality Analysis') {
            withSonarQubeEnv('sonar') {
                sh './mvnw sonar:sonar'
            }
        }

        stage('packaging') {
            sh "./mvnw verify -Pprod -DskipTests"
            archiveArtifacts artifacts: '**/target/*.jar,**/target/docs/*.html', fingerprint: true
        }
    }

    stage('build docker') {
		// l'image sera buildée dans target/docker

		// récupération des fichiers nécessaire pour faire l'image
        sh "git clone https://github.com/pdelaby/docker-for-spring-boot.git target/docker"

		// copie du jar de l'application
        sh "cp target/*.jar target/docker/"

		// build
        docker.build("fac/${imageName}:latest", 'target/docker')
    }


	stage('stop et rm docker'){
		// Word Count : on compte les lignes présentes du docker PS
		def imageWC = sh(script: "docker ps -a -q --filter \"name=${imageName}\" | wc -l", returnStdout: true).trim()

		// Si on trouve une image, on l'éteint et on la vire
		if ( imageWC == "1" ){
			sh "docker stop ${imageName}"
			sh "docker rm ${imageName}"
		}else{
			println 'Image non demarree'
		}

	}
	stage('start docker'){
		// démarrage de l'image et configuration pour qu'elle se connecte à spring boot admin
		// qui est dans le réseau springadmin
		sh "docker run -d --name ${imageName} --net=springadmin -p 9999:8080 fac/${imageName}:latest"
	}
}