pipeline {
    agent any

    triggers {
        cron('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git branch: 'main', url: 'https://github.com/wchang778/spring-petclinic.git/'
            }
        }
        
        stage('Build') {
            steps {

                // Run Maven on a Unix agent.
                sh "./mvnw clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
                failure {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Test') {
            steps {
                echo 'Testing ....'
            }
        }
    }
}
