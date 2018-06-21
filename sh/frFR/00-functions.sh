#!/bin/bash

CHEMIN_APPLI="$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )"
NOM_FICHIER_CONFIG="computate.config"
CHEMIN_CONFIG="$CHEMIN_APPLI/config/$NOM_FICHIER_CONFIG"
NOM_LANGUE="frFR"

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

if [ ! -f $CHEMIN_CONFIG ]; then
	computate "cp $CHEMIN_APPLI/config/$NOM_LANGUE/$NOM_FICHIER_CONFIG $CHEMIN_CONFIG"
fi

function config_var {
	config_var=$(cat "$CHEMIN_CONFIG" | perl -0777ne 'print "$1" if /\[\s*'"$1"'\s*\][\s\S]*?\s+'"$2"'\s*=\s*(.*)\s*/')
	export "$2"="${config_var}"
}

config_var maven VERSION_MAVEN
export VERSION_MAVEN="${VERSION_ZOOKEEPER:-3.5.3}"

config_var zookeeper VERSION_ZOOKEEPER
export VERSION_ZOOKEEPER="${VERSION_ZOOKEEPER:-3.5.4}"
config_var zookeeper PREFIXE_PORT_ZOOKEEPER
export PREFIXE_PORT_ZOOKEEPER="${PREFIXE_PORT_ZOOKEEPER:-102}"
config_var zookeeper PORT_CLIENT_ZOOKEEPER
export PORT_CLIENT_ZOOKEEPER="${PORT_CLIENT_ZOOKEEPER:-${PREFIXE_PORT_ZOOKEEPER}81}"
config_var zookeeper PORT_ADMIN_ZOOKEEPER
export PORT_ADMIN_ZOOKEEPER="${PORT_ADMIN_ZOOKEEPER:-${PREFIXE_PORT_ZOOKEEPER}80}"

config_var solr VERSION_SOLR
export VERSION_SOLR="${VERSION_SOLR:-7.3.1}"
config_var solr PREFIXE_PORT_SOLR
export PREFIXE_PORT_SOLR="${PREFIXE_PORT_SOLR:-103}"
config_var solr PORT_SOLR
export PORT_SOLR="${PORT_SOLR:-${PREFIXE_PORT_SOLR}83}"
