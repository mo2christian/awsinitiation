# Introduction au cloud
Liste des commandes à executer lors de la formation

Avant de commencer l'execution des commandes, il est necessaire de configurer le CLI via la commance **aws configure**.
Specifier comme region par defaut eu-west-3, qui correspond à la region de Paris.
Renseigner ses credentials : ACCESS KEY et SECRET KEY (Ils vous seront fourni par le fomateur)

 # Création d'un serveur web
 Pour creer le serveur, utiliser la commande suivante : 
 **aws ec2 run-instances --image-id ??ami-id?? --count 1 --instance-type t2.micro --key-name ??key??** 
 avec les parametres suivants : 
 * ami-id : l'ID de l'image. On pourra prendre l'image ami-0de12f76efe134f2f
 * key : le nom de clé SSH qui devra etre crée au préalable.
 La commande se presentera ainsi : **aws ec2 run-instances --image-id ami-014d8dccd70fd2632 --count 1 --instance-type t2.micro --key-name demo**
 L'execution de la commande vous retournera un json dont le champs 'InstanceId' correspond à l'identifiant de votre machine. Pensez à le noter.
 
 Il est important de tagger ses ressources afin de faciliter leur recherche. Taggez votre machine avec la commande 
 **aws ec2 create-tags --resources ??instanceId?? --tags Key=Name,Value=??name??**
 avec les parametres suivants :
 * instanceId: qui est l'identifiant de votre machine
 * name: le nom que vous voulez assigner à votre machine
 
 Connectez vous à votre machine en utilisant la commande **ssh ubuntu@??public-ip?? -i /path/to/keypair** où piblic-ip est l'adresse publique de votre machine.
 Vous pouvez l'obtenir avec la commande suivante **aws ec2 describe-instances --filters "Name=tag:Name,Values=??name??" --query "Reservations[].Instances[].PublicDnsName"**
 Lancer l'installation du serveur NGINX avec la commande **sudo apt install nginx**
 
 Pour creer votre fichier, saisir la commande **sudo nano** qui vous ouvrira un éditeur en ligne de commande. Vous pourrez saisir votre code HTML et l'enregister sous le nom **index.html** dans le répertoire **/var/www/html**
