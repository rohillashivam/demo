pipeline {
	agent any
	
	tools {
        maven 'maven_3.5.4' 
    }
    
	stages {
		stage('Compile Stage') {
			steps {
				//withMaven(maven:'maven_3.5.4') {
					sh 'mvn clean compile'
				//}
			}
		}
		
		stage('TEST stage') {
			steps {
				//withMaven(maven:'maven_3.5.4') {
					sh 'mvn clean test'
				//}
			}
		}
		
		stage('Build') {
			steps {
				//withMaven(maven:'maven_3.5.4') {
					sh 'mvn clean install'
				//}
			}
		}
		
	}
}