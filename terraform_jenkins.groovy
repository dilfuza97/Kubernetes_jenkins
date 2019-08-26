node('master'){
  stage('Check terraform') {

    try {
      // Trying to run terraform command
      env.terraform  = sh returnStdout: true, script: 'terraform --version'
      echo """
      echo Terraform already installed version ${env.terraform}
      """
}
    stage("Download Terraform"){
            steps{
                ws("tmp/"){
                    script {
                        def exists = fileExists 'terraform_0.12.7_linux_amd64.zip'
                        if (exists) {
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "sudo mv terraform /bin"
                            sh "terraform version"
                        } else {
                            sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"
                            sh "unzip -o terraform_0.12.7_linux_amd64.zip"
                            sh "sudo mv terraform /bin"
                            sh "terraform version"
                        }
                    }
      stage("Terraform plan"){
      sh "terraform init"
      sh "terraform plan"

    }

    stage("Terraform apply"){
        sh "terraform apply --auto-approve"

      }
  }
                }
            }
        }
  }      
