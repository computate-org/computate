#!/bin/bash

export langueNom="${langueNom:-frFR}"
export siteNom="${siteNom:-computate}"
export appliUrl="${appliUrl:-https://github.com/computate/$siteNom.git}"
export appliUtilisateur="${appliUtilisateur:-$USER}"
export appliGroupe="${appliUtilisateur:-$appliUtilisateur}"
export siteChemin="${siteChemin:-/usr/local/src/$siteNom}"
export appliComputateChemin="${appliComputateChemin:-/usr/local/src/computate}"
export appliComputateUrl="${appliComputateUrl:-https://github.com/computate/computate.git}"
export nomFichierConfig="${siteNom}.config"
export cheminConfig="$siteChemin/config/$nomFichierConfig"

function computate() {
	echo "Voulez-vous exécuter la commande ci-dessous ? "
	echo
	echo "$1"
	read -p '[s]auter, [q]uitter ou [Entrée] pour continuer: ' -i '' ACTION_COMMANDE
	case $ACTION_COMMANDE in 
		[Ss]* );;
		[Qq]* ) exit 0 ;;
		* )
		eval "$1"
		;;
	esac
}

export appliComputateUtilisateurActuel=$(ls -ld "$appliComputateChemin" | awk '{print $3}')
export appliComputateGroupeActuel=$(ls -ld "$appliComputateChemin" | awk '{print $4}')

if [ ! -d $appliComputateChemin ]; then
	computate "sudo mkdir -p $appliComputateChemin"
fi
if [ "$appliComputateUtilisateurActuel" != "$appliUtilisateur" ] || [ "$appliComputateGroupeActuel" != "$appliGroupe" ]; then
	computate "sudo chown -R $appliUtilisateur:$appliGroupe $appliComputateChemin"
fi
if [ ! -d $appliComputateChemin/config ]; then
	computate "git clone $appliComputateUrl $appliComputateChemin"
fi
$appliComputateChemin/bin/frFR/installer.sh

export appliUtilisateurActuel=$(ls -ld "$siteChemin" | awk '{print $3}')
export appliGroupeActuel=$(ls -ld "$siteChemin" | awk '{print $4}')

if [ ! -d $siteChemin ]; then
	computate "sudo mkdir -p $siteChemin"
fi
if [ "$appliUtilisateurActuel" != "$appliUtilisateur" ] || [ "$appliGroupeActuel" != "$appliGroupe" ]; then
	computate "sudo chown -R $appliUtilisateur:$appliGroupe $siteChemin"
fi
if [ ! -d $siteChemin/config ]; then
	computate "git clone $appliUrl $siteChemin"
fi

