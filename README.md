# jmoordbadminfaces
jmoordbadminfaces, genera proyecto web basado en adminfaces
Este usa Adminfaces
https://adminfaces.github.io/site/

con Las dependencias de jmooordb

Pasos para usar:

* Verificar que exista el archivo settings.xml en el directorio .m2
  Si no existe en el proyecto en Web Pages esta el archivo settings.xml 
  que puede copiar a la carpeta .m2

* Configurar el path de Maven
sudo gedit /etc/profile

export M2_HOME=/home/avbravo/netbeans11/java/maven/

export M2=$M2_HOME/bin

export PATH=$M2:$PATH 



* Clonar el proyecto
<pre>

crear una carpeta
   mkdir adminfacesarchetype

entrar a la carpeta
   cd adminfacesarchetype

clonar el proyecto
git clone https://github.com/avbravo/adminfacesarchetype.git


Ejecutar

 mvn archetype:create-from-project

Entrar al directorio archetype dentro de

cd /target/generated-sources/archetype 

Ejecutar 
mvn install

Con esto tenemos nuestro archetype listo para ser usado

</pre>

* Ejecutar



Crear un proyecto web basado en adminfaces
<pre>
Pasos:
1.Crear el directorio
    mkdir testing

2. Entrar el directorio
   cd testing
   

Ejecutar

* Recuerde observar la version del archetyp
 mvn archetype:generate -Dfilter=com.avbravo:jmoordbadminfaces-archetype -DarchetypeVersion=0.1

Responder a las preguntas

1: local -> com.avbravo:myshowcase-archetype (myshowcase-archetype)
Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains): : 1
Define value for property 'groupId': 
com.avbravo               
                         
Define value for property 'artifactId': 
adminfacesarchetype

Define value for property 'version' 1.0-SNAPSHOT: 
 0.1

Define value for property 'package' com.avbravo: 
 com.avbravo

Confirm properties configuration:

Responder Y 


</pre>

Esto genera el nuevo proyecto basado en adminfaces con todos los componentes. Sugerencia es un proyecto de ejemplo
el creara todos los paquetes y los incluira en los nuevos paquetes que especifico 
borrelos para evitar conflictos.

