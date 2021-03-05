node {
	def app
	
	stage('Clone repository') {               
    	git branch: '0.2.6-devops-work',
        credentialsId: 'GitHub Credentials',
        url: 'https://github.com/careydevelopment/ecosystem-user-service.git'
    } 

	stage('Build JAR') {
    	docker.image('maven:3.6.3-jdk-11').inside('-v /root/.m2:/root/.m2') {
        	sh 'mvn -B clean package'
        	stash includes: '**/target/ecosystem-user-service.jar', name: 'jar'
    	}
    }
     
    stage('Build Image') {
    	unstash 'jar'
		app = docker.build 'brianmcarey/repo'
    }
    
    stage('Push') {
    	docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {            
			app.push("$BUILD_NUMBER")
			app.push('latest')           
        }    
    }
}