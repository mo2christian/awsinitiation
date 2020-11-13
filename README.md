# Introduction au cloud
Liste des commandes à executer lors de la formation

Avant de commencer l'execution des commandes, il est necessaire de configurer le CLI via la commande 
* **aws configure**.

Specifier comme region par defaut eu-west-3, qui correspond à la region de Paris.
Renseigner ses credentials : ACCESS KEY et SECRET KEY (Ils vous seront fourni par le fomateur)


 # Création d'un serveur web
 Pour creer le serveur, utiliser la commande suivante 
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
 
 Connectez vous à votre machine en utilisant la commande **ssh ubuntu@??public-ip?? -i /path/to/keypair** où public-ip est l'adresse publique de votre machine.
 Vous pouvez l'obtenir avec la commande suivante **aws ec2 describe-instances --filters "Name=tag:Name,Values=??name??" --query "Reservations[].Instances[].PublicDnsName"**
 Lancer l'installation du serveur NGINX avec la commande **sudo apt install nginx**
 
 Pour creer votre fichier, saisir la commande **sudo nano** qui vous ouvrira un éditeur en ligne de commande. Vous pourrez saisir votre code HTML et l'enregister sous le nom **index.html** dans le répertoire **/var/www/html**
 
 Les tests terminés, vous pouvez supprimer l'instance avec la commande **aws ec2 terminate-instances --instance-ids ??instance-id??**

 # Utilisation de S3
 La création d'un bucket se fait avec la commande **aws s3api create-bucket ??name??** où :
 * name: represente le nom du bucket. 
 
 A noter que le bucket a une porté globale c-a-d que son son doit etre unique pour tous les buckets amazon. La pratique est de surfixer le nom avec le nom de domaine de l'entreprise ex: fr.softeamgroup.
 
 La commande se presentera sous cette forme : **aws s3api create-bucket --bucket fr.softeamgroup.formation-awsinitiation --region us-east-1**
 
 Verifier que le bucket a bien été crée avec la commande : **aws s3 ls**. Cette commande vous listera tous vos buckets, quelque soit la région. On peut lister le contenu d'un bucket en utilisant la commande **aws s3 ls s3://bucket**
 
 L'upload d'un fichier se fait comme suit : **aws s3 cp /path/to/local/file s3://bucket**.
 On peut utiliser la meme commande pour telecharger un document depuis le bucket vers notre poste local en utilisant la commande **aws s3 cp s3://bucket /path/to/local/file**
 
 La suppression d'un bucket se fait avec la commande **aws s3api delete-bucket --bucket bucket**. La suppression de s'effectura pas si le bucket n'est pas vide.
 
 Pour supprimer un ficher dans un bucket, utiliser la commande **aws s3 rm s3://bucket/file**. La suppression de tous les fichiers d'un bucket se fait avec la commande **aws s3 rm s3://bucket --recursive**
 
 # Creation d'un lambda
 La creation d'une function necessite un role, qui définit les habilitations de cette fonction.
 
 Le role peut etre crée avec la commande suivante : **aws iam create-role --role-name ??name?? --assume-role-policy-document file://??file??** où
 * name: représente le nom du role
 * file: le nom du fichier définissant les services ou entités pouvant utiliser ce role
 
 La creation du role effectué, il faut preciser les permissions qu'accordent ce role.
 La commande **aws iam create-policy --policy-name ??name?? --policy-document file://??file??** où
 * name: le nom de la police
 * file: le fichier definissant toutes les permissions
 
 Les permissions definies, on peut les rattacher au role avec la commande **aws iam attach-role-policy --role-name ??roleName?? --policy-arn arn:aws:iam::??idcompte??:policy/??policyName??**
 
 Pour avoir des informations sur un role ou une policy, utiliser les commandes 
 * **aws iam get-role --role-name ??roleName??**
 * **aws iam get-policy --policy-arn ??policyArn??**
 
 Le role disponible, on peut creer notre lambda avec la commande **aws lambda create-function --function-name hello --runtime python3.8 --role arn:aws:iam::XXXXXX:role/??roleName?? --handler hello.handle --zip-file fileb://hello.zip**
 
 La suppression de la fonction se fait avec la commande **aws lambda delete-function hello**
 
