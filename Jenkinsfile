pipeline 
{
    agent any
    
    tools{
        maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        stage("Deploy to DEV"){
            steps{
                echo("deploy to DEV")
            }
        }
        
        
                
        stage('Sanity Automation Tests on DEV') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/prizm09/AutomationOpenCart.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng_sanity.xml -Denv=dev"
                    
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }
        
        
                
        stage('Regression Automation Tests') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/prizm09/AutomationOpenCart.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng_regression.xml -Denv=qa"
                    
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/prizm09/AutomationOpenCart.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resource/testrunners/testng_sanity.xml -Denv=stage"
                    
                }
            }
        }
        
        
    }
}