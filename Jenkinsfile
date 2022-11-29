
pipeline {
    agent any

    environment {
        APP_NAME = 'Spring-PetClinic'
    }

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                scmVars = git branch: 'main', url: 'https://github.com/wchang778/spring-petclinic.git/'
                commitHash = scmVars.GIT_COMMIT
                env.GIT_COMMIT_HASH = scmVars.GIT_COMMIT
            }
        }
        
        stage('Test')

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
                    // archiveArtifacts 'target/*.jar'
                }

                failure {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }

                always {
                    emailext (
                        subject: "${APP_NAME} [proj=${env.PROJECT_NAME}, branch=${env.BRANCH_NAME}, job=${env.JOB_NAME}, build=${env.BUILD_NUMBER}, status=${env.BUILD_STATUS}]",
                        body: """
                        <h3>${APP_NAME}</h3> 
                        <p>
                        proj=${env.PROJECT_NAME}, branch=${env.BRANCH_NAME}, job=${env.JOB_NAME}, build=${env.BUILD_NUMBER}, status=${env.BUILD_STATUS}, commit=${env.GIT_COMMIT_HASH}
                        </p>

                        <p>
                        job_url=${env.JOB_DISPLAY_URL}
                        </p>

                        <p>
                        run_url=${env.RUN_DISPLAY_URL}
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
