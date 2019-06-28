# Please follow the steps 
## Run the application on Centos 7
```
git clone https://github.com/dilfuza97/Kubernetes_jenkins.git
cd applicaiton_deployment
yum -y install epel-release && yum clean all
yum -y install python-pip && yum clean all
yum install mariadb-devel  -y
yum install gcc -y
yum -y install python-devel libxslt-devel libffi-devel openssl-devel
pip install -r requirements.txt
nohub python app.py & 
```


# Dockerize the application 
```
git clone https://github.com/dilfuza97/Kubernetes_jenkins.git
cd applicaiton_deployment
docker build acirrustech:acirrustech . 
docker run -dti -p 80:5000 acirrustech:acirrustech
```
