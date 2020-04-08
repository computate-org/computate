
# Install prerequisites

Create a Red Hat account at https://developers.redhat.com/

```bash
sudo yum install -y ansible python3 python3-pip docker
sudo pip install pip
sudo pip install openshift kubernetes
sudo systemctl start docker
sudo systemctl enable docker

oc new-app https://github.com/computate/computate-scolaire.git --image-stream redhat-openjdk18-openshift:1.5
```

# Install Ansible and dnf for python3 supported package management. 

```bash
sudo yum install -y ansible dnf
```

# Install the computate project

```bash
sudo install -d -o $USER -g $USER /usr/local/src/computate
git clone git@github.com:computate/computate.git /usr/local/src/computate

install -d /usr/local/src/computate/ansible/inventories/tower2/host_vars/tower2
ansible-vault create /usr/local/src/computate/ansible/inventories/tower2/host_vars/tower2/vault
ansible-playbook install-freeipa-vm.yml -i inventories/tower2/hosts --vault-id @prompt

```

# Setup computer the computate way. 

```bash
ansible-playbook computate-setup-computer.yml -i /usr/local/src/computate/ansible/inventories/$USER-$HOSTNAME/hosts --vault-id @prompt
```

# Install computate-zookeeper on OpenShift

```bash
ansible-playbook install-computate-zookeeper.yml -i /usr/local/src/computate/ansible/inventories/$USER-$HOSTNAME/hosts --vault-id @prompt
```

# Install solr-operator

```bash
ansible-playbook install-solr-operator-src.yml -i /usr/local/src/computate/ansible/inventories/$USER-$HOSTNAME/hosts --vault-id @prompt
```

# Install postgres-operator

```bash
ansible-vault create inventories/$USER-$HOSTNAME/host_vars/$HOSTNAME/vault
ansible-vault edit inventories/$USER-$HOSTNAME/host_vars/$HOSTNAME/vault
ansible-playbook install-postgres-operator-src.yml -i inventories/$USER-$HOSTNAME/hosts --vault-id @prompt
ansible-playbook install-postgres-operator.yml -i /usr/local/src/computate/ansible/inventories/$USER-postgres-operator/hosts -e /usr/local/src/computate/ansible/inventories/$USER-postgres-operator/host_vars/postgres-operator/vault --vault-id @prompt --tags=install
```


# Install Libvirt Server

# Install a FreeIPA Server

```bash
sudo install -d -o $USER -g $USER /usr/local/src/ansible-freeipa
git clone https://github.com/freeipa/ansible-freeipa.git /usr/local/src/ansible-freeipa
install -d ~/.ansible/roles
ln -s /usr/local/src/ansible-freeipa/roles/* ~/.ansible/roles/
install -d ~/.ansible/plugins/modules
ln -s /usr/local/src/ansible-freeipa/plugins/modules/*.py ~/.ansible/plugins/modules/
install -d ~/.ansible/plugins/module_utils
ln -s /usr/local/src/ansible-freeipa/plugins/module_utils/*.py ~/.ansible/plugins/module_utils/
```

# computate-ansible
sudo install -d -o $USER -g $USER /usr/local/src/computate-ansible
git clone git@github.com:computate/computate-ansible.git /usr/local/src/computate-ansible/
install -d /usr/local/src/computate/ansible/inventories/computate/group_vars
install -d /usr/local/src/computate/ansible/inventories/computate/host_vars
install -d /usr/local/src/computate/ansible/inventories/computate/host_vars/RHTE-computate-demo
ansible-vault create /usr/local/src/computate/ansible/inventories/computate/host_vars/RHTE-computate-demo/vault
ansible-vault edit /usr/local/src/computate/ansible/inventories/computate/host_vars/RHTE-computate-demo/vault

# Install VM. 
cd /usr/local/src/computate/ansible
ansible-playbook computate-scolaire-vm.yaml -u ctate
ansible-playbook computate-scolaire-vm.yaml -i inventories/computate/hosts -u $USER --extra-vars "target=tower1" --vault-id @prompt
ansible-playbook --extra-vars "target=tower1 ansible_sudo_pass= ansible_become_pass= ansible_ssh_pass=" computate-scolaire-vm.yaml -u ctate

# Install Ant. 
cd /usr/local/src/computate/ansible
ansible-playbook install-ant.yaml -i inventories/computate/hosts -u demo --extra-vars "target=RHTE-computate-demo" --vault-id @prompt

# Install Zookeeper. 
cd /usr/local/src/computate/ansible
ansible-playbook install-zookeeper.yaml -i inventories/computate/hosts -u demo --extra-vars "target=RHTE-computate-demo" --vault-id @prompt

# Install solr
cd /usr/local/src/computate/ansible
ansible-playbook install-solr.yaml -i inventories/computate/hosts -u demo --extra-vars "target=RHTE-computate-demo" --vault-id @prompt

# Install Bridge Network

the nmcli ansible module is actually broken, so this one doesn't work very well. 

```bash
ansible-playbook install-bridge-network.yml -e @/usr/local/src/computate/ansible/inventories/tower2/host_vars/tower2/vault --vault-id @prompt
```
