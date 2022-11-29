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
                step {
                    // Get some code from a GitHub repository
                    scmVars = git branch: 'main', url: 'https://github.com/wchang778/spring-petclinic.git/'
//                    commitHash = scmVars.GIT_COMMIT
//                    env.GIT_COMMIT_HASH = scmVars.GIT_COMMIT
                }
            }
        }

        stage('Test')

        steps {

            // Run Maven on a Unix agent.
            sh "./mvnw -Dmaven.test.failure.ignore=true clean package"

            // To run Maven on a Windows agent, use
            // bat "mvn -Dmaven.test.failure.ignore=true clean package"
        }

        post {
            // If Maven was able to run the tests, even if some of the test
            // failed, record the test results and archive the jar file.
            always {
                junit '**/target/surefire-reports/TEST-*.xml'
                archiveArtifacts 'target/*.jar'

                emailext(
                        attachLog: true,
                        body: 'Please go to ${BUILD_URL} and verify the build',
                        compressLog: true,
                        recipientProviders: [culprits(), requestor(), developers()],
                        to: 'test@jenkins',
                        subject: 'Job [${JOB_NAME}] Build# [${BUILD_NUMBER}] need attention'
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

