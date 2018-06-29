#!/bin/bash

cheminAppli="$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )"
nomFichierConfig="computate.config"
cheminConfig="$cheminAppli/config/$nomFichierConfig"
nomLangue="frFR"

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

if [ ! -f $cheminConfig ]; then
	computate "cp $cheminAppli/config/$nomLangue/$nomFichierConfig $cheminConfig"
fi

function config_var {
	config_var=$(cat "$cheminConfig" | perl -0777ne 'print "$1" if /\[\s*'"$1"'\s*\][\s\S]*?\s+'"$2"'\s*=\s*(.*)\s*/')
	export "$2"="${config_var}"
}

config_var maven versionMaven
export versionMaven="${versionMaven:-3.5.3}"

config_var zookeeper versionZookeeper
export versionZookeeper="${versionZookeeper:-3.5.4}"
config_var zookeeper prefixePortZookeeper
export prefixePortZookeeper="${prefixePortZookeeper:-102}"
config_var zookeeper portClientZookeeper
export portClientZookeeper="${portClientZookeeper:-${prefixePortZookeeper}81}"
config_var zookeeper portAdminZookeeper
export portAdminZookeeper="${portAdminZookeeper:-${prefixePortZookeeper}80}"

config_var solr versionSolr
export versionSolr="${versionSolr:-7.3.1}"
config_var solr prefixePortSolr
export prefixePortSolr="${prefixePortSolr:-103}"
config_var solr portSolr
export portSolr="${portSolr:-${prefixePortSolr}83}"
export URL_SOLR="http://localhost:${portSolr}/solr"
