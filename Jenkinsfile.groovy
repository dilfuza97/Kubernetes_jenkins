
node('master') {
  properties([parameters([string(defaultValue: 'DEV', description: 'Provide ENV', name: 'ENV', trim: true)])])
  properties([parameters([booleanParam(defaultValue: true, description: 'This will do terraform apply', name: 'Terraform_apply'), booleanParam(defaultValue: false, description: 'This will do terraform destroy', name: 'Terraform_destroy')])])

    stage('Clone repo') {
    git 'https://github.com/dilfuza97/Kubernetes_jenkins.git'
  }

   stage("Terraform init") {
     dir("${WORKSPACE}/Kubernetes_jenkins/artemis.tf") {
       sh "terraform init"
     }
   }

   stage('Terraform Apply/Plan') {
         if (params.Terraform_apply) {
           dir("${WORKSPACE}/Kubernetes_jenkins/artemis.tf/") {
             echo "##### Terraform Applying the Changes ####"
             sh "terraform apply  --auto-approve  -var-file=grafana.tfvars"
           }
        }
    }

    stage('Terraform Destoy') {
         if (params.Terraform_destroy) {
           dir("${WORKSPACE}/Kubernetes_jenkins/artemis.tf/") {
             echo "##### Terraform Destroying the Changes ####"
             sh "terraform destroy  --auto-approve  -var-file=grafana.tfvars"
           }
         }
       }


}
