pipeline {
	agent any
	
	stages {
		stage('Compile Stage') {
			steps {
				withMaven(maven:'maven_3.6.3') {
					sh 'mvn clean compile'
				}
			}
		}
		
		stage('TEST stage') {
			steps {
				withMaven(maven:'maven_3.6.3') {
					sh 'mvn clean test'
				}
			}
		}
		
		stage('Build') {
			steps {
				withMaven(maven:'maven_3.6.3') {
					sh 'mvn clean install'
				}
			}
		}
	}
}