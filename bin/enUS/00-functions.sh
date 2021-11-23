#!/bin/bash

export languageName="${languageName:-enUS}"
export siteName="${siteName:-computate}"
export sitePath="${sitePath:-$(readlink -f $( dirname $( dirname $( dirname ${BASH_SOURCE[0]} ) ) ) )}"
export appComputatePath="${appComputatePath:-/usr/local/src/computate}"
export configFileName="${siteName}.config"
export configPath="$sitePath/config/$configFileName"

function computate() {
	echo "Do you want to execute the command below? "
	echo
	echo "$1"
	read -p '[s]kip, [q]uit ou [Enter] to continue: ' -i '' ACTION_COMMAND
	case $ACTION_COMMAND in 
		[Ss]* );;
		[Qq]* ) exit 0 ;;
		* )
		eval "$1"
		;;
	esac
}

if [ ! -f $appComputatePath/config/computate.config ]; then
	computate "cp $appComputatePath/config/$languageName/computate.config $appComputatePath/config/computate.config"
fi

function config_var {
	config_var=$(cat "$configPath" | perl -0777ne 'print "$1" if /\[\s*'"$1"'\s*\][\s\S]*?\s+'"$2"'\s*=\s*(.*)\s*/')
	export "$2"="${config_var}"
}

function computate_config_var {
	config_var=$(cat "$appComputatePath/config/computate.config" | perl -0777ne 'print "$1" if /\[\s*'"$1"'\s*\][\s\S]*?\s+'"$2"'\s*=\s*(.*)\s*/')
	export "$2"="${config_var}"
}

computate_config_var maven versionMaven
export versionMaven="${versionMaven:-3.5.3}"

computate_config_var zookeeper versionZookeeper
export versionZookeeper="${versionZookeeper:-3.5.4}"
computate_config_var zookeeper prefixPortZookeeper
export prefixPortZookeeper="${prefixPortZookeeper:-102}"
computate_config_var zookeeper portClientZookeeper
export portClientZookeeper="${portClientZookeeper:-${prefixPortZookeeper}81}"
computate_config_var zookeeper portAdminZookeeper
export portAdminZookeeper="${portAdminZookeeper:-${prefixPortZookeeper}80}"

computate_config_var solr versionSolr
export versionSolr="${versionSolr:-7.1.0}"
computate_config_var solr prefixPortSolr
export prefixPortSolr="${prefixPortSolr:-103}"
computate_config_var solr portSolr
export portSolr="${portSolr:-${prefixPortSolr}83}"
export URL_SOLR="http://localhost:${portSolr}/solr"
