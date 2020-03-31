
# Install Ansible 

```bash
sudo yum install -y ansible
```

# Install the computate project

```bash
sudo install -d -o $USER -g $USER /usr/local/src/computate
git clone git@github.com:computate/computate.git /usr/local/src/computate
```

# Install a FreeIPA Server

```bash
sudo install -d -o $USER -g $USER /usr/local/src/ansible-freeipa
git clone https://github.com/freeipa/ansible-freeipa.git /usr/local/src/ansible-freeipa

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
