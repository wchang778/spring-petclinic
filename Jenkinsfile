pipeline {
    agent any

    environment {
        APP_NAME = 'Spring-PetClinic'
    }

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
        
        stage('Test') {
            steps {

                // Run Maven on a Unix agent.
                sh "./mvnw clean test"

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

                always {
                    emailext (
                        subject: "${APP_NAME} [proj=${PROJECT_NAME}, branch=${BRANCH_NAME}, job=${JOB_NAME}, build=${BUILD_NUMBER}, status=${BUILD_STATUS}]",
                        body: """
                        <h3>${APP_NAME}</h3> 
                        <p>
                        proj=${PROJECT_NAME}, branch=${BRANCH_NAME}, job=${JOB_NAME}, build=${BUILD_NUMBER}, status=${BUILD_STATUS}
                        </p>

                        <p>
                        job_url=${JOB_DISPLAY_URL}
                        </p>

                        <p>
                        run_url=${RUN_DISPLAY_URL}
                        </p>
                        """,
                        to: 'test@mailhog.com'
                    )
                }
            }
        }

        stage('Build') {
            steps {
                echo 'Building ....'
            }
        }
    }
}
