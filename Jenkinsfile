#!/usr/bin/env groovy

// Ne garder que 5 builds et 5 artefacts
properties([buildDiscarder(logRotator( artifactNumToKeepStr: '5', numToKeepStr: '5'))])


node {

    stage('checkout') {
        checkout scm
		//git url: 'https://github.com/spring-projects/spring-petclinic.git'
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
            archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }

		stage('cleanup'){
			cleanWs()
		}
    }
}


