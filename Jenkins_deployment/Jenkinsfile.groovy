
node('master') {
  properties([parameters([string(defaultValue: 'DEV', description: 'Provide ENV', name: 'ENV', trim: true)])])
  properties([parameters([booleanParam(defaultValue: true, description: 'This will do terraform apply', name: 'Terraform_apply'), booleanParam(defaultValue: false, description: 'This will do terraform destroy', name: 'Terraform_destroy')])])

    stage('Clone repo') {
    git 'https://github.com/dilfuza97/Kubernetes_jenkins.git'
  }

    stage("Download Terraform"){
       sh "wget https://releases.hashicorp.com/terraform/0.12.7/terraform_0.12.7_linux_amd64.zip"
        sh "unzip -o terraform_0.12.7_linux_amd64.zip"
        sh "sudo mv terraform /bin"
        sh "terraform version"

   stage("Terraform init") {
     dir("${WORKSPACE}/kub_deployment/Jenkins_deployment") {
       sh "terraform init"
     }
   }

   stage('Terraform Apply/Plan') {
         if (params.Terraform_apply) {
           dir("${WORKSPACE}/kub_deployment/Jenkins_deployment") {
             sh "terraform apply --auto-approve /kub_deployment/Jenkins_deployment"
           }
        }
    }

    stage('Terraform Destoy') {
         if (params.Terraform_destroy) {
          dir("${WORKSPACE}/kub_deployment/Jenkins_deployment") {
             sh "terraform destroy --auto-approve /kub_deployment/Jenkins_deployment"

         }
       }
     }
  }
}
