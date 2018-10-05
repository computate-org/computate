#!/bin/bash

export langueNom="${langueNom:-frFR}"
export appliNom="${appliNom:-computate}"
export appliUrl="${appliUrl:-https://github.com/computate/$appliNom.git}"
export appliUtilisateur="${appliUtilisateur:-$USER}"
export appliGroupe="${appliUtilisateur:-$appliUtilisateur}"
export appliChemin="${appliChemin:-/usr/local/src/$appliNom}"
export appliComputateChemin="${appliComputateChemin:-/usr/local/src/computate}"
export appliComputateUrl="${appliComputateUrl:-https://github.com/computate/computate.git}"
export nomFichierConfig="${appliNom}.config"
export cheminConfig="$appliChemin/config/$nomFichierConfig"

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

export appliUtilisateurActuel=$(ls -ld "$appliChemin" | awk '{print $3}')
export appliGroupeActuel=$(ls -ld "$appliChemin" | awk '{print $4}')

if [ ! -d $appliChemin ]; then
	computate "sudo mkdir -p $appliChemin"
fi
if [ "$appliUtilisateurActuel" != "$appliUtilisateur" ] || [ "$appliGroupeActuel" != "$appliGroupe" ]; then
	computate "sudo chown -R $appliUtilisateur:$appliGroupe $appliChemin"
fi
if [ ! -d $appliChemin/config ]; then
	computate "git clone $appliUrl $appliChemin"
fi

